package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
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

public class DjdtDetailsActivity extends BaseActivity {

    private static final String TAG = "DjdtDetailsActivity";
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
    private String mDjdtId;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_djdt_details;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(DjdtDetailsActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();


        Intent intent = getIntent();
        mDjdtId = intent.getStringExtra("djdtId");

        String url =UrlUtil.FATHER_HTML+ UrlUtil.DJDT + UrlUtil.getWithNo(this);
         mBigEventsTitleTitle.setText("详 情");
//        隐藏按钮
        mBigEventsSearchIv.setVisibility(View.GONE);
        WebViewUtils.setWebView(mDialog,mWeb, url, this, "android", "SDeedsDetial", mDjdtId);
    }

    @OnClick(R.id.big_events_left_back)
    public void onViewClicked() {
        finish();
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