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

public class ActivityManagementYouthLeague extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.tyxx)
    TextView mtyxx;
    @BindView(R.id.qnxx)
    TextView mqnxx;
    @BindView(R.id.tqty)
    TextView mtqty;
    private String i="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_management_youth_league;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.tyxx, R.id.qnxx, R.id.tqty})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tyxx://团员信息
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"团员信息");
                break;
            case R.id.qnxx://青年信息
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"青年信息");
                break;
            case R.id.tqty://团青推优
//                jump(MemberStatisticsActivity.class);
                jump(ActivitySubjectH5.class,"团青推优");
                break;
            case R.id.shyk_left_back://返回
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityManagementYouthLeague.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityManagementYouthLeague.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}