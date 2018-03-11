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

public class ActivityQualityImprovement extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.cxcx)
    TextView mcxcx;
    @BindView(R.id.jjjs)
    TextView mjjjs;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.actvity_quality_improvement;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.cxcx, R.id.jjjs})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.cxcx://创新创效
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"创新创效");
                break;
            case R.id.jjjs://竞技竞赛
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"竞技竞赛");
                break;
            case R.id.shyk_left_back://返回
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityQualityImprovement.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityQualityImprovement.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}