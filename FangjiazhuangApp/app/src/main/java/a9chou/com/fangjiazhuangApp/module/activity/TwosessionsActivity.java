package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zm9316 on 2017/12/28.
 */

public class TwosessionsActivity extends BaseActivity{

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dxzh)
    TextView mdxzh;
    @BindView(R.id.zbwyh)
    TextView mzbwyh;
    @BindView(R.id.zbdyh)
    TextView mzbdyh;
    @BindView(R.id.wdkfb)
    TextView mwdkfb;
    @BindView(R.id.activity_threee_lssons)
    LinearLayout mActivityThreeeLssons;
    Unbinder unbinder;
    Unbinder unbinder1;
    private LoadingDailog mDialog;
    private String mUrl;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_two_sessions;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(TwosessionsActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mShykTitleTitle.setText(getIntent().getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);

//        if (SpPermissionUtil.returnX(getActivity()) == 1) {
//            mShykXz.setVisibility(View.VISIBLE);
//        } else {
//            mShykXz.setVisibility(View.GONE);
//        }
//        mUrl =
//                UrlUtil.FATHER_HTML + UrlUtil.SHYK +
//                        UrlUtil.getWithNo(getActivity()) +
//                        "&wp=" + SpPermissionUtil.getThreeLssonsWpPermission(getActivity()) +
//                        "&mt=" + SpPermissionUtil.getThreeLssonsMtPermission(getActivity());
//
//        Log.d(TAG, "initViews:murl" + mUrl);

//        //        设置支持js
//        WebViewUtils.setWebView(mDialog, mWeb, mUrl, getActivity(), "android");
//        //         获取webview中的标题，然后传到我的title中
//        WebChromeClient wvcc = new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                mShykTitleTitle.setText(title);
//                if (mWeb.canGoBack()) {
//                    mShykXz.setVisibility(View.GONE);
//                } else {
//                    if (SpPermissionUtil.returnX(getActivity()) == 1) {
//                        mShykXz.setVisibility(View.VISIBLE);
//                    } else {
//                        mShykXz.setVisibility(View.GONE);
//                    }
//                }
//            }
//        };
//        mWeb.setWebChromeClient(wvcc);
    }

    @OnClick({R.id.shyk_left_back, R.id.dxzh,R.id.zbwyh,R.id.zbdyh,R.id.wdkfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                jump(MainActivity.class);
                break;
            case R.id.dxzh:
                jump(PartygroupActivity.class);//党小组会
                break;
            case R.id.zbwyh:
                jump(PartygroupActivity.class);//支部委员会
                break;
            case R.id.zbdyh:
                jump(PartygroupActivity.class);//支部党员大会
                break;
//            case R.id.wdk:
//                jump(PartygroupActivity.class);//微党课
//                break;
            case R.id.wdkfb:
                jump(PartygroupActivity.class);//微党课发布
                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(TwosessionsActivity.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }

}
