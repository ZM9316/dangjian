package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.module.dao.LoginBean;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 引导页，三秒跳转
 */
public class SplashActivity extends BaseActivity {
     private boolean mB;
    private SharedPreferences.Editor mEd;
    private String mTag;
    private LoginBean mLoginBean;
    private String mToken;
    private String mUserName;
    private String mpassword;
    private SharedPreferences sp;
    private Map<String, String> map;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }
    @Override
    protected void initViews() {
//        取值
        mB = getSharedPreferences("config", MODE_PRIVATE).getBoolean("b", false);
          Observable.
                timer(3, TimeUnit.SECONDS).
                subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Long value) {
                        if (mB) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    sp = getSharedPreferences("config", MODE_PRIVATE);
                                    mToken = sp.getString("token", "");
                                    mUserName = sp.getString("username", "");
                                    mpassword = sp.getString("password", "");
                                    map=new HashMap<>();
                                    map.put("password", mpassword);
                                    map.put("username", mUserName);
                                    map.put("mobileLogin", "true");
                                    map.put("login", "x");
                                    Go();
                                }
                            }).start();

                        } else {
                            Map<String, String> logout = new HashMap<>();
                            logout.put("userName", mUserName);
                            logout.put("token", mToken);
                            logout.put("mobileLogin", "true");
                            ViseHttp.GET("logout").addParams(logout).request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.d("zm9316", "onSuccess: " + data);
                                    startActivity(new Intent(SplashActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    finish();
                                    SharedPreferences.Editor ed = sp.edit();
                                    SharedPreferences.Editor perEd = getSharedPreferences("per", MODE_PRIVATE).edit();
                                    perEd.clear();
                                    perEd.commit();
                                    ed.clear();
                                    ed.commit();
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d("zm9316", "onFail: " + errCode + "  " + errMsg);
                                }
                            });
                            jumpIntent(LoginActivity.class);

                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 网络请求
     */
    private void Go() {
        ViseHttp.
                POST("login").
                addParams(map).
                request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Gson gson = new Gson();
                        mLoginBean = gson.fromJson(data, LoginBean.class);
                        String loginType = mLoginBean.getType();
                        if ("S".equals(loginType)) {
                            mTag = mLoginBean.getData().getToken();
                            if (!TextUtils.isEmpty(mTag)) {
                               if(mToken.equals(mTag)){
                                   jumpIntent(MainActivity.class);
                               }else{
                                   Map<String, String> logout = new HashMap<>();
                                   logout.put("userName", mUserName);
                                   logout.put("token", mToken);
                                   logout.put("mobileLogin", "true");
                                   ViseHttp.GET("logout").addParams(logout).request(new ACallback<String>() {
                                       @Override
                                       public void onSuccess(String data) {
                                           Log.d("zm9316", "onSuccess: " + data);
                                           startActivity(new Intent(SplashActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                           finish();
                                           SharedPreferences.Editor ed = sp.edit();
                                           SharedPreferences.Editor perEd = getSharedPreferences("per", MODE_PRIVATE).edit();
                                           perEd.clear();
                                           perEd.commit();
                                           ed.clear();
                                           ed.commit();
                                       }

                                       @Override
                                       public void onFail(int errCode, String errMsg) {
                                           Log.d("zm9316", "onFail: " + errCode + "  " + errMsg);
                                       }
                                   });
                                   jumpIntent(LoginActivity.class);
                               }
                            }
                        } else {
                            Map<String, String> logout = new HashMap<>();
                            logout.put("userName", mUserName);
                            logout.put("token", mToken);
                            logout.put("mobileLogin", "true");
                            ViseHttp.GET("logout").addParams(logout).request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.d("zm9316", "onSuccess: " + data);
                                    startActivity(new Intent(SplashActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    finish();
                                    SharedPreferences.Editor ed = sp.edit();
                                    SharedPreferences.Editor perEd = getSharedPreferences("per", MODE_PRIVATE).edit();
                                    perEd.clear();
                                    perEd.commit();
                                    ed.clear();
                                    ed.commit();
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d("zm9316", "onFail: " + errCode + "  " + errMsg);
                                }
                            });
                            jumpIntent(LoginActivity.class);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                        ToastUtils.showToast("网络连接超时，请检查网络");
                        jumpIntent(LoginActivity.class);

                    }
                });
    }

    private void jumpIntent(Class c) {
        Intent intent = new Intent(SplashActivity.this, c);
        startActivity(intent);
    }
}
