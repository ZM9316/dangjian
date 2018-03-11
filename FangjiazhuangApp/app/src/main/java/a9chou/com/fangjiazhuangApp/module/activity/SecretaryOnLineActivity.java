package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.DrawableTextUtil;
import a9chou.com.fangjiazhuangApp.utils.SpPermissionUtil;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class SecretaryOnLineActivity extends BaseActivity {

    private static final String TAG = "SecretaryOnLineActivity";
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;

    @BindView(R.id.activity_secretary_on_line)
    LinearLayout mActivitySecretaryOnLine;
    @BindView(R.id.web)
    WebView mWeb;
    private LoadingDailog mDialog;
    private String mUrl;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_secretary_on_line;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(SecretaryOnLineActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mShykXz.setText("写邮件");
        DrawableTextUtil.setTextCompoundDrawablesLeft(this, R.drawable.e, mShykXz, 0);

        if (SpPermissionUtil.returnZ(SecretaryOnLineActivity.this) == 1) {
            mShykXz.setVisibility(View.VISIBLE);
        } else {
            mShykXz.setVisibility(View.GONE);
        }

        mUrl = UrlUtil.FATHER_HTML + UrlUtil.SJZX + UrlUtil.getWithNo(SecretaryOnLineActivity.this) + "&wp=" + SpPermissionUtil.getSecretaryPermission(SecretaryOnLineActivity.this);


        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, mUrl, SecretaryOnLineActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mShykTitleTitle.setText(title);

                if (mWeb.canGoBack()) {
                    mShykXz.setVisibility(View.GONE);
                } else {
                    if (SpPermissionUtil.returnZ(SecretaryOnLineActivity.this) == 1) {
                        mShykXz.setVisibility(View.VISIBLE);
                    } else {
                        mShykXz.setVisibility(View.GONE);
                    }
                }
            }

        };
        mWeb.setWebChromeClient(wvcc);
    }


    @OnClick({R.id.shyk_left_back, R.id.shyk_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                //                判断webview是否可以
                if (mWeb.canGoBack()) {
                    mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    mWeb.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.shyk_xz:
                Intent intent = new Intent(this, WriteEmail.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mWeb.reload();
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
}