package a9chou.com.fangjiazhuangApp.module.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.android.tu.loadingdialog.LoadingDailog;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;

/**
 * Created by zhang on 2018/3/6.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.webX)
    WebView mwebX;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.test;
    }

    @Override
    protected void initViews() {

//        String url="https://docs.google.com/viewer?url=http://10.24.64.40:8181/treps/static/mobile/wp/test.xlsx";
        String url="https://view.officeapps.live.com/op/view.aspx?src=http://www.excelcn.com/down/moban/6/625625.doc";
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(TestActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
        WebViewUtils.setWebView(mDialog, mwebX, url, TestActivity.this, "android");
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }

        };
        mwebX.setWebChromeClient(wvcc);


    }
}
