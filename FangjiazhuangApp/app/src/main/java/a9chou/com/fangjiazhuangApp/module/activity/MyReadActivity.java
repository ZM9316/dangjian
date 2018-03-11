package a9chou.com.fangjiazhuangApp.module.activity;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;

import org.litepal.crud.DataSupport;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.db.BookList;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

public class MyReadActivity extends BaseActivity {

    private static final String TAG = "MyReadActivity";
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
    private List<BookList> mBookLists;
    private String mUrl;
    private String mId;
    private BookList mBookList;
    private String mRecordId;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_read;
    }

    @Override
    protected void initViews() {
        mBigEventsTitleTitle.setText("我阅读的书籍");
        mBigEventsSearchIv.setVisibility(View.GONE);


        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(MyReadActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
        //        查。
        mBookLists = DataSupport.findAll(BookList.class);
        String url = UrlUtil.FATHER_HTML + UrlUtil.READ + UrlUtil.getWithNo(this);
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, MyReadActivity.this, "android");
//         获取webview中的标题，然后传到我的title中
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
        finish();

    }

    //在线学习 跳转 阅读角界面
    @JavascriptInterface
    public void readingCorner(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] a = str.split(",");
                mUrl = a[0];
                mId = a[2];
                mRecordId = a[1];
                Log.d(TAG, "run: id   ==>" + mUrl + "  " + mId);
                mBookList = new BookList();
                mBookList.setBookpath(IP+"/treps" + mUrl);

                if (mBookLists.size() == 0) {
                    mBookList.save();
                } else {
                    boolean isSave = false;
                    for (int i = 0; i < mBookLists.size(); i++) {

                        if (mBookLists.get(i).getBookpath().equals(mBookList.getBookpath())) {
                            isSave = true;
                            mBookList = mBookLists.get(i);
                        }
                    }
                    if (!isSave) {
                        mBookList.save();
                    }
                }
                ReadActivity.openBook(mBookList, MyReadActivity.this, mId, mRecordId);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mWeb.reload();
    }
}