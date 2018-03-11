package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.tu.loadingdialog.LoadingDailog;
import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.SoftKeyboardUtil;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class IdeologicalBuildingActivity extends BaseActivity {

    private static final String TAG = "IdeologicalBuildingActivity";
    @BindView(R.id.big_events_left_back)
    TextView mBigEventsLeftBack;
    @BindView(R.id.big_events_title_title)
    TextView mBigEventsTitleTitle;
    @BindView(R.id.big_events_search_iv)
    ImageView mBigEventsSearchIv;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.search_abolish)
    TextView mSearchAbolish;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.web)
    WebView mWeb;
    private LoadingDailog mDialog;
    private String mUrl;
    private String mName;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_ideological_building;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(IdeologicalBuildingActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        String url = UrlUtil.FATHER_HTML + UrlUtil.IDEOLOGICAL_BUILDING + UrlUtil.getWithNo(this);
        mBigEventsTitleTitle.setText("思想建设");
        mBigEventsSearchIv.setVisibility(View.GONE);
        WebViewUtils.setWebView(mDialog, mWeb, url, IdeologicalBuildingActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mBigEventsTitleTitle.setText(title);
            }
        };
        mWeb.setWebChromeClient(wvcc);
//        待改动
        SoftKeyboardUtil.setSoftKeyboard(this, mBigEventsSearchEdit, mWeb, "Search");
    }

    @OnClick({R.id.big_events_left_back, R.id.big_events_search_iv, R.id.search_abolish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.big_events_left_back:
//                判断webview是否可以
                if (mWeb.canGoBack()) {
                    mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    mWeb.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.big_events_search_iv:
                SearchUtil.showSearch(mBigEventsSearchEdit,this, mBigEventsTitleRelative, mSearchLinear);
                break;
            case R.id.search_abolish:
                SearchUtil.abolishSearch(this, mBigEventsSearchEdit, mBigEventsTitleRelative, mSearchLinear);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWeb.canGoBack()) {
                mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mWeb.goBack();
                return true;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }

    //在线学习 跳转 播放视频界面
    @JavascriptInterface
    public void oldMovie(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                String[] array = content.split(",");
                mUrl = array[0];
                mName = array[1];

                if (NetUtil.isWifiConnected(IdeologicalBuildingActivity.this)) {
                    Intent intent = new Intent(IdeologicalBuildingActivity.this, OldMoviesActivity.class);
                    intent.putExtra("url", mUrl);
                    intent.putExtra("name", mName);
                    startActivity(intent);
                    Log.d(TAG, "run: " + content);
                } else {
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(IdeologicalBuildingActivity.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("温馨提示！");
                    dialog.setMessage("当前不是wifi状态");
                    dialog.setPositiveButton("土豪无所谓",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(IdeologicalBuildingActivity.this, OldMoviesActivity.class);
                                    intent.putExtra("url", mUrl);
                                    intent.putExtra("name", mName);
                                    startActivity(intent);
                                    Log.d(TAG, "run: " + content);
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


}