package a9chou.com.fangjiazhuangApp.module.fragment;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;

import java.util.HashMap;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityXZActivity;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.SpPermissionUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * date: 2017/9/29.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class ActivitFragment extends BaseFragment {

    private static final String TAG = "ActivityFragment";
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.activity_threee_lssons)
    LinearLayout mActivityThreeeLssons;
    Unbinder unbinder;
    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.jiazai)
    Button mJiazai;
    @BindView(R.id.no_net_linear)
    LinearLayout mNoNetLinear;
    @BindView(R.id.web_linear)
    LinearLayout mWebLinear;
    private String mToken;
    private String mUserName;
    private String mUrl;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    private Map<String, String> mMap;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initViews() {


        Bundle bundle = getArguments();
        //从activity传过来的Bundle
        if (bundle != null) {
            mToken = (String) bundle.get("token");
            mUserName = (String) bundle.get("userName");
        }


        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mMap = new HashMap<>();
        mMap.put("userName", mUserName);
        mMap.put("token", mToken);

        mTitleTitle.setText("党组织活动管理");
        mDialog.show();
        if (!NetUtil.isNetworkAvailable(getActivity())) {
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
        if (SpPermissionUtil.returnY(getActivity()) == 1) {
            mShykXz.setVisibility(View.VISIBLE);
        } else {
            mShykXz.setVisibility(View.GONE);
        }

        mUrl = UrlUtil.FATHER_HTML + UrlUtil.HDGL + UrlUtil.getWithNo(getActivity()) + "&wp=" + SpPermissionUtil.getActivesPermission(getActivity());
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, mUrl, getContext(), "android");
        //         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
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
                    mShykXz.setVisibility(View.GONE);
                } else {
                    mTitleLeftIv.setImageResource(R.drawable.dh);
                    if (SpPermissionUtil.returnY(getActivity()) == 1) {
                        mShykXz.setVisibility(View.VISIBLE);
                    } else {
                        mShykXz.setVisibility(View.GONE);
                    }
                }
            }

            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(getActivity(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        };
        mWeb.setWebChromeClient(wvcc);
    }

    @OnClick({R.id.jiazai, R.id.shyk_xz})
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
            case R.id.shyk_xz:
                Intent intent = new Intent(getActivity(), ActivityXZActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    /**
     * 别通过new对象拿到Fragment了，直接调用我就行了
     */
    public static ActivitFragment newActivityFragment(String userName, String value) {
        //将fragment绑定参数
        Bundle bundle = new Bundle();
        bundle.putString("token", value);
        bundle.putString("userName", userName);
        ActivitFragment fragment = new ActivitFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            ToastUtils.showToast("上传图片失败");
    }

    public boolean onBackPressed() {
        if (mWeb.canGoBack()) {
            mWeb.goBack();
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mWeb.reload();
    }

}
