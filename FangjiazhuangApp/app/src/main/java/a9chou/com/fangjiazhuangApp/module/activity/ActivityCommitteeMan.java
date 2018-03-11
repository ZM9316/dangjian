package a9chou.com.fangjiazhuangApp.module.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dongchengcheng on 2017/12/5.
 */

public class ActivityCommitteeMan extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.jiweipic)
    ImageView zjiweipic;
    private String i;
    private String j;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_committee_man;
    }

    @Override
    protected void initViews() {
        i=getIntent().getStringExtra("tag");
        j=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(j);
        mShykXz.setVisibility(View.GONE);
        switch(i){
            case "综合党支部":
                zjiweipic.setImageResource(R.drawable.dang_zong);
                break;
            case "经营党支部":
                zjiweipic.setImageResource(R.drawable.dang_jing);
                break;
            case "工程党支部":
                zjiweipic.setImageResource(R.drawable.dang_gong);
                break;
            case "运行党支部":
                zjiweipic.setImageResource(R.drawable.dang_yun);
                break;
            case "设备党支部":
                zjiweipic.setImageResource(R.drawable.dang_she);
                break;
            case "综合分工会":
                zjiweipic.setImageResource(R.drawable.fen_zong);
                break;
            case "经营分工会":
//                zjiweipic.setImageResource(R.drawable.dang_gong);
                break;
            case "工程分工会":
//                zjiweipic.setImageResource(R.drawable.dang_gong);
                break;
            case "运行分工会":
                zjiweipic.setImageResource(R.drawable.fen_yun);
                break;
            case "设备分工会":
                zjiweipic.setImageResource(R.drawable.fen_she);
                break;
            case "综合团支部":
                zjiweipic.setImageResource(R.drawable.tuan_zong);
                break;
            case "经营团支部":
//                zjiweipic.setImageResource(R.drawable.tuan_zong);
                break;
            case "工程团支部":
//                zjiweipic.setImageResource(R.drawable.dang_gong);
                break;
            case "运行团支部":
                zjiweipic.setImageResource(R.drawable.tuan_yun);
                break;
            case "设备团支部":
                zjiweipic.setImageResource(R.drawable.tuan_she);
                break;
        }
//
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
        }
    }


    public void onViewClicked() {
        finish();
    }
}
