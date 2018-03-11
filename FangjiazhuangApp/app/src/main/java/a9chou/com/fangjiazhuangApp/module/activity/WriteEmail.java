package a9chou.com.fangjiazhuangApp.module.activity;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class WriteEmail extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_write_email;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(WriteEmail.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
         String url = UrlUtil.FATHER_HTML + UrlUtil.XJTX + UrlUtil.getWithNo(this);
        mShykXz.setVisibility(View.GONE);
//        设置支持js
        WebViewUtils.setWebView(mDialog,mWeb, url, WriteEmail.this, "android");
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
        finish();
    }
    @JavascriptInterface
    public void writeE() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 finish();
            }
        });
    }

}