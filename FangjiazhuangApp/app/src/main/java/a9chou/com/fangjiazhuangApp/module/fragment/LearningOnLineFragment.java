package a9chou.com.fangjiazhuangApp.module.fragment;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.SoftKeyboardUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date: 2017/9/25.
 * author: 王艺凯 (lenovo )
 * function:
 */
public class LearningOnLineFragment extends BaseFragment {

    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.search_abolish)
    TextView mSearchAbolish;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.tool_bar)
    RelativeLayout mToolBar;
    @BindView(R.id.web)
    WebView mWeb;
    Unbinder unbinder;
    @BindView(R.id.jiazai)
    Button mJiazai;
    @BindView(R.id.no_net_linear)
    LinearLayout mNoNetLinear;
    @BindView(R.id.web_linear)
    LinearLayout mWebLinear;
    private String mUrl;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_lineaningonline;
    }


    @Override
    protected void initViews() {

        mTitleTitle.setText("在线学习");
        mSearch.setVisibility(View.GONE);
        mTitleLd.setVisibility(View.GONE);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mUrl = UrlUtil.FATHER_HTML + UrlUtil.LEARNONLINE + UrlUtil.getWithNo(getActivity());
        if (!NetUtil.isNetworkAvailable(getContext())) {
            mDialog.dismiss();
            mWebLinear.setVisibility(View.GONE);
            mNoNetLinear.setVisibility(View.VISIBLE);
        } else {
            WebOnLine();
            mWebLinear.setVisibility(View.VISIBLE);
            mNoNetLinear.setVisibility(View.GONE);
        }
    }

    private void WebOnLine() {
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, mUrl, getContext(), "android");

        //         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitleTitle.setText(title);
                if (mWeb.canGoBack()) {
                    mTitleLeftIv.setImageResource(R.drawable.left_back);
                    mTitleLeftIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                            mWeb.goBack();
                        }
                    });
                } else {
                    mTitleLeftIv.setImageResource(R.drawable.dh);
                }
            }

        };
        mWeb.setWebChromeClient(wvcc);
//        设置软键盘相关
        SoftKeyboardUtil.setSoftKeyboard(getContext(), mBigEventsSearchEdit, mWeb, "Search");
    }

    @OnClick({R.id.jiazai, R.id.title_ld, R.id.search, R.id.search_abolish, R.id.title_left_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiazai:
                if (NetUtil.isNetworkAvailable(getActivity())) {
                    WebOnLine();
                    mWebLinear.setVisibility(View.VISIBLE);
                    mNoNetLinear.setVisibility(View.GONE);
                } else {
                    ToastUtils.showToast("当前网络不可用,请检查网络");
                }

                break;
            case R.id.title_ld:
                ToastUtils.showToast("消息");
                break;
            case R.id.search:
                SearchUtil.showSearch(mBigEventsSearchEdit,getActivity(), mBigEventsTitleRelative, mSearchLinear);
                break;
            case R.id.search_abolish:
                SearchUtil.abolishSearch(getActivity(), mBigEventsSearchEdit, mBigEventsTitleRelative, mSearchLinear);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mWeb.reload();
    }

}
