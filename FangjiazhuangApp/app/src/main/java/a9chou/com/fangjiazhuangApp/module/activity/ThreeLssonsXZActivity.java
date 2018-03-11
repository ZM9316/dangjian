package a9chou.com.fangjiazhuangApp.module.activity;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.SpPermissionUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ThreeLssonsXZActivity extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;

    @BindView(R.id.activity_threee_lssons)
    LinearLayout mActivityThreeeLssons;
    @BindView(R.id.web)
    WebView mWeb;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_three_lssons_xz;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ThreeLssonsXZActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        String url = UrlUtil.FATHER_HTML + UrlUtil.SHYKXZ + UrlUtil.getWithNo(this) + "&mmt=" + SpPermissionUtil.getThreeLssonsMtPermission(ThreeLssonsXZActivity.this);

        mShykXz.setVisibility(View.GONE);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, ThreeLssonsXZActivity.this, "android");
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
    public void goBackActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast("跳转");
                finish();
            }
        });
    }


}