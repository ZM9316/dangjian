package a9chou.com.fangjiazhuangApp.module.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.tu.loadingdialog.LoadingDailog;
import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.activity.ActivitySubjectH5;
import a9chou.com.fangjiazhuangApp.module.activity.DjkhActivity;
import a9chou.com.fangjiazhuangApp.utils.DrawableTextUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class MemorabiliaFragment extends BaseFragment {

    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.shyk_left_back)
    TextView mshyk_left_back;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.web)
    WebView mWeb;
    private LoadingDailog mDialog;


    @Override
    protected int attachLayoutRes() {
        return R.layout.memora_bilia_fragment;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        String url = UrlUtil.FATHER_HTML + UrlUtil.BIG_EVENTS + UrlUtil.getWithHas(getActivity());
        mTitleLd.setVisibility(View.GONE);
        mSearch.setVisibility(View.GONE);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, getActivity(), "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
                mTitleTitle.setText(title);
                if("大事记详情".equals(title)){
                    mshyk_left_back.setVisibility(View.VISIBLE);
                    mTitleLeftIv.setVisibility(View.GONE);
                }
            }

        };
        mWeb.setWebChromeClient(wvcc);
    }

    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                //判断webview是否可以
                if (mWeb.canGoBack()) {
                    mshyk_left_back.setVisibility(View.GONE);
                    mTitleLeftIv.setVisibility(View.VISIBLE);
                    mWeb.goBack();
                }
                break;
        }
    }

}