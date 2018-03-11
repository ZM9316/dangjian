package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class DzjxActivity extends BaseActivity {


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
    @BindView(R.id.khtb)
    TextView mkhtb;
//    @BindView(R.id.kscg)
//    TextView mkscg;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dzjx;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("党群绩效");
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.khnr, R.id.djkh, R.id.dlkh,R.id.ghkh,R.id.tqkh,R.id.khtb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.khnr:
                jump(KhnrActivity.class);//考核内容
                break;
            case R.id.djkh:
                jump(DjkhActivity.class);//党建考核
                break;
            case R.id.dlkh:
                jump(DlkhActivity.class);//党廉考核
                break;
            case R.id.ghkh:
                jump(GhkhActivity.class);//工会考核
                break;
            case R.id.tqkh:
                jump(TqkhActivity.class);//团青考核
                break;
            case R.id.khtb:
                jump(KhtbActivity.class);//考核通报
                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(DzjxActivity.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
}