package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.module.dao.LoginBean;
import a9chou.com.fangjiazhuangApp.utils.ExampleUtil;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.uname_edit)
    EditText mUnameEdit;
    @BindView(R.id.uname_linearlayout)
    LinearLayout mUnameLinearlayout;
    @BindView(R.id.pword_edit)
    EditText mPwordEdit;
    @BindView(R.id.pword_linearlayout)
    LinearLayout mPwordLinearlayout;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    private String mUserName;
    private String mPassWord;
    private LoginBean mLoginBean;
    private String mTag;
    private Map<String, String> mMap;
    private LoadingDailog mDialog;
    private SharedPreferences.Editor mEd;
    private String mUserId;
    private long exitTime = 0;
    private Set<String> tags = new HashSet<String>();

    @Override

    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("登录中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();


    }

    /**
     * 跳转
     *
     * @param c
     */
    private void jumpIntent(Class c) {
        Intent intent = new Intent(LoginActivity.this, c);
        startActivity(intent);
        //Sp存值
        mEd = getSharedPreferences("config", MODE_PRIVATE).edit();
        mEd.putBoolean("b", true);
        mEd.putString("username", mUserName);
        mEd.putString("password", mPassWord);
        mEd.putString("token", mTag);
        mEd.putString("userId", mUserId);
        Log.i("zm9316","username:"+mUserName+"userId:"+mUserId+"token:"+mTag);
        mEd.commit();
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked() {

        //         获取 账号密码
        mUserName = mUnameEdit.getText().toString().trim();
        mPassWord = mPwordEdit.getText().toString().trim();
        //        拼入参数
        mMap = new HashMap<>();
        mMap.put("username", mUserName);
        mMap.put("password", mPassWord);
        mMap.put("mobileLogin", "true");
        mMap.put("login", "x");
        mDialog.show();

        if (NetUtil.isNetworkAvailable(LoginActivity.this)) {
            Go(mMap);
//            test();
        } else {
            ToastUtils.showToast("当前网络不可用，请链接网络");
            mDialog.dismiss();
        }


    }

    // 设置消息推送标签
    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
            }

			ExampleUtil.showToast(logs, getApplicationContext());
        }

    };




    /**
     * 网络请求
     */
    private void Go(Map<String, String> map) {
        Log.i("zm9316","用户名："+map.get("username")+"密码："+map.get("password"));

        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassWord)) {
            mDialog.dismiss();
            ToastUtils.showToast("请输入账号或密码");
        } else {
            ViseHttp.
                    POST("login").
                    addParams(map).
                    request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Gson gson = new Gson();
                            mLoginBean = gson.fromJson(data, LoginBean.class);
                            Log.i("zm9316", "onSuccess: "+data);
                            String loginType = mLoginBean.getType();

                            if ("S".equals(loginType)) {

                                mTag = mLoginBean.getData().getToken();
                                mUserId = mLoginBean.getData().getId();
                                 ToastUtils.showToast(mLoginBean.getData().getName());
                                 ToastUtils.showToast(mLoginBean.getData().getId());
                                //登录成功后判断是否有默认服务站

//                                tags.add("abcd");
//
//                                JPushInterface.setAliasAndTags(getApplicationContext(),
//                                        null, tags, mTagsCallback);
                                JPushInterface.setAlias(LoginActivity.this,1,mUserId);
                                mDialog.dismiss();
                                if (TextUtils.isEmpty(mTag)) {
                                    ToastUtils.showToast("没有Token信息，请及时联系管理员");
                                 } else {
                                    jumpIntent(MainActivity.class);
                                }

                            } else {
                                mDialog.dismiss();
                                ToastUtils.showToast("请输入正确的用户名或密码");
                            }


                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Log.d(TAG, "onFail: " + errCode + "  " + errMsg);
                             mDialog.dismiss();
                            ToastUtils.showToast("网络连接超时，请检查网络");
                         }
                    });
        }
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

}