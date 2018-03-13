package a9chou.com.fangjiazhuangApp.module.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.PopupWindowUtil;
import a9chou.com.fangjiazhuangApp.utils.TimeUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.R.id.meet_agenda;
import static a9chou.com.fangjiazhuangApp.R.id.ty;

/**
 * Created by zm9316 on 2017/12/22.
 */

public class ActivityInfo extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.huiyi_name_tv)
    TextView mhuiyi_name_tv;
    @BindView(R.id.huiyi_didian_tv)
    TextView mhuiyi_didian_tv;
    @BindView(R.id.canhui_renyuan_tv)
    TextView mcanhui_renyuan_tv;
    @BindView(R.id.meet_name)
    EditText mmeet_name;
    @BindView(R.id.choose_join)
    EditText mchoose_join;
    @BindView(R.id.choose_attend)
    EditText mchoose_attend;
    @BindView(R.id.place)
    EditText mplace;
    @BindView(R.id.choose_start)
    EditText mchoose_start;
    @BindView(R.id.liexiren_renyuan)
    LinearLayout mliexiren_renyuan;
    //    @BindView(R.id.choose_end)
//    EditText mchoose_end;
    @BindView(meet_agenda)
    EditText mmeet_agenda;
    @BindView(R.id.release_btn)
    Button mrelease_btn;
    private TimePickerView mtimePicker;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private Map<String, String> mmMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private String join;
    private String attend;
    private Bundle bundle = new Bundle();
    private LoadingDailog mDialog;
    private String joinId = "";
    private String attendId = "";
    private String placeName;
    private String tit = "";
    private String bftit = "";
    private String typeId = "";
    private String partyOrgId = "";
    private String className = "";
    private Button mbtn_cancel;
    private TextView mManual_Input;
    private TextView mList_Selection;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_huiyiinfo;
    }

    @Override
    protected void initViews() {
        typeId = getIntent().getStringExtra("typeId");
        partyOrgId = getIntent().getStringExtra("partyOrgId");
        tit = getIntent().getStringExtra("tit");
        bftit = getIntent().getStringExtra("bftit");
        className = getIntent().getStringExtra("className");
        mShykXz.setVisibility(View.GONE);
        //设置EditText的显示方式为多行文本输入
        mchoose_join.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mmeet_agenda.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //改变默认的单行模式
        mchoose_join.setSingleLine(false);
        mmeet_agenda.setSingleLine(false);
        //水平滚动设置为False
        mchoose_join.setHorizontallyScrolling(false);
        mmeet_agenda.setHorizontallyScrolling(false);
//        mend_time.setVisibility(View.GONE);
        bundle = this.getIntent().getExtras();
        join = bundle.getString("join");
        attend = bundle.getString("attend");
        mchoose_join.setText(join);
        mchoose_attend.setText(attend);
        Log.i("zm9316", "info:" + tit);
        if(!"AAA".equals(className)&&"53c435ca02a611e8b47f6212970bda1b".equals(partyOrgId)||"0e15612102a611e8b47f6212970bda1b".equals(partyOrgId)||"a8e19d8e02a511e8b47f6212970bda1b".equals(partyOrgId)||"76af0d6202a511e8b47f6212970bda1b".equals(partyOrgId)){
            className="AAA";
        }
        if ("集体学习".equals(tit) || "党课".equals(tit)) {
            mShykTitleTitle.setText(tit);
            mhuiyi_name_tv.setText("名    称");
            mmeet_name.setHint("请输入名称");
            mhuiyi_didian_tv.setText("地    点");
            mcanhui_renyuan_tv.setText("人    员");
            mchoose_join.setHint("请输入参加人员");
            mplace.setHint("请输入地点");
            mmeet_agenda.setHint("请输入学习内容");
            mliexiren_renyuan.setVisibility(View.GONE);
        } else {
            mShykTitleTitle.setText(tit);
            mhuiyi_name_tv.setText("会议名称");
            mmeet_name.setHint("请输入会议名称");
            mhuiyi_didian_tv.setText("会议地点");
            mcanhui_renyuan_tv.setText("参会人员");
            mchoose_join.setHint("请输入参会人员");
            mplace.setHint("请输入会议地点");
            mmeet_agenda.setHint("请输入会议议程");
            mliexiren_renyuan.setVisibility(View.VISIBLE);
        }

    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityInfo.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }


    @OnClick({R.id.shyk_left_back, R.id.release_btn, R.id.place, R.id.meet_name, R.id.choose_join, R.id.choose_attend, R.id.choose_start, R.id.meet_agenda})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.shyk_left_back:
                jump(PartygroupActivity.class, tit, bftit, typeId, partyOrgId, className);
                Log.i("zm9316","className::"+className);
                finish();
                break;
            case R.id.choose_join:
                mchoose_join.setInputType(InputType.TYPE_NULL);
                jump(ActivityMenberList.class, typeId, partyOrgId, tit);
                break;
            case R.id.place:
                mplace.setInputType(InputType.TYPE_NULL);
                jump(ActivityConferenceSelection.class);
                break;
            case R.id.choose_attend:
                mchoose_attend.setInputType(InputType.TYPE_NULL);
                if("0e15612102a611e8b47f6212970bda1b".equals(partyOrgId)
                    ||"a8e19d8e02a511e8b47f6212970bda1b".equals(partyOrgId)
                    ||"76af0d6202a511e8b47f6212970bda1b".equals(partyOrgId)){
                    jump(ActivityStaffInput.class);
                }else{
                    PopupWindowUtil.initBottomPopup(ActivityInfo.this, R.layout.attend_popupwindow);
                    mbtn_cancel = (Button) PopupWindowUtil.getView(R.id.btn_cancel);
                    mManual_Input = (TextView) PopupWindowUtil.getView(R.id.Manual_Input);
                    mList_Selection = (TextView) PopupWindowUtil.getView(R.id.List_Selection);
                    if("53c435ca02a611e8b47f6212970bda1b".equals(partyOrgId)){
                        if("党委办公会".equals(tit)){
                            mManual_Input.setVisibility(View.VISIBLE);
                            mList_Selection.setVisibility(View.VISIBLE);
                        }
//                        else{
//                            mManual_Input.setVisibility(View.VISIBLE);
//                            mList_Selection.setVisibility(View.VISIBLE);
//                        }

                    }else{
                        if("1ad4c01fbfbb11e7b9a46212970bta1s".equals(partyOrgId)
                                ||"31147d99d97411e796d06212970bda1b".equals(partyOrgId)
                                ||"214a8b6ed97411e796d06212970bda1b".equals(partyOrgId)
                                ||"12650a72d97411e796d06212970bda1b".equals(partyOrgId)
                                ||"0ad4d01fbfbb11e7b9a46212970bda1b".equals(partyOrgId)){
                            mManual_Input.setVisibility(View.VISIBLE);
                            mList_Selection.setVisibility(View.VISIBLE);
                        }else{
                            mManual_Input.setVisibility(View.VISIBLE);
                            mList_Selection.setVisibility(View.VISIBLE);
                        }
                    }
                    mbtn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupWindowUtil.popupDismiss();
                        }
                    });
                    mManual_Input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            jump(ActivityStaffInput.class);
                            PopupWindowUtil.popupDismiss();
                        }
                    });
                    mList_Selection.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            jump(ActivityAttendMenberList.class, typeId, partyOrgId, tit);
                            PopupWindowUtil.popupDismiss();
                        }
                    });
                }

