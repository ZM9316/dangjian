package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityOrganization_construction extends BaseActivity {


    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zzjg)
    TextView mzzjg;
    @BindView(R.id.znbs)
    TextView mznbs;
    @BindView(R.id.jczz)
    TextView mjczz;
    private String i="";
    private String j="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organization_construction;
    }

    @Override
    protected void initViews() {
        j=getIntent().getStringExtra("bftit");
        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
        if("工会管理".equals(j)){
            mznbs.setVisibility(View.INVISIBLE);
        }
        if("党建政工".equals(j)){
            mzzjg.setText("党委委员");
        }else if("纪律检查".equals(j)){
            mzzjg.setText("纪委委员");
            mjczz.setText("基层纪检");
        }else if("工会管理".equals(j)){
            mzzjg.setText("工会委员");
        }
    }

    @OnClick({R.id.shyk_left_back, R.id.zzjg, R.id.znbs, R.id.jczz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zzjg://组织机构
                if("党建政工".equals(j)){
                    jump(ActivityComprehensiveH5.class,"党委委员",j);
                }else if("纪律检查".equals(j)){
                    jump(ActivityComprehensiveH5.class,"纪委委员",j);
                }else{
                    jump(ActivityComprehensiveH5.class,"工会委员","工会管理");
                }
                break;
            case R.id.znbs://职能部室
                if("党建政工".equals(j)){
                    jump(ActivityComprehensiveH5.class,"职能部室",j);
                }else if("纪律检查".equals(j)){
                    jump(ActivityComprehensiveH5.class,"职能部室",j);
                }else{
                    jump(ActivityComprehensiveH5.class,"职能部室","工会管理");
                }

                break;
            case R.id.jczz://基层组织
                if("党建政工".equals(j)){
                    jump(ActivityComprehensiveH5.class,"基层组织",j);
                }else if("纪律检查".equals(j)){
                    jump(ActivityComprehensiveH5.class,"基层纪检",j);
                }else{
                    jump(ActivityComprehensiveH5.class,"基层组织","工会管理");
                }

                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityOrganization_construction.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
    private void jump(Class c,String Tag,String tit) {
        Intent i = new Intent(ActivityOrganization_construction.this, c);
        i.putExtra("tit", Tag);
        i.putExtra("bftit", tit);
        startActivity(i);
    }
}