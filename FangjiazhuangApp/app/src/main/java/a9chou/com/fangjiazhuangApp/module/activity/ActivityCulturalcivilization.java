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

public class ActivityCulturalcivilization extends BaseActivity {

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
    @BindView(R.id.mztj)
    TextView mmztj;
    @BindView(R.id.gjgr)
    TextView mgjgr;
    @BindView(R.id.zhzl)
    TextView mzhzl;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_cultural_civilization;
    }

    @Override
    protected void initViews() {


        mShykTitleTitle.setText(getIntent().getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.mztj, R.id.gjgr, R.id.zhzl,R.id.ddjt, R.id.whxg})
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
            case R.id.mztj://民族团结
                jump(ActivitySubjectH5.class,"民族团结");
                break;
            case R.id.gjgr://共建共荣
                jump(ActivitySubjectH5.class,"共建共荣");
                break;
            case R.id.zhzl://综合治理
                jump(ActivitySubjectH5.class,"综合治理");
                break;
//            case R.id.qywh://企业文化
//                jump(ActivityCorporateCulture.class,"企业文化");
//                break;
//            case R.id.wmcj://文明创建
//                jump(ActivityCivilizationCreation.class,"文明创建");
//                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityCulturalcivilization.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityCulturalcivilization.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}