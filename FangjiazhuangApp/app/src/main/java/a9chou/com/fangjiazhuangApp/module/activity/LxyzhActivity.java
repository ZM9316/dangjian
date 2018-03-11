package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class LxyzhActivity extends BaseActivity {


    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dzdg)
    TextView mdzdg;
    @BindView(R.id.zsjjh)
    TextView mzsjjh;
    @BindView(R.id.pfjy)
    TextView mpfjy;
//    @BindView(R.id.kscg)
//    TextView mkscg;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lxyzh;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("两学一做");
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.dzdg, R.id.zsjjh, R.id.pfjy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.dzdg:
                jump(DzdgActivity.class);//党章党规
                break;
            case R.id.zsjjh:
                jump(ZsjjhActivity.class);//总书记讲话
                break;
            case R.id.pfjy:
                jump(PfjyActivity.class);//普法教育
                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(LxyzhActivity.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
}