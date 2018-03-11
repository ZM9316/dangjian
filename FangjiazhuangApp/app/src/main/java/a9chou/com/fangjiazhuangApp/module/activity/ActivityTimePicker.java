package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import butterknife.BindView;
import butterknife.OnClick;

;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class ActivityTimePicker extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
//    @BindView(R.id.timePicker)
//    TimePickerView mtimePicker;
    private LoadingDailog mDialog;
    private TimePickerView mtimePicker;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_timepicker;
    }

    @Override
    protected void initViews() {

        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityTimePicker.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();

        mShykTitleTitle.setText("时间选择");
        mShykXz.setVisibility(View.GONE);
//        initEndPicker();



    }


    @OnClick({R.id.shyk_left_back, R.id.shyk_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                jump(MainActivity.class);
                break;
            case R.id.shyk_xz:
                jump(ActivityInfo.class);
                break;
        }
    }



    private void jump(Class c) {
        Intent i = new Intent(ActivityTimePicker.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
        finish();
    }

}
