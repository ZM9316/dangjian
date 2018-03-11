package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity {
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dzzgl)
    TextView mDzzgl;
    @BindView(R.id.ghgzgl)
    TextView mGhgzgl;
    @BindView(R.id.tqgzgl)
    TextView mTqgzgl;
    @BindView(R.id.jjjcgl)
    TextView mJjjcgl;
    @BindView(R.id.activity_more)
    LinearLayout mActivityMore;
    @BindView(R.id.qywh)
    TextView mQywh;
    @BindView(R.id.dt)
    TextView mDt;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_more;
    }

    @Override
    protected void initViews() {
        mShykTitleTitle.setText("更多");
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.dt, R.id.shyk_left_back, R.id.dzzgl, R.id.ghgzgl, R.id.tqgzgl, R.id.jjjcgl, R.id.qywh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dt:
                ToastUtils.showToast("敬请期待");
                break;
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.dzzgl:
                jumpNext(DzzglActivity.class);
                break;
            case R.id.ghgzgl:
                jumpNext(GhgzglActivity.class);
                break;
            case R.id.tqgzgl:
                jumpNext(TqgzglActivity.class);

                break;
            case R.id.jjjcgl:
                jumpNext(JjjcglActivity.class);
                break;
            case R.id.qywh:
                jumpNext(QywhActivity.class);

                break;
        }
    }

    private void jumpNext(Class c) {
        Intent intent = new Intent(MoreActivity.this, c);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}