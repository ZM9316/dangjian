package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityLabour extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zhfgh)
    TextView mkhnr;
    @BindView(R.id.jyfgh)
    TextView mdjkh;
    @BindView(R.id.gcfgh)
    TextView mdlkh;
    @BindView(R.id.yxfgh)
    TextView mghkh;
    @BindView(R.id.sbfgh)
    TextView mtqkh;
//    @BindView(R.id.fghzbpm)
//    TextView mkhtb;
    //    @BindView(R.id.kscg)
//    TextView mkscg;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_labour_union;
    }

    @Override
    protected void initViews() {
        Intent i=getIntent();
        mShykTitleTitle.setText(i.getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.zhfgh, R.id.jyfgh, R.id.gcfgh,R.id.yxfgh,R.id.sbfgh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.zhfgh:
                jump(ActivityBranchContent.class,"综合分工会");//综合分工会
                break;
            case R.id.jyfgh:
                jump(ActivityBranchContent.class,"经营分工会");//经营分工会
                break;
            case R.id.gcfgh:
                jump(ActivityBranchContent.class,"工程分工会");//工程分工会
                break;
            case R.id.yxfgh:
                jump(ActivityBranchContent.class,"运行分工会");//运行分工会
                break;
            case R.id.sbfgh:
                jump(ActivityBranchContent.class,"设备分工会");//设备分工会
                break;
//            case R.id.fghzbpm:
//                jump(ActivityShareUI.class,"支部排名");//分工会支部排名
//                break;
        }
    }
    private void jump(Class c,String tag) {
        Intent i = new Intent(ActivityLabour.this, c);
        i.putExtra("tit", tag);
        startActivity(i);
//        finish();
    }
}