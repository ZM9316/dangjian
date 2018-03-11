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

public class ActivityPartymemberList extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dylb)
    TextView mdylb;
    @BindView(R.id.tjfx)
    TextView mtjfx;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_party_menber;
    }

    @Override
    protected void initViews() {


        mShykTitleTitle.setText("团青管理");
        mShykXz.setVisibility(View.GONE);

    }
    @OnClick({R.id.shyk_left_back, R.id.dylb, R.id.tjfx})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.dylb://党员列表
//                jump(MemberActivesActivity.class);
                jump(ActivitySubjectH5.class,"党员列表");
                break;
            case R.id.tjfx://统计分析
//                jump(MemberStatisticsActivity.class);
                ToastUtils.showToast("点击了统计分析！！！");
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityPartymemberList.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(ActivityPartymemberList.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}