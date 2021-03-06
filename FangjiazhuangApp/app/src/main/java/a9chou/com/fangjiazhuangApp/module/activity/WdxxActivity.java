package a9chou.com.fangjiazhuangApp.module.activity;

import android.view.View;
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
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class WdxxActivity extends BaseActivity {

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
        return R.layout.activity_wdxx;
    }

    @Override
    protected void initViews() {

        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(WdxxActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mBigEventsTitleTitle.setText("我的消息");
        mBigEventsSearchIv.setVisibility(View.GONE);

        String url = UrlUtil.FATHER_HTML + UrlUtil.WDXX + UrlUtil.getWithNo(this);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, WdxxActivity.this, "android");
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
        //                判断webview是否可以
        if (mWeb.canGoBack()) {
            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWeb.goBack();
        } else {
            finish();
        }
    }
}