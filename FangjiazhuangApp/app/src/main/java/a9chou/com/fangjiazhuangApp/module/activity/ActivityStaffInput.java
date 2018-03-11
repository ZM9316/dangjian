package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhang on 2018/1/30.
 */

public class ActivityStaffInput extends BaseActivity {

    @BindView(R.id.big_events_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.big_events_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.big_events_search_iv)
    ImageView mbig_events_search_iv;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.attend_tv)
    TextView mattend_tv;
    @BindView(R.id.btn)
    Button mbtn;
    @BindView(R.id.clear)
    Button mclear;
    @BindView(R.id.attend_et)
    EditText mattend_et;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private List<BbsjBean> list = new ArrayList<>();
    private LoadingDailog mDialog;
    private String sumId ="";
    private String sumName="";
    private String pin="";
    private List<String> sn=new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.staff_input;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("添加列席人员");
        mbig_events_search_iv.setVisibility(View.GONE);
        mattend_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Log.i("zm9316", "onTextChanged: "+charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.big_events_left_back, R.id.shyk_xz,R.id.btn,R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.big_events_left_back:
                finish();
                break;
            case R.id.clear:
                mattend_tv.setText("");
                mattend_et.setText("");
                sn.clear();
                break;
            case R.id.btn:
                sn.add(mattend_et.getText().toString());
                sumName="";
                for(int i=0;i<sn.size();i++){
                    if(!"".equals(sn.get(i))){
                        sumName+=sn.get(i)+",";
                    }

                }
                mattend_tv.setText(sumName);
                mattend_et.setText("");
                Go(sumName);
                Log.i("zm9316","sumName::"+sumName);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
////                        Go(mattend_tv.getText().toString());
//                    }
//                }).start();
                break;
            case R.id.shyk_xz:
                if(!"".equals(mattend_tv.getText().toString())){
                    Bundle bundle=getIntent().getExtras();
                    bundle.putString("attend", sumId);
                    bundle.putString("attendN",sumName);
                    Log.i("zm9316","sumId::"+ sumId +"sumName::"+sumName);
                    mDialog.dismiss();
                    jump(ActivityInfo.class,bundle);
                    Log.i("zm9316",":::::::::"+mattend_et.getText().toString());
                }else{
                    ToastUtils.showToast("列席人员为空！");
                }
                break;
        }
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityStaffInput.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    private void Go(String attendName){
        loadDialog();
        sumName=attendName;
        Log.i("zm9316",mattend_et.getText().toString());
        mEd=getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);
        mMap.put("nameData", attendName);
        ViseHttp.POST("mobile/wp/wpFlow/getPersonDatas")
                .addParams(mMap)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mShykXz.setEnabled(true);
                        HashMap<String,String> hashMap = new HashMap<String,String>();
                        sumId="";
                            try{
                                Log.i("zm9316","xxxx:"+data);
                                JSONObject o = new JSONObject(data);
                                Log.i("zm9316","qqqqqq:"+o.isNull("data"));
                                if(o.has("data")){
                                    JSONArray type = o.getJSONArray("data");
                                    if(type.length()!=0){
                                        for(int i=0;i<type.length();i++){
                                            JSONObject x=type.getJSONObject(i);
                                            Iterator iterator = x.keys();
                                            while(iterator.hasNext()){
                                                String key = iterator.next() + "";
                                                if(key.equals("name")){
                                                    hashMap.put(key,x.getString(key));
                                                }
                                                if(key.equals("id")){
                                                    hashMap.put(key,x.getString(key));
                                                }
                                            }
                                            list.add(new BbsjBean(hashMap.get("name"),hashMap.get("id")));
                                        }
                                        for(int i=0;i<list.size();i++){
                                            sumId+=list.get(i).getPersonId()+",";
                                        }
                                        mDialog.dismiss();
                                    }else{
                                        mDialog.dismiss();
                                        ToastUtils.showToast("未找到该人员的信息，请检查输入的姓名是否正确！");
                                    }

                                }else{
                                    mDialog.dismiss();
                                    ToastUtils.showToast("未找到该人员的信息，请检查输入的姓名是否正确！");
//                                    mShykXz.setEnabled(false);
                                }

                            }catch (Exception e){

                            }finally {
                                mattend_et.setText("");
                            }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtils.showToast("网络不通畅，请重试！");
                    }
                });
    }

    private void jump(Class c,Bundle attend) {
//        Intent i = new Intent(ActivityAttendMenberList.this, c);
//        i.putExtra("tag", "1");
//        i.putExtras( attend);
//        startActivity(i);
//        finish();
        Intent i=new Intent(ActivityStaffInput.this, c);
        i.putExtras(attend);
        setResult(1,i);
        finish();
//        ActivityAttendMenberList.onDestory();
    }

}