//                if ("4".equals(typeId)) {
//                    jump(ActivityStaffInput.class);
//                } else {
//                    jump(ActivityAttendMenberList.class, typeId, partyOrgId, tit);
//                }
                break;
            case R.id.choose_start:
                mchoose_start.setInputType(InputType.TYPE_NULL);
                initStartPicker();
                break;
//            case R.id.choose_end:
//                mchoose_start.setInputType(InputType.TYPE_NULL);
//                initEndPicker();
//                break;
            case R.id.release_btn:
                loadDialog();
                if ("".equals(mmeet_name.getText().toString())) {
                    mDialog.dismiss();
                    ToastUtils.showToast("会议名称不能为空！");
                } else {
                    if ("".equals(mchoose_join.getText().toString())) {
                        mDialog.dismiss();
                        ToastUtils.showToast("参会人员不能为空！");
                    } else {
                        if ("".equals(mplace.getText().toString())) {
                            mDialog.dismiss();
                            ToastUtils.showToast("会议地点不能为空！");
                        } else {
                            if ("".equals(mchoose_start.getText().toString())) {
                                mDialog.dismiss();
                                ToastUtils.showToast("开始时间不能为空！");
                            } else {
                                if ("".equals(mmeet_agenda.getText().toString())) {
                                    mDialog.dismiss();
                                    ToastUtils.showToast("会议议程或学习内容不能为空！");
                                } else {
                                    Date dt = new Date();
                                    mEd = getSharedPreferences("config", MODE_PRIVATE);
                                    mUserId = mEd.getString("userId", "");
                                    mToken = mEd.getString("token", "");
                                    mUserName = mEd.getString("username", "");
                                    mMap = new HashMap<>();
                                    mMap.put("userId", mUserId);
                                    mMap.put("token", mToken);
                                    mMap.put("isNewRecord", "true");
                                    mMap.put("typeId", typeId);
                                    mMap.put("partyOrgId", partyOrgId);
                                    mMap.put("userName", mUserName);
                                    mMap.put("founderName", mUserName);
                                    mMap.put("founder", mUserId);
                                    mMap.put("title", mmeet_name.getText().toString());
                                    mMap.put("address", mplace.getText().toString());
                                    mMap.put("startTime", mchoose_start.getText().toString());
                                    mMap.put("createDate", TimeUtil.getTime1(dt).toString());
                                    mMap.put("content", mmeet_agenda.getText().toString());
                                    mMap.put("ids", joinId);
                                    mMap.put("attendPerson", attendId);
                                    Log.i("zm9316",joinId+"::::::"+attendId);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ViseHttp.POST("mobile/wp/wpMeeting/save").
//                                                    baseUrl("http://10.24.21.163:8181/treps/a/").
                                                    addParams(mMap).
                                                    request(new ACallback<String>() {
                                                        @Override
                                                        public void onSuccess(String data) {
                                                            Log.i("zm9316", "Ainfo:Success");
                                                            mDialog.dismiss();
                                                            Log.i("zm9316", "typeId:" + typeId + "partyOrgId:" + partyOrgId);
                                                            jump(PartygroupActivity.class, tit, bftit, typeId, partyOrgId, className);//党小组会
                                                            finish();
                                                        }

                                                        @Override
                                                        public void onFail(int errCode, String errMsg) {
                                                            Log.i("zm9316", "Ainfo:Fail:" + mMap + "err::::" + errCode + "," + errMsg);
                                                            mDialog.dismiss();
                                                            ToastUtils.showToast("当前网络不通畅，请重试！");
                                                        }
                                                    });
                                        }
                                    }).start();
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    private void initStartPicker() {
        //时间选择器
        mtimePicker = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                mchoose_start.setText(TimeUtil.getTime1(date));
//                ToastUtils.showToast(date+"");
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, true, true})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDecorView(null)
                .build();
        mtimePicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getExtras().getString("join") != null & data.getExtras() != null) {
                joinId = data.getExtras().getString("join");
                mchoose_join.setText(data.getExtras().getString("joinN"));
            }
            if (data.getExtras().getString("attend") != null & data.getExtras() != null) {
                attendId = data.getExtras().getString("attend");
                mchoose_attend.setText(data.getExtras().getString("attendN"));
            }
            if (data.getExtras().getString("placeName") != null & data.getExtras() != null) {
                placeName = data.getExtras().getString("placeName");
                mplace.setText(data.getExtras().getString("placeName"));
            }
        }

    }


    private void jump(Class c) {
        Intent i = new Intent(ActivityInfo.this, c);
        i.putExtra("tag", "1");
//        bundle.putString("join",mchoose_join.getText().toString());
//        bundle.putString("attend",mchoose_attend.getText().toString());
//        startActivity(i);
        startActivityForResult(i, 0);
//        finish();
    }

    private void jump(Class c, Bundle bundle) {
        Intent i = new Intent(ActivityInfo.this, c);
        i.putExtra("tag", "1");
        i.putExtras(bundle);
        startActivityForResult(i, 0);
//        finish();
//        startActivity(i);
//        startActivityForResult(i,0);
    }

    private void jump(Class c, String typeid, String partyOrgId, String tit) {
        Intent i = new Intent(ActivityInfo.this, c);
        i.putExtra("typeId", typeid);
        i.putExtra("partyOrgId", partyOrgId);
        i.putExtra("tit", tit);
        startActivityForResult(i, 0);
//        finish();
//        startActivity(i);
//        startActivityForResult(i,0);
    }

    private void jump(Class c, String tit, String bftit, String typeid, String partyOrgId, String className) {
        Intent i = new Intent(ActivityInfo.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId", typeid);
        i.putExtra("partyOrgId", partyOrgId);
        i.putExtra("tit", tit);
        i.putExtra("bftit", bftit);
        i.putExtra("className", className);
        startActivity(i);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            jump(PartygroupActivity.class, tit, bftit, typeId, partyOrgId, className);
            Log.i("zm9316","className::"+partyOrgId);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
