package a9chou.com.fangjiazhuangApp.module.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.SignInAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.QianDaoBean;
import a9chou.com.fangjiazhuangApp.module.dao.SignInBean;
import a9chou.com.fangjiazhuangApp.utils.TimeUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.XsnowPermissionUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity {

    private static final String TAG = "SignInActivity";
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.sign_in_recycler)
    RecyclerView mSignInRecycler;
    @BindView(R.id.activity_sign_in)
    LinearLayout mActivitySignIn;
    @BindView(R.id.sign_in_linear)
    LinearLayout mSignInLinear;
    @BindView(R.id.sign_in_head_tv)
    TextView mSignInHeadTv;
    @BindView(R.id.zwchry)
    LinearLayout mZwchry;
    private String mToken;
    private String mUserName;
    private Button mBtn;
    private LinearLayoutManager mLinearLayoutManager;
    private List<SignInBean.DataBean.PersonListBean> mPersonList;
    private String mMeetingTitle;
    private String mTogetTitle;
    private SharedPreferences mSp;
    private String mDate;
    private String mTogetherId;
    private long mStartTime;
    private long mEndTime;
    private long mNow;
    private String Ids="";
    private String j="";
    private String k="";
    private String tit="";
    private String bftit="";
    private String sumListId="";
    private String className="";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initViews() {

        mSp = getSharedPreferences("config", MODE_PRIVATE);
        mToken = mSp.getString("token", "");
        mUserName = mSp.getString("username", "");

        mShykTitleTitle.setText("会议签到");
        mShykXz.setVisibility(View.GONE);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        mDate = sDateFormat.format(new Date());
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSignInRecycler.setLayoutManager(mLinearLayoutManager);
        mSignInRecycler.setHasFixedSize(true);


        Intent i = getIntent();
        mTogetherId = i.getStringExtra("id");
        j=i.getStringExtra("typeId");
        k=i.getStringExtra("partyOrgId");
        tit=i.getStringExtra("tit");
        bftit=i.getStringExtra("bftit");
//        Ids=i.getStringExtra("id");
        className=i.getStringExtra("className");
        Map<String, String> togetherMap = new HashMap<>();

        togetherMap.put("id", mTogetherId);
        togetherMap.put("userName", mUserName);
        togetherMap.put("token", mToken);
        ViseHttp
                .POST("mobile/wp/wpMeeting/getPerson")
                .addParams(togetherMap)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.i(TAG,data);
                        Gson g = new Gson();
                        SignInBean sign = g.fromJson(data, SignInBean.class);
                        mPersonList = sign.getData().getPersonList();
                        mTogetTitle = sign.getData().getTitle();
//                        mStartTime = TimeUtil.DateToSeconds(sign.getData().getStartTime());
//                        mEndTime = TimeUtil.DateToSeconds(sign.getData().getEndTime());
                        Log.i("zm9316","sign"+sign.getData().getEndTime());
//                        mNow = TimeUtil.DateToSeconds(mDate);
//                        Log.d(TAG, "onSuccess: " + mStartTime + "  " + mEndTime + "  " + mNow);
                        if (mPersonList.size() != 0) {
                            mSignInLinear.setVisibility(View.VISIBLE);
                            mZwchry.setVisibility(View.GONE);
                            SignInAdapter adapter = new SignInAdapter(mPersonList);
                            adapter.openLoadAnimation();
                            mSignInRecycler.setAdapter(adapter);
                            adapter.addHeaderView(getHeaderView(), 0);

                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                public String mTogetherUserId;

                                @Override
                                public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                    mBtn = (Button) adapter.getViewByPosition(mSignInRecycler, position + 1, R.id.sign_in_sign_btn);
                                    mBtn.setVisibility(View.INVISIBLE);
                                    switch (view.getId()) {
                                        case R.id.sign_in_sign_btn:
                                            for (int i = 0; i < mPersonList.size(); i++) {
                                                mTogetherUserId = mPersonList.get(position).getUserId();
                                            }
                                            if (mNow > mStartTime) {
                                                if (mBtn.getText() != "已 到") {
                                                    Map<String, String> m = new HashMap<String, String>();
                                                    m.put("meetingId", mTogetherId);
                                                    m.put("userId", mTogetherUserId);
                                                    m.put("signature", mDate);
                                                    m.put("token", mToken);
                                                    m.put("userName", mUserName);
                                                    ViseHttp
                                                            .POST("mobile/wp/wpMeetingPerson/updateSignature")
                                                            .addParams(m)
                                                            .request(new ACallback<String>() {
                                                                @Override
                                                                public void onSuccess(String data) {
                                                                    Log.d(TAG, "onSuccess: " + data);
                                                                    Gson g = new Gson();
                                                                    QianDaoBean qianDaoBean = g.fromJson(data, QianDaoBean.class);
                                                                    String message = qianDaoBean.getMessage();
                                                                    ToastUtils.showToast(message);
                                                                    mBtn.setBackgroundResource(R.drawable.yi_sign_in_btn);
                                                                    mBtn.setText("已 到");
                                                                }
                                                                @Override
                                                                public void onFail(int errCode, String errMsg) {
                                                                    Log.d(TAG, "onSuccess: " + errCode + "  " + errMsg);
                                                                }
                                                            });
                                                } else {
                                                    Map<String, String> mmp = new HashMap<String, String>();
                                                    mmp.put("meetingId", mTogetherId);
                                                    mmp.put("userId", mTogetherUserId);
                                                    mmp.put("token", mToken);
                                                    mmp.put("signature", "");
                                                    mmp.put("userName", mUserName);
                                                    ViseHttp
                                                            .POST("mobile/wp/wpMeetingPerson/updateSignature")
                                                            .addParams(mmp)
                                                            .request(new ACallback<String>() {
                                                                @Override
                                                                public void onSuccess(String data) {
                                                                    Log.d(TAG, "onSuccess: " + data);
                                                                    ToastUtils.showToast("取消签到");
                                                                    mBtn.setBackgroundResource(R.drawable.sign_in_btn);
                                                                    mBtn.setText("签 到");
                                                                }

                                                                @Override
                                                                public void onFail(int errCode, String errMsg) {
                                                                    Log.d(TAG, "onSuccess: " + errCode + "  " + errMsg);
                                                                }
                                                            });
                                                }
                                            } else {
                                                ToastUtils.showToast("会议会未开始");
                                            }
                                            break;
                                        case R.id.sign_in_tel_btn:

                                            XsnowPermissionUtil.getPermission(SignInActivity.this, Manifest.permission.CALL_PHONE);

                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPersonList.get(position).getTelephone()));
                                            if (ActivityCompat.checkSelfPermission(SignInActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                return;
                                            }
                                            startActivity(intent);
                                            break;
                                    }
                                }
                            });
                        } else {
                            mZwchry.setVisibility(View.VISIBLE);
                            mSignInLinear.setVisibility(View.GONE);
                            mSignInHeadTv.setText(mTogetTitle);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });


    }

    @OnClick(R.id.shyk_left_back)
    public void onViewClicked() {
        jump(PartygroupActivity.class,mTogetherId,tit,bftit,j,k,className);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            jump(PartygroupActivity.class,mTogetherId,tit,bftit,j,k,className);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void jump(Class c,String Ids,String tit,String bftit,String typeid,String partyOrgId,String classname) {
        Intent i = new Intent(SignInActivity.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("id",Ids);
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        i.putExtra("className",classname);
        startActivity(i);
        finish();
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.sign_in_item_header, (ViewGroup) mSignInRecycler.getParent(), false);

        TextView tv = (TextView) view.findViewById(R.id.sign_in_head_tv);


        tv.setText(mMeetingTitle);
        return view;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}