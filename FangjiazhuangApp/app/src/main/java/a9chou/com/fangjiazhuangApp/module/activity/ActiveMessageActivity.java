package a9chou.com.fangjiazhuangApp.module.activity;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * date: 2017/12/6.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class ActiveMessageActivity extends BaseActivity {
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.activity_xz)
    LinearLayout mActivityXz;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    private LoadingDailog mDialog;
    private String activeTitle;
    private String activeId;
    private String createby;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_xz;
    }

    @Override
    protected void initViews() {
        activeTitle = getIntent().getStringExtra("activeTitle");
        activeId = getIntent().getStringExtra("activeId");
        createby = getIntent().getStringExtra("createby");
        mShykTitleTitle.setText("活动留言");
        mShykXz.setVisibility(View.GONE);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActiveMessageActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        String url = UrlUtil.FATHER_HTML + UrlUtil.HDLY + UrlUtil.getWithNo(ActiveMessageActivity.this) +
                "&Id=" + activeId + "&title=" + activeTitle + "&createBy=" + createby;

        mShykXz.setVisibility(View.GONE);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, ActiveMessageActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
                mShykTitleTitle.setText(title);
            }

            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
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
                    Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        };
        mWeb.setWebChromeClient(wvcc);
    }


    @OnClick(R.id.shyk_left_back)
    public void onViewClicked() {
        finish();
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


    @JavascriptInterface
    public void goBackActive() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                finish();
            }
        });
    }
}
