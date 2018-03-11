package a9chou.com.fangjiazhuangApp.module.fragment;

import android.content.Intent;
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
import a9chou.com.fangjiazhuangApp.module.activity.ActivitySubjectH5;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityUnderstanding;
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
public class LearningUnderstandFragment extends BaseFragment {
    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.ztjy)
    TextView mztjy;
    @BindView(R.id.spjy)
    TextView mspjy;
    @BindView(R.id.sxnd)
    TextView msxnd;
    private String i="";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_understanding;
    }


    @Override
    protected void initViews() {

//        i=getActivity().getIntent().getStringExtra("tit");
        mTitleTitle.setText("学思践悟");
        mTitleLd.setVisibility(View.GONE);
        mSearch.setVisibility(View.GONE);
    }

//    private void WebOnLine() {
//        //        设置支持js
//        WebViewUtils.setWebView(mDialog, mWeb, mUrl, getContext(), "android");
//
//        //         获取webview中的标题，然后传到我的title中
//        WebChromeClient wvcc = new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                mTitleTitle.setText(title);
//                if (mWeb.canGoBack()) {
//                    mTitleLeftIv.setImageResource(R.drawable.left_back);
//                    mTitleLeftIv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//                            mWeb.goBack();
//                        }
//                    });
//                } else {
//                    mTitleLeftIv.setImageResource(R.drawable.dh);
//                }
//            }
//
//        };
//        mWeb.setWebChromeClient(wvcc);
////        设置软键盘相关
//        SoftKeyboardUtil.setSoftKeyboard(getContext(), mBigEventsSearchEdit, mWeb, "Search");
//    }

    @OnClick({R.id.ztjy, R.id.spjy, R.id.sxnd})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.ztjy://专题教育
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"专题教育");
                break;
//            case R.id.xxjh://学习计划
////                jump(MemberStatisticsActivity.class);
//                jump(ActivitySubjectH5.class,"学习计划");
//                break;
            case R.id.spjy://视频教育
//                jump(MemberStatisticsActivity.class);
                jump(ActivitySubjectH5.class,"视频教育");
                break;
            case R.id.sxnd://书香宁电
//                jump(MemberStatisticsActivity.class);
                jump(ActivitySubjectH5.class,"书香宁电");
                break;
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mWeb.reload();
//    }
    private void jump(Class c) {
        Intent i = new Intent(getActivity(), c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(getActivity(), c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}
