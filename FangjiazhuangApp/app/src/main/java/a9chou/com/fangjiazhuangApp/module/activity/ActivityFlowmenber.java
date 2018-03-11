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

public class ActivityFlowmenber extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.xxlb)
    TextView mxxlb;
    @BindView(R.id.tjfx)
    TextView mtjfx;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_flow_menber;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.xxlb, R.id.tjfx})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.xxlb://信息列表
//                jump(MemberActivesActivity.class);
                if("流动党员".equals(i)){
                    jump(ActivitySubjectH5.class,"流动信息列表");
                }else if("积极分子".equals(i)){
                    jump(ActivitySubjectH5.class,"积极信息列表");
                }

                break;
            case R.id.tjfx://统计分析
//                jump(MemberStatisticsActivity.class);
                ToastUtils.showToast("点击了统计分析！！！");
                break;
            case R.id.shyk_left_back://返回
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityFlowmenber.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityFlowmenber.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}