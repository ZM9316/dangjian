package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
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
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.OrgId;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class TqgzglActivity extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.tqhd)
    TextView mtqhd;
    @BindView(R.id.zthd)
    TextView mzthd;
    @BindView(R.id.jczz)
    TextView mjczz;
    @BindView(R.id.twh)
    TextView mtwh;
    @BindView(R.id.zzjgt)
    TextView mZzjgt;
    @BindView(R.id.hsgd)
    TextView mhsgd;
    @BindView(R.id.yxyz)
    TextView myxyz;
    private ArrayList<OrgId> partyOrgId=new ArrayList<>();
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private LoadingDailog mDialog;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_tqgzgl;
    }

    @Override
    protected void initViews() {


        mShykTitleTitle.setText("团青管理");
        mShykXz.setVisibility(View.GONE);

    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(TqgzglActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    @OnClick({R.id.shyk_left_back, R.id.tqhd, R.id.zthd, R.id.jczz,R.id.twh,R.id.hsgd, R.id.yxyz, R.id.zzjgt})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tqhd://团青管理
//                jump(MemberActivesActivity.class);
                jump(ActivityManagementYouthLeague.class,"团青管理");
                break;
            case R.id.zthd://五小QC
                jump(ActivitySubjectH5.class,"五小QC");
//                jump(MemberStatisticsActivity.class);
                break;
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.jczz://基层组织
                jump(ActivitySubjectH5.class,"基层组织");
                break;
            case R.id.twh://团委会
//                jump(TyxxActivity.class);
                loadDialog();
                Go("4");
                break;
            case R.id.hsgd://号手岗队
                jump(ActivitySubjectH5.class,"号手岗队");
//                jump(TyxxActivity.class);
                break;
                case R.id.yxyz://一学一做
                    jump(ActivitySubjectH5.class,"一学一做");
//                jump(TyxxActivity.class);
                break;
            case R.id.zzjgt:
                jump(ActivitySubjectH5.class,"团委委员");
//                jump(OrganizationPictureActivity.class,"团委委员");//团委
                break;
        }
    }

    private void Go(final String typeId){

        new Thread(new Runnable() {
            @Override
            public void run() {
                mEd=getSharedPreferences("config",MODE_PRIVATE);
                mUserId = mEd.getString("userId", "");
                mToken = mEd.getString("token", "");
                mUserName = mEd.getString("username", "");
                mMap = new HashMap<>();
                mMap.put("userId", mUserId);
                mMap.put("token", mToken);
                mMap.put("userName", mUserName);
                ViseHttp.POST("mobile/wp/wpMeeting/partyAndGroup2")
                        .addParams(mMap)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                partyOrgId.clear();
                                com.tencent.mm.opensdk.utils.Log.i("zm9316","ActivityThreeMeeting:"+data);
                                HashMap<String,String>  hashMap=new HashMap<>();
                                try{
                                    JSONObject o = new JSONObject(data);
                                    if(o.isNull("data")){
                                        ToastUtils.showToast("未获取到相关信息！！！");
                                    }else{
                                        JSONArray type = o.getJSONArray("data");
                                        for(int i=0;i<type.length();i++){
                                            JSONObject x = type.getJSONObject(i);
                                            Iterator iterator = x.keys();
                                            while(iterator.hasNext()){
                                                String key = iterator.next() + "";
                                                if(key.equals("id")){
                                                    hashMap.put("id",x.getString("id"));
                                                }
                                                if(key.equals("name")){
                                                    hashMap.put("name",x.getString("name"));
                                                }
                                            }
                                            partyOrgId.add(new OrgId(hashMap.get("name"),hashMap.get("id")));
                                            Log.i("zm9316","partyOrgId"+i+":"+partyOrgId);
                                        }
                                        switch(typeId){
                                            case "4":
                                                for(int s=0;s<partyOrgId.size();s++){
                                                    if("团建政工".equals(partyOrgId.get(s).getName())){
                                                        mDialog.dismiss();
                                                        jump(PartygroupActivity.class,"团委会","团青管理",typeId,partyOrgId.get(s).getId(),true,"AAA");//支部委员会
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    mDialog.dismiss();
                                }catch (Exception e){
                                    mDialog.dismiss();
                                    com.tencent.mm.opensdk.utils.Log.i("zm9316",e+"");
                                }
                            }
                            @Override
                            public void onFail(int errCode, String errMsg) {
                                mDialog.dismiss();
                            }
                        });
            }
        }).start();

    }



    private void jump(Class c) {
        Intent i = new Intent(TqgzglActivity.this, c);
        i.putExtra("tag", "2");
        startActivity(i);
//        finish();
    }

    private void jump(Class c,String Tag,String tit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent intent = new Intent(TqgzglActivity.this, c);
//        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        intent.putExtra("typeId",typeid);
        intent.putExtra("partyOrgId",partyOrgId);
        intent.putExtra("isshow",isshow);
        intent.putExtra("className",className);
        startActivity(intent);
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(TqgzglActivity.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }

}