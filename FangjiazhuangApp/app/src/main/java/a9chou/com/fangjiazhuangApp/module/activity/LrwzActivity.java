package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.HashMap;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class LrwzActivity extends BaseActivity {
    private static final String TAG = "LrwzActivity";
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.title_edit)
    EditText mTitleEdit;
    @BindView(R.id.xd_edit)
    EditText mXdEdit;
    @BindView(R.id.sc_btn)
    Button mScBtn;
    @BindView(R.id.activity_lrwz)
    LinearLayout mActivityLrwz;
    private String mTitle;
    private String mXd;
    private String mUserId;
    private String mToken;
    private String mUsername;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lrwz;
    }

    @Override
    protected void initViews() {


        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        mUserId = sp.getString("userId", "");
        mToken = sp.getString("token", "");
        mUsername = sp.getString("username", "");
        Log.d(TAG, "initViews: " + mUserId + "  " + mToken + "  " + mUsername);


        mShykTitleTitle.setText("心得体会");
        mShykXz.setVisibility(View.GONE);

        mTitle = mTitleEdit.getText().toString().trim();
        mXd = mXdEdit.getText().toString().trim();

    }

    @OnClick({R.id.shyk_left_back, R.id.title_edit, R.id.xd_edit, R.id.sc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                goBack();
                break;
            case R.id.title_edit:
                break;
            case R.id.xd_edit:
                break;
            case R.id.sc_btn:
                String title = mTitleEdit.getText().toString().trim();
                String content = mXdEdit.getText().toString().trim();
                Log.d(TAG, "onViewClicked: " + title + "   " + content);

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    ToastUtils.showToast("请输入完整信息");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("userId", mUserId);
                    map.put("userName", mUsername);
                    map.put("token", mToken);
                    map.put("typeId", "0");
                    map.put("content", content);
                    map.put("contentTitle", title);
                    ViseHttp.
                            GET("mobile/wp/wpExperience/save")
                            .addParams(map)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    ToastUtils.showToast("上传成功");
                                    goBack();
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d(TAG, "onFail: " + errMsg + "  " + errCode);
                                }
                            });
                }
                break;
        }
    }

    private void goBack() {
        Intent intent = new Intent(LrwzActivity.this, XdthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}