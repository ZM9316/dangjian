package a9chou.com.fangjiazhuangApp.module;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.FragmentViewPagerAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.db.BookList;
import a9chou.com.fangjiazhuangApp.module.activity.ActiveMessageActivity;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityEditActivity;
import a9chou.com.fangjiazhuangApp.module.activity.ActivitySignInActivity;
import a9chou.com.fangjiazhuangApp.module.activity.LoginActivity;
import a9chou.com.fangjiazhuangApp.module.activity.OldMoviesActivity;
import a9chou.com.fangjiazhuangApp.module.activity.ReadActivity;
import a9chou.com.fangjiazhuangApp.module.activity.SignInActivity;
import a9chou.com.fangjiazhuangApp.module.dao.LoginBean;
import a9chou.com.fangjiazhuangApp.module.dao.UpDateBean;
import a9chou.com.fangjiazhuangApp.module.fragment.ActivitFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.HomeFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.LearningOnLineFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.LearningUnderstandFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.MemorabiliaFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.MineFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.ThreeLssonsFragment;
import a9chou.com.fangjiazhuangApp.utils.ExampleUtil;
import a9chou.com.fangjiazhuangApp.utils.LocalBroadcastManager;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.SpPermissionUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UpDateUtil;
import a9chou.com.fangjiazhuangApp.utils.XsnowPermissionUtil;
import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.main_tab)
    TabLayout mMainTab;
    private List<Fragment> mFragmentList;
    private long exitTime = 0;
    private String mId;
    private List<BookList> bookLists;
    private BookList mBookList;
    private List<String> mTitles;
    private int[] tabIcons = {
            R.drawable.selector_tab1,
            R.drawable.selector_tab2,
            R.drawable.selector_tab3,
            R.drawable.selector_tab5,
            R.drawable.selector_tab4
    };
    private FragmentViewPagerAdapter mAdapter;
    private String mUrl;
    private String mToken;
    private String mUserName;
    private String mUserid;
    private Boolean mB;
    private String mUpDateUrl="";
    private int mUpDateCode;
    private String mUri;
    private String mUrl1;
    private String mName;
    private String mRemarks;
    private String mRecordId;
    private SharedPreferences sp;
    public static boolean isForeground = false;

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
//        jpush

        registerMessageReceiver();  // used for receive msg

        sp = getSharedPreferences("config", MODE_PRIVATE);
        mToken = sp.getString("token", "");
        mUserName = sp.getString("username", "");
        mUserid = sp.getString("userId", "");
        mB = sp.getBoolean("b",false);
//6.0运行时权限
        XsnowPermissionUtil.getPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Map<String, String> map = new HashMap<>();
        map.put("userName", mUserName);
        map.put("token", mToken);
//向SharedPreferences中存权限
        SpPermissionUtil.sharedPermission(MainActivity.this, map);
        Map<String, String> upDate = new HashMap<>();
        upDate.put("userName", mUserName);
        upDate.put("token", mToken);
        upDate.put("userId", mUserid);
        //        查。
        bookLists = DataSupport.findAll(BookList.class);
        ViseHttp
                .GET("mobile/sys/sysAppUpdate/data")
                .addParams(upDate)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Gson gson = new Gson();
                        Log.i("tag",data);
                        UpDateBean dateBean = gson.fromJson(data, UpDateBean.class);
                        if(!dateBean.getData().getUrl().equals(null)){
                            mUpDateUrl = dateBean.getData().getUrl();
                            mUpDateCode = dateBean.getData().getVersion();
                            mRemarks = dateBean.getData().getRemarks();
                            int getVCode = UpDateUtil.getVersionCode(MainActivity.this);
                            Log.d(TAG, "onSuccess: " + dateBean.toString());
                            if (getVCode < mUpDateCode&&!mUpDateUrl.equals(null)) {
                                mUri = IP+"/treps" + mUpDateUrl;
                                UpDateUtil.showDialogUpdate(mUri, MainActivity.this, mRemarks);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
        initFragments();
        initTitle();
        mAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles);
        mMainVp.setAdapter(mAdapter);
        mMainTab.setupWithViewPager(mMainVp);
        setupTabIcons();
        int id=getIntent().getIntExtra("tid",-1);
        if(id==1){
            mMainVp.setCurrentItem(2);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    //返回记住当前状态
    @Override
    protected void onRestart() {
        super.onRestart();
        bookLists = DataSupport.findAll(BookList.class);
    }

    private void initTitle() {
        mTitles = new ArrayList<>();
        mTitles.add("首页");
//        mTitles.add("在线学习");
        mTitles.add("学思践悟");
        mTitles.add("三会一课");
        mTitles.add("大事记");
        mTitles.add("我的");
    }

    private void initFragments() {
        mFragmentList = new ArrayList<>();
//        传值
        mFragmentList.add(new HomeFragment());
//        mFragmentList.add(new LearningOnLineFragment());
        mFragmentList.add(new LearningUnderstandFragment());
//        mFragmentList.add(ActivitFragment.newActivityFragment(mUserName, mToken));
        mFragmentList.add(new ThreeLssonsFragment());
        mFragmentList.add(new MemorabiliaFragment());
        mFragmentList.add(new MineFragment());
    }

    /**
     * 双击返回按钮，退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {//
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                myExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void myExit() {
        Intent intent = new Intent();
        intent.setAction("ExitApp");
        this.sendBroadcast(intent);
        super.finish();
    }

    //在线学习 跳转 播放视频界面
    @JavascriptInterface
    public void goToCinema(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] array = content.split(",");
                mUrl1 = array[0];
                mName = array[1];
                if (NetUtil.isWifiConnected(MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, OldMoviesActivity.class);
                    intent.putExtra("url", mUrl1);
                    intent.putExtra("name", mName);
                    startActivity(intent);
                    Log.d(TAG, "run: " + content);
                } else {
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(MainActivity.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("温馨提示！");
                    dialog.setMessage("当前不是wifi状态");
                    dialog.setPositiveButton("土豪无所谓",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, OldMoviesActivity.class);
                                    intent.putExtra("url", mUrl1);
                                    intent.putExtra("name", mName);
                                    startActivity(intent);
                                }
                            });
                    dialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    // 显示
                    dialog.show();

                }
            }
        });
    }

    @JavascriptInterface
    public void goBackActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivitFragment someFragment = (ActivitFragment) mAdapter.instantiateItem(mMainVp, 2);
                WebView webView = (WebView) someFragment.getView().findViewById(R.id.web);
                if (webView.canGoBack()) {
                    webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

                    webView.goBack();
                }
            }
        });
    }

    //在线学习 跳转 阅读角界面
    @JavascriptInterface
    public void readingCorner(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] a = str.split(",");
                mUrl = a[0];
                mId = a[2];
                mRecordId = a[1];
                Log.d(TAG, "run: id   ==>" + mUrl + "  " + mId);
                mBookList = new BookList();
//                mBookList.setBookpath("http://111.205.44.47/treps" + mUrl);
                mBookList.setBookpath(IP+"treps" + mUrl);
                if (bookLists.size() == 0) {
                    mBookList.save();
                } else {
                    boolean isSave = false;
                    for (int i = 0; i < bookLists.size(); i++) {

                        if (bookLists.get(i).getBookpath().equals(mBookList.getBookpath())) {
                            isSave = true;
                            mBookList = bookLists.get(i);
                        }
                    }
                    if (!isSave) {
                        mBookList.save();
                    }
                }
                ReadActivity.openBook(mBookList, MainActivity.this, mId, mRecordId);
            }
        });
    }

    private void setupTabIcons() {
        mMainTab.getTabAt(0).setCustomView(getTabView(0));
        mMainTab.getTabAt(1).setCustomView(getTabView(1));
        mMainTab.getTabAt(2).setCustomView(getTabView(2));
        mMainTab.getTabAt(3).setCustomView(getTabView(3));
        mMainTab.getTabAt(4).setCustomView(getTabView(4));
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(mTitles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }

    //    活动查看人员
    @JavascriptInterface
    public void signInActivity(final String id) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActivitySignInActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    //活动留言跳转
    @JavascriptInterface
    public void activeMessage(final String activeId, final String activeTitle, final String createby) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActiveMessageActivity.class);
                intent.putExtra("activeTitle", activeTitle);
                intent.putExtra("activeId", activeId);
                intent.putExtra("createby", createby);
                startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void activeEdit(final String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActivityEditActivity.class);
                intent.putExtra("str", id);
                startActivity(intent);
            }
        });
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    public void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //三会一课
    @JavascriptInterface
    public void signIn(final String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

}