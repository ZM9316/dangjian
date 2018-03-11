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

public class ActivityTradeUnionManagement extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zhfgh)
    TextView mzhfgh;
    @BindView(R.id.jyfgh)
    TextView mjyfgh;
    @BindView(R.id.yxfgh)
    TextView myxfgh;
    @BindView(R.id.sbfgh)
    TextView msbfgh;
    @BindView(R.id.gcfgh)
    TextView mgcfgh;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_tradeunion_mangerment;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.zhfgh, R.id.jyfgh, R.id.yxfgh, R.id.sbfgh, R.id.gcfgh})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zhfgh://综合分工会
                jump(ActivityBranchContent.class,"综合分工会");
                break;
            case R.id.jyfgh://经营分工会
                jump(ActivityBranchContent.class,"经营分工会");
                break;
            case R.id.yxfgh://运行分工会
                jump(ActivityBranchContent.class,"运行分工会");
                break;
            case R.id.sbfgh://设备分工会
                jump(ActivityBranchContent.class,"设备分工会");
                break;
            case R.id.gcfgh://工程分工会
                jump(ActivityBranchContent.class,"工程分工会");
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityTradeUnionManagement.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityTradeUnionManagement.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}