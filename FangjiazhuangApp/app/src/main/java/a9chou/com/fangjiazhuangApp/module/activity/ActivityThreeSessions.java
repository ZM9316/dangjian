package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityThreeSessions extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.khnr)
    TextView mkhnr;
    @BindView(R.id.djkh)
    TextView mdjkh;
    @BindView(R.id.dlkh)
    TextView mdlkh;
    @BindView(R.id.ghkh)
    TextView mghkh;
    @BindView(R.id.tqkh)
    TextView mtqkh;
//    @BindView(R.id.khtb)
//    TextView mkhtb;
//    @BindView(R.id.kscg)
//    TextView mkscg;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_threesession;
    }

    @Override
    protected void initViews() {
        Intent i=getIntent();
        mShykTitleTitle.setText(i.getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.khnr, R.id.djkh, R.id.dlkh,R.id.ghkh,R.id.tqkh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.khnr:
                jump(ActivityThreeSessionStatistics.class,"综合党支部");//综合党支部
                ToastUtils.showToast("综合党支部");
                break;
            case R.id.djkh:
                jump(ActivityThreeSessionStatistics.class,"经营党支部");//经营党支部
                ToastUtils.showToast("经营党支部");
                break;
            case R.id.dlkh:
                jump(ActivityThreeSessionStatistics.class,"工程党支部");//工程党支部
                ToastUtils.showToast("工程党支部");
                break;
            case R.id.ghkh:
                jump(ActivityThreeSessionStatistics.class,"运行党支部");//运行党支部
                ToastUtils.showToast("运行党支部");
                break;
            case R.id.tqkh:
                jump(ActivityThreeSessionStatistics.class,"设备党支部");//设备党支部
                ToastUtils.showToast("设备党支部");
                break;
//            case R.id.khtb:
//                jump(ActivityShareUI.class,"支部排名");//党支部排名
//                break;
        }
    }
    private void jump(Class c,String tit) {
        Intent i = new Intent(ActivityThreeSessions.this, c);
        i.putExtra("tit", tit);
        startActivity(i);
//        finish();
    }
}