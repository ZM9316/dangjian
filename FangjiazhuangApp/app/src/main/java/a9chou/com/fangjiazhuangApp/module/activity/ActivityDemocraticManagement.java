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

public class ActivityDemocraticManagement extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zdh)
    TextView mzdh;
    @BindView(R.id.qybz)
    TextView mqybz;
    @BindView(R.id.cwgk)
    TextView mcwgk;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_democratic_management;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.zdh, R.id.qybz, R.id.cwgk})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.zdh://职代会
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"职代会");
                break;
            case R.id.qybz://权益保障
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"权益保障");
                break;
            case R.id.cwgk://权益保障
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"厂务公开");
                break;
            case R.id.shyk_left_back://返回
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityDemocraticManagement.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityDemocraticManagement.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}