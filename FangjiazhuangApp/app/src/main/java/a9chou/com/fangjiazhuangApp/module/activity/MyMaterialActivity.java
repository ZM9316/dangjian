package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

public class MyMaterialActivity extends BaseActivity {

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
    private int x = 0;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_material;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(MyMaterialActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mBigEventsSearchIv.setVisibility(View.GONE);
        String url = UrlUtil.FATHER_HTML + UrlUtil.GRXX + UrlUtil.getWithNo(this);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, MyMaterialActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mBigEventsTitleTitle.setText(title);
            }
        };
        mWeb.setWebChromeClient(wvcc);
        if(getWindow().getAttributes().softInputMode==WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
        //隐藏软键盘
            mWeb.loadUrl("javascript:javacalljs()");
        }
    }



    @OnClick(R.id.big_events_left_back)
    public void onViewClicked() {
//                判断webview是否可以
        if (mWeb.canGoBack()) {
//            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWeb.goBack();
        } else {
            goBack();
        }
    }

    private void goBack() {
//        Intent intent = new Intent(MyMaterialActivity.this, MineFragment.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        finish();

    }


    //个人资料
    @JavascriptInterface
    public void backPersonalData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish();
                ToastUtils.showToast("修改成功");
//                mWeb.goBack();
//                if (!mWeb.canGoBack()) {
//                    mWeb.reload();
//                }

            }
        });
    }
}