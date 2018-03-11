package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivityThreeSessionStatistics extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dydh)
    TextView mdydh;
    @BindView(R.id.zwh)
    TextView mzwh;
    @BindView(R.id.dxzh)
    TextView mdxzh;
    @BindView(R.id.dk)
    TextView mdk;
    private String i="";
    private String j="";


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_threesession_statistics;
    }

    @Override
    protected void initViews() {
        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.dydh, R.id.zwh, R.id.dxzh,R.id.dk,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.dydh://党员大会
                switch(i){
                    case "综合党支部":
                        jump(ActivityStatisticalAnalysis.class,"党员大会",i,"0","");
                        break;
                    case "经营党支部":
                        jump(ActivityStatisticalAnalysis.class,"党员大会",i,"0","");
                        break;
                    case "运行党支部":
                        jump(ActivityStatisticalAnalysis.class,"党员大会",i,"0","");
                        break;
                    case "设备党支部":
                        jump(ActivityStatisticalAnalysis.class,"党员大会",i,"0","");
                        break;
                    case "工程党支部":
                        jump(ActivityStatisticalAnalysis.class,"党员大会",i,"0","");
                        break;
                }

//                ToastUtils.showToast("党员大会");
                break;
            case R.id.zwh://支委会
                switch(i){
                    case "综合党支部":
                        jump(ActivityStatisticalAnalysis.class,"支委会",i,"1","");
                        break;
                    case "经营党支部":
                        jump(ActivityStatisticalAnalysis.class,"支委会",i,"1","");
                        break;
                    case "运行党支部":
                        jump(ActivityStatisticalAnalysis.class,"支委会",i,"1","");
                        break;
                    case "设备党支部":
                        jump(ActivityStatisticalAnalysis.class,"支委会",i,"1","");
                        break;
                    case "工程党支部":
                        jump(ActivityStatisticalAnalysis.class,"支委会",i,"1","");
                        break;
                }

//                ToastUtils.showToast("支委会");
                break;
            case R.id.dxzh://党小组会
                switch(i){
                    case "综合党支部":
                        jump(ActivityStatisticalAnalysis.class,"党小组会",i,"2","");
                        break;
                    case "经营党支部":
                        jump(ActivityStatisticalAnalysis.class,"党小组会",i,"2","");
                        break;
                    case "运行党支部":
                        jump(ActivityStatisticalAnalysis.class,"党小组会",i,"2","");
                        break;
                    case "设备党支部":
                        jump(ActivityStatisticalAnalysis.class,"党小组会",i,"2","");
                        break;
                    case "工程党支部":
                        jump(ActivityStatisticalAnalysis.class,"党小组会",i,"2","");
                        break;
                }

//                ToastUtils.showToast("党小组会");
                break;
            case R.id.dk://党课
                switch(i){
                    case "综合党支部":
                        jump(ActivityStatisticalAnalysis.class,"党课",i,"3","");
                        break;
                    case "经营党支部":
                        jump(ActivityStatisticalAnalysis.class,"党课",i,"3","");
                        break;
                    case "运行党支部":
                        jump(ActivityStatisticalAnalysis.class,"党课",i,"3","");
                        break;
                    case "设备党支部":
                        jump(ActivityStatisticalAnalysis.class,"党课",i,"3","");
                        break;
                    case "工程党支部":
                        jump(ActivityStatisticalAnalysis.class,"党课",i,"3","");
                        break;
                }

//                ToastUtils.showToast("党课");
                break;
        }
    }
    private void jump(Class c,String tag) {
        Intent i = new Intent(ActivityThreeSessionStatistics.this, c);
        i.putExtra("tit", tag);
        startActivity(i);
//        finish();
    }
    private void jump(Class c,String tit,String bftit,String typeid,String partyOrgId) {
        Intent i = new Intent(ActivityThreeSessionStatistics.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        startActivity(i);
        finish();
    }

}