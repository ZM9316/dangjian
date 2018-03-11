package a9chou.com.fangjiazhuangApp.module.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.tu.loadingdialog.LoadingDailog;
import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.DrawableTextUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

public class ActivityComprehensiveH5 extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.activity_dyxx)
    LinearLayout mActivityDyxx;
    private LoadingDailog mDialog;
    private String i="";
    private String j="";
    private  String url="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dyxx;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityComprehensiveH5.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        i=getIntent().getStringExtra("tit");
        j=getIntent().getStringExtra("bftit");
        switch (i){
            case "党委委员":
                url = UrlUtil.FATHER_HTML + UrlUtil.PGO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "纪委委员":
                url = UrlUtil.FATHER_HTML + UrlUtil.DIO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "工会委员":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "职能部室":
                if("党建政工".equals(j)){
                    url = UrlUtil.FATHER_HTML + UrlUtil.PGFD + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                }else if("纪律检查".equals(j)){
                    url = UrlUtil.FATHER_HTML + UrlUtil.DIFD + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                }
                break;
            case "基层组织":
                if("党建政工".equals(j)){
                    url = UrlUtil.FATHER_HTML + UrlUtil.PGGO + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                }else if("工会管理".equals(j)){
                    url = UrlUtil.FATHER_HTML + UrlUtil.GAGRO + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                }
                break;
            case "基层纪检":
                url = UrlUtil.FATHER_HTML + UrlUtil.DIGO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
        }


        mShykTitleTitle.setText(i);

        DrawableTextUtil.setTextCompoundDrawablesLeft(ActivityComprehensiveH5.this, R.drawable.screen, mShykXz, 0);
        mShykXz.setVisibility(View.GONE);
//        mShykXz.setText("筛选");
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, ActivityComprehensiveH5.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
                mShykTitleTitle.setText(title);
            }

        };
        mWeb.setWebChromeClient(wvcc);
    }

    @OnClick({R.id.shyk_left_back, R.id.shyk_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                //                判断webview是否可以
                if (mWeb.canGoBack()) {
                    mWeb.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.shyk_xz:
                ToastUtils.showToast("筛选");
                View popupView = ActivityComprehensiveH5.this.getLayoutInflater().inflate(R.layout.popupwindow, null);
                //   创建PopupWindow对象，指定宽度和高度
                PopupWindow window = new PopupWindow(popupView, 300, 400);
                //  设置动画
                window.setAnimationStyle(R.style.popup_window_anim);
                //   设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                //   设置可以获取焦点
                window.setFocusable(true);
                //  设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                //  更新popupwindow的状态
                window.update();
                //  以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(mShykXz, 0, 20);
                break;
        }
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