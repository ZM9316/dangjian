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

public class ActivityEffectivenessmonitor extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.lxxm)
    TextView mlxxm;
    @BindView(R.id.jcjy)
    TextView mjcjy;
    @BindView(R.id.zgwc)
    TextView mzgwc;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_effectiveness_monitor;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.lxxm, R.id.jcjy, R.id.zgwc})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.lxxm://立项项目
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"立项项目");
                break;
            case R.id.jcjy://监察建议
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"监察建议");
                break;
            case R.id.zgwc://整改完成
//                jump(MemberStatisticsActivity.class);
                jump(ActivitySubjectH5.class,"整改完成");
                break;
            case R.id.shyk_left_back://返回
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityEffectivenessmonitor.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityEffectivenessmonitor.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}