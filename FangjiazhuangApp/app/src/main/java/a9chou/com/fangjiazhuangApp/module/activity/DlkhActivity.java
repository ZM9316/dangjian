package a9chou.com.fangjiazhuangApp.module.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dongchengcheng on 2017/12/20.
 * 党章党规
 */

public class DlkhActivity extends BaseActivity {
    @BindView(R.id.shyk_left_back)
    TextView shykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView shykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView shykXz;
    @BindView(R.id.web)
    WebView web;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_oragization_service;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(DlkhActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        shykTitleTitle.setText("党廉考核");
        shykXz.setVisibility(View.GONE);
        String url = UrlUtil.FATHER_HTML + UrlUtil.PARTY_ASSESSMENT + UrlUtil.getWithNo(this);
        Log.i("url",url);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, web, url, DlkhActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
                shykTitleTitle.setText(title);
            }

        };
        web.setWebChromeClient(wvcc);
    }

    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
