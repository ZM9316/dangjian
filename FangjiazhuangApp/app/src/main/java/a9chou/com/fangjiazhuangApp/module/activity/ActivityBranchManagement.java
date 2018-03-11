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

public class ActivityBranchManagement extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zhdzb)
    TextView mzhdzb;
    @BindView(R.id.jydzb)
    TextView mjydzb;
    @BindView(R.id.yxdzb)
    TextView myxdzb;
    @BindView(R.id.sbdzb)
    TextView msbdzb;
    @BindView(R.id.gcdzb)
    TextView mgcdzb;
    private String i="";
    private String j="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_branch_mangerment;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.zhdzb, R.id.jydzb, R.id.yxdzb, R.id.sbdzb, R.id.gcdzb})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zhdzb://综合党支部
                jump(ActivityBranchContent.class,"综合党支部");
                break;
            case R.id.jydzb://经营党支部
                jump(ActivityBranchContent.class,"经营党支部");
                break;
            case R.id.yxdzb://运行党支部
                jump(ActivityBranchContent.class,"运行党支部");
                break;
            case R.id.sbdzb://设备党支部
                jump(ActivityBranchContent.class,"设备党支部");
                break;
            case R.id.gcdzb://工程党支部
                jump(ActivityBranchContent.class,"工程党支部");
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityBranchManagement.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityBranchManagement.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}