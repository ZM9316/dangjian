package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class DzzglActivity extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dwh)
    TextView mdwh;
    @BindView(R.id.zxzxx)
    TextView mzxzxx;
    @BindView(R.id.ztzr)
    TextView mztzr;
    @BindView(R.id.dwgkl)
    TextView mdwgk;
    @BindView(R.id.dyxx)
    TextView mdyxx;
    @BindView(R.id.zzgxxx)
    TextView mzzgxxx;
    @BindView(R.id.dyldxx)
    TextView mdyldxx;
    @BindView(R.id.jjfz)
    TextView mjjfz;
//    @BindView(R.id.dfsj)
//    TextView mdfsj;
//    @BindView(R.id.xsjw)
//    TextView mxsjw;
    @BindView(R.id.bzjs)
    TextView mbzjs;
    @BindView(R.id.activity_more)
    LinearLayout mActivityMore;
    private String i="";
    private ArrayList<OrgId> partyOrgId=new ArrayList<>();
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private LoadingDailog mDialog;



    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dzzgl;
    }

    @Override
    protected void initViews() {
        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
//        mxsjw.setVisibility(View.GONE);

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
                                                    if("党建政工".equals(partyOrgId.get(s).getName())){
                                                        jumpNext(PartygroupActivity.class,"党委办公会",i,typeId,partyOrgId.get(s).getId(),true,"AAA");//支部委员会
                                                    }
                                                }
                                                break;
                                            case "5":
                                                for(int s=0;s<partyOrgId.size();s++){
                                                    if("党建政工".equals(partyOrgId.get(s).getName())){
                                                        jumpNext(PartygroupActivity.class,"集体学习",i,typeId,partyOrgId.get(s).getId(),true,"AAA");//支部委员会
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


    @OnClick({R.id.shyk_left_back,R.id.bzjs,R.id.jjfz,R.id.dwh, R.id.zxzxx,R.id.ztzr,R.id.dwgkl,R.id.dyxx,R.id.zzgxxx,R.id.dyldxx})
    protected void onViewClicked(View view){
        switch (view.getId()){
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.dyxx://党员信息
                jumpNext(DyxxActivity.class);
                break;
            case R.id.bzjs://组织建设
                jumpNext(ActivityOrganization_construction.class,"组织建设",i);
                break;
//            case R.id.dfsj://党费收缴
//                jumpNext(DyxxActivity.class);
//                break;
            case R.id.ztzr://主体责任
                jumpNext(ActivitySubject.class,"主体责任");
                break;
            case R.id.dwgkl://党务公开
                jumpNext(ActivitySubjectH5.class,"党务公开");
                break;
            case R.id.dwh://党委办公会
//                jumpNext(DyxxActivity.class);
                loadDialog();
                Go("4");
                break;
            case R.id.zxzxx://集体学习
//                jumpNext(DyxxActivity.class);
//                ToastUtils.showToast("点击了集体学习！！！");
                loadDialog();
                Go("5");

                break;
            case R.id.zzgxxx://党员接转
//                jumpNext(ActivityPartytransferList.class,"党员接转");
                jumpNext(ActivitySubjectH5.class,"党员接转");
                break;
            case R.id.dyldxx://流动党员
//                jumpNext(ActivityFlowmenber.class,"流动党员");
                jumpNext(ActivitySubjectH5.class,"流动党员");

                break;
            case R.id.jjfz://积极分子
//                jumpNext(DyldxxActivity.class);
//                jumpNext(ActivityFlowmenber.class,"积极分子");
                jumpNext(ActivitySubjectH5.class,"积极分子");
                break;
//            case R.id.shyk://三会一课
//                jumpNext(ActivityThreeSessions.class,"三会一课");
//                break;
//            case R.id.zbgl://支部管理
//                jumpNext(ActivityBranchManagement.class,"支部管理");
//                break;
//            case R.id.xsjw://学思践悟
////                jumpNext(OrganizationPictureActivity.class,"党委委员");
//                jumpNext(ActivityUnderstanding.class,"学思践悟");
//                ToastUtils.showToast("点击了学思践悟！！！");
//                break;
        }
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(DzzglActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    private void jumpNext(Class c) {
        Intent intent = new Intent(DzzglActivity.this, c);
        intent.putExtra("tag", "0");
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag,String tit) {
        Intent intent = new Intent(DzzglActivity.this, c);
//        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag,String tit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent intent = new Intent(DzzglActivity.this, c);
//        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        intent.putExtra("typeId",typeid);
        intent.putExtra("partyOrgId",partyOrgId);
        intent.putExtra("isshow",isshow);
        intent.putExtra("className",className);
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag) {
        Intent intent = new Intent(DzzglActivity.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
}