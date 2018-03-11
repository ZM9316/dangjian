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

public class ActivityBranchContent extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zzjg)
    TextView mzzjg;
    @BindView(R.id.zrqd)
    TextView mzrqd;
    @BindView(R.id.dyfz)
    TextView mdyfz;
    @BindView(R.id.dwgk)
    TextView mdwgk;
    @BindView(R.id.shyk)
    TextView mshykp;
    @BindView(R.id.jjfz)
    TextView mjjfz;
    @BindView(R.id.dyxxt)
    TextView mdyxxt;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_branch_content;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
        if(i.endsWith("分工会")){
            mzzjg.setText("工会委员");
            mshykp.setText("两会一课");
            mdwgk.setText("业务公开");
            mdyfz.setVisibility(View.INVISIBLE);
            mjjfz.setVisibility(View.INVISIBLE);
            mdyxxt.setVisibility(View.INVISIBLE);
        }else if(i.endsWith("党支部")){
            mzzjg.setText("支部委员");
            mshykp.setText("三会一课");
            mdwgk.setText("业务公开");
            mdyfz.setVisibility(View.VISIBLE);
            mjjfz.setVisibility(View.VISIBLE);
            mdyxxt.setVisibility(View.VISIBLE);
        }else if(i.endsWith("团支部")){
            mzzjg.setText("支部委员");
            mshykp.setText("三会一课");
            mdwgk.setVisibility(View.INVISIBLE);
            mdyfz.setVisibility(View.INVISIBLE);
            mjjfz.setVisibility(View.INVISIBLE);
            mdyxxt.setVisibility(View.INVISIBLE);
        }

    }
    @OnClick({R.id.shyk_left_back, R.id.zzjg, R.id.zrqd, R.id.dyfz, R.id.dwgk, R.id.shyk, R.id.jjfz, R.id.dyxxt})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zzjg://组织机构
                jump(ActivitySubjectH5.class,i+"组织机构",i);
                break;
            case R.id.jjfz://积极分子
                jump(ActivitySubjectH5.class,"支部积极分子",i);
                break;
            case R.id.dyxxt://党员信息
                jump(ActivitySubjectH5.class,"支部党员信息",i);
                break;
            case R.id.zrqd://责任清单
                jump(ActivitySubjectH5.class,"责任清单",i);
                break;
            case R.id.dyfz://党员发展
                jump(ActivitySubjectH5.class,"党员发展",i);
                break;
            case R.id.dwgk://党务公开
                jump(ActivitySubjectH5.class,"管理党务公开",i);
                break;
            case R.id.shyk://两会一课
                if(i.endsWith("分工会")){
                    jump(ActivityTwoMeeting.class, mshykp.getText().toString(),i);
                }else{
                    jump(ActivityThreeMeeting.class, mshykp.getText().toString(),i);
                }
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityBranchContent.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityBranchContent.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
    private void jump(Class c,String Tag,String tit) {
        Intent intent = new Intent(ActivityBranchContent.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}