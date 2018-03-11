package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityCorporateCulture extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.ddjt)
    TextView mddjt;
    @BindView(R.id.whxg)
    TextView mwhxg;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_corporate_culture;
    }

    @Override
    protected void initViews() {


        mShykTitleTitle.setText(getIntent().getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.ddjt, R.id.whxg})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.ddjt://道德讲堂
                jump(ActivitySubjectH5.class,"道德讲堂");
                break;
            case R.id.whxg://文化宣贯
                jump(ActivitySubjectH5.class,"文化宣贯");
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityCorporateCulture.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityCorporateCulture.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}