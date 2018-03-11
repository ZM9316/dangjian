package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityShareUI extends BaseActivity {


    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.share1)
    TextView mshare1;
    @BindView(R.id.share2)
    TextView mshare2;
    @BindView(R.id.share3)
    TextView mshare3;
    @BindView(R.id.share4)
    TextView mshare4;
    @BindView(R.id.share5)
    TextView mshare5;
    @BindView(R.id.share6)
    TextView mshare6;
    @BindView(R.id.share7)
    TextView mshare7;
//    @BindView(R.id.share8)
//    TextView mshare8;
    @BindView(R.id.share9)
    TextView mshare9;
//    @BindView(R.id.kscg)
//    TextView mkscg;
    private String i;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activityshareui;
    }

    @Override
    protected void initViews() {
        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);

        if(i.endsWith("分工会")){
            mshare1.setText("分工会委员");
            mshare2.setText("两会一课");
            mshare5.setText("班组建设");
            mshare6.setText("素质提升");
            mshare7.setText("员工楷模");
            mshare9.setText("工会荣誉");
            Log.i("zm9316",i);
        }else if(i.endsWith("党支部")) {
            mshare1.setText("支部委员");
            mshare2.setText("三会一课");

        }else if(i.endsWith("团支部")){
            mshare1.setText("团支部委员");
            mshare2.setText("三会一课");
            mshare4.setVisibility(View.GONE);
            mshare9.setVisibility(View.GONE);
            mshare6.setText("团日活动");
            mshare7.setText("       ");
            mshare7.setCompoundDrawables(null, null, null, null);
        }
    }

    @OnClick({R.id.shyk_left_back, R.id.share1, R.id.share2, R.id.share3,R.id.share4,R.id.share5,R.id.share6,R.id.share7,R.id.share9})//,R.id.share8
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.share1:
//                jump(ActivityShareArticle.class,"支部委员");//党支部委员
                jump(ActivityCommitteeMan.class,"支部委员",i);//党支部委员
                break;
            case R.id.share2:
                jump(ActivityThreeMeeting.class,mshare2.getText().toString(),i);//三会一课
//                finish();
                break;
            case R.id.share3:
                jump(ActivityShareArticle.class,"规定动作清单");//规定动作清单
                break;
            case R.id.share4:
                jump(ActivityShareArticle.class,"支部规制");//党支部规制
                break;
            case R.id.share5:
                jump(ActivityShareArticle.class,"年度计划");//年度计划
                break;
            case R.id.share6:
                jump(ActivityShareArticle.class,"党员信息");//党员信息
                break;
            case R.id.share7:
                jump(ActivityShareArticle.class,"道德讲堂");//道德讲堂
                break;
//            case R.id.share8:
//                jump(ActivityShareArticle.class,"支部品牌");//支部品牌
//                break;
            case R.id.share9:
                jump(ActivityShareArticle.class,"党务公开");//党务公开
                break;

        }
    }

    private void jump(Class c,String tag) {
        Intent i = new Intent(ActivityShareUI.this, c);
        i.putExtra("tit", tag);
        startActivity(i);
//        finish();
    }
    private void jump(Class c,int tag) {
        Intent i = new Intent(ActivityShareUI.this, c);
        i.putExtra("tid", tag);
        startActivity(i);
//        finish();
    }
    private void jump(Class c,String tag,String tit) {
        Intent i = new Intent(ActivityShareUI.this, c);
        i.putExtra("tit", tag);
        i.putExtra("tag", tit);
        startActivity(i);
//        finish();
    }
}