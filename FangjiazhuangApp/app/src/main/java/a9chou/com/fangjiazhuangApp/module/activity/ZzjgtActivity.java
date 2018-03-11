package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;


public class ZzjgtActivity extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    private String mUrl;
    private LoadingDailog mDialog;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_zzjgt;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ZzjgtActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
        if ("0".equals(tag)) {
            mUrl = UrlUtil.FATHER_HTML+ UrlUtil.ZZJJT+UrlUtil.getWithHas(this);
        } else if ("1".equals(tag)) {
            mUrl = UrlUtil.FATHER_HTML+ UrlUtil.GHGL+UrlUtil.getWithHas(this);

        } else if ("2".equals(tag)) {
            mUrl =UrlUtil.FATHER_HTML + UrlUtil.TQZZ+UrlUtil.getWithHas(this);

        } else if ("3".equals(tag)) {
            mUrl = UrlUtil.FATHER_HTML + UrlUtil.JJZZ+UrlUtil.getWithHas(this);

        }
        mShykTitleTitle.setText("组织架构图");
        mShykXz.setVisibility(View.GONE);
//        设置支持js
        WebViewUtils.setWebView(mDialog,mWeb, mUrl, ZzjgtActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mShykTitleTitle.setText(title);
            }

        };
        mWeb.setWebChromeClient(wvcc);
    }

    @OnClick(R.id.shyk_left_back)
    public void onViewClicked() {
//                判断webview是否可以
        if (mWeb.canGoBack()) {
            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWeb.goBack();
        } else {
            finish();
        }    }
}