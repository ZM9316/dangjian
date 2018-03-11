package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class MyWzActivity extends BaseActivity {

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
    @BindView(R.id.wz_content)
    TextView mWzContent;
    @BindView(R.id.activity_my_wz)
    LinearLayout mActivityMyWz;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_my_wz;
    }

    @Override
    protected void initViews() {
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String content = i.getStringExtra("content");
        mBigEventsTitleTitle.setText(title);
        mWzContent.setText("       " + content);
        mBigEventsSearchIv.setVisibility(View.GONE);
    }


    @OnClick(R.id.big_events_left_back)
    public void onViewClicked() {
        finish();
    }
}