package a9chou.com.fangjiazhuangApp.module.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dongchengcheng on 2017/12/5.
 */

public class ActivityTuanwei extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.tuanweipic)
    ImageView ztuanweipic;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_tuanwei_pic;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("组织结构图");
        mShykXz.setVisibility(View.GONE);
//
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
        }
    }

    public void onViewClicked() {
        finish();
    }
}
