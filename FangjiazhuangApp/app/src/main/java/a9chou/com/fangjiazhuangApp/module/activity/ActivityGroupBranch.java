package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityGroupBranch extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zhtzb)
    TextView mzhtzb;
    @BindView(R.id.jytzb)
    TextView mjytzb;
    @BindView(R.id.gctzb)
    TextView mgctzb;
    @BindView(R.id.yxtzb)
    TextView myxtzb;
    @BindView(R.id.sbtzb)
    TextView msbtzb;
//    @BindView(R.id.tzbpm)
//    TextView mtzbpm;
    //    @BindView(R.id.kscg)
//    TextView mkscg;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_group_branch;
    }

    @Override
    protected void initViews() {
        Intent i=getIntent();
        mShykTitleTitle.setText(i.getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);
        mjytzb.setVisibility(View.INVISIBLE);
        mgctzb.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.shyk_left_back, R.id.zhtzb, R.id.jytzb, R.id.gctzb,R.id.yxtzb,R.id.sbtzb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zhtzb:
                jump(ActivityBranchContent.class,"综合团支部");//综合团支部
                break;
            case R.id.jytzb:
                jump(ActivityBranchContent.class,"经营团支部");//经营团支部
                break;
            case R.id.gctzb:
                jump(ActivityBranchContent.class,"工程团支部");//工程团支部
                break;
            case R.id.yxtzb:
                jump(ActivityBranchContent.class,"运行团支部");//运行团支部
                break;
            case R.id.sbtzb:
                jump(ActivityBranchContent.class,"设备团支部");//设备团支部
                break;
//            case R.id.tzbpm:
//                jump(ActivityShareUI.class,"支部排名");//团支部排名
//                break;
        }
    }
    private void jump(Class c,String tag) {
        Intent i = new Intent(ActivityGroupBranch.this, c);
        i.putExtra("tit", tag);
        startActivity(i);
//        finish();
    }
}