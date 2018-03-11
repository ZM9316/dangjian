package a9chou.com.fangjiazhuangApp.module.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import java.util.ArrayList;
import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.FragmentViewPagerAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.fragment.ActivityReadFragment;
import a9chou.com.fangjiazhuangApp.module.fragment.ActivityUnreadFragment;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityNoticeBulletin extends BaseActivity {

    private static final String TAG = "MyXXActivity";
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
    //    @BindView(R.id.activity_big_events)
//    LinearLayout mActivityBigEvents;
    private LoadingDailog mDialog;
    private List<Fragment> mFragmentList;
    private List<String> mTitles;
    private FragmentViewPagerAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_xx;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityNoticeBulletin.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mBigEventsTitleTitle.setText("通知公告");
        mBigEventsSearchIv.setVisibility(View.GONE);
//        initFragments();
//        initTitle();
//        mAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles);
//        mMainVp.setAdapter(mAdapter);
//        mMainTab.setupWithViewPager(mMainVp);
       String url = UrlUtil.FATHER_HTML + UrlUtil.MYXX + UrlUtil.getWithNo(this);
////        String url = UrlUtil.FATHER_HTML + UrlUtil.XJTX + UrlUtil.getWithNo(this);
       //        设置支持js
       WebViewUtils.setWebView(mDialog, mWeb, url, ActivityNoticeBulletin.this, "android");
////         获取webview中的标题，然后传到我的title中
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




    @JavascriptInterface
    public void writeE() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast("aaa");
//                finish();
            }
        });
    }

}