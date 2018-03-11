package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class SetPassActivity extends BaseActivity {

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
    @BindView(R.id.activity_big_events)
    LinearLayout mActivityBigEvents;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_set_pass;
    }

    @Override
    protected void initViews() {
        mBigEventsTitleTitle.setText("修改密码");
        mBigEventsSearchIv.setVisibility(View.GONE);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(SetPassActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();

        String url = UrlUtil.FATHER_HTML + UrlUtil.SETPASSWORD + UrlUtil.getWithNo(this);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, SetPassActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mBigEventsTitleTitle.setText(title);
            }
        };
        mWeb.setWebChromeClient(wvcc);

    }

    @OnClick(R.id.big_events_left_back)
    public void onViewClicked() {
        finish();
    }

    //修改密码
    @JavascriptInterface
    public void setPassWord() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                finish();
                ToastUtils.showToast("修改成功");
                startActivity(new Intent(SetPassActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }


}