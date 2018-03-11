package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class JjjcglActivity extends BaseActivity {
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.zzjs)
    TextView mzzjs;
    @BindView(R.id.jwh)
    TextView mjwh;
    @BindView(R.id.jjjc)
    TextView mjjjc;
    @BindView(R.id.jdzr)
    TextView mjdzr;
//    @BindView(R.id.jdsj)
//    TextView mjdsj;
//    @BindView(R.id.xnjc)
//    TextView mxnjc;
//    @BindView(R.id.sjgl)
//    TextView msjgl;
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
        return R.layout.activity_jjjcgl;
    }

    @Override
    protected void initViews() {

        i=getIntent().getStringExtra("tit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
    }


    @OnClick({R.id.shyk_left_back,R.id.zzjs, R.id.jwh, R.id.jjjc, R.id.jdzr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.jjjc://党纪检查
                jumpNext(ActivitySubjectH5.class,"党纪检查");
                break;
//            case R.id.xnjc://效能监察
//                jumpNext(ActivityEffectivenessmonitor.class,"效能监察");
//                break;
//            case R.id.jdsj://监督视角
////                jumpNext(ActivityEffectivenessmonitor.class,"监督视角");
//                ToastUtils.showToast("监督视角！！！");
//                break;
            case R.id.jdzr://监督责任
                jumpNext(ActivitySubjectH5.class,"监督责任");
                break;
//            case R.id.sjgl://审计管理
//                jumpNext(SjglActivity.class);
//                break;
            case R.id.jwh://纪委办公会
//                jumpNext(XfglActivity.class);
//                ToastUtils.showToast("点击了纪委办公会！！！");
                loadDialog();
                Go("4");
//                jumpNext(PartygroupActivity.class,"纪委办公会",i,"4","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//支部委员会
                break;
            case R.id.zzjs://组织建设
                jumpNext(ActivityOrganization_construction.class,"组织建设",i);//纪委
//                ToastUtils.showToast("点击了组织建设！！！");
                break;

        }
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(JjjcglActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
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
                                                    if("纪建政工".equals(partyOrgId.get(s).getName())){
                                                        mDialog.dismiss();
                                                        jumpNext(PartygroupActivity.class,"纪委办公会",i,typeId,partyOrgId.get(s).getId(),true,"AAA");//支部委员会
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

    private void jumpNext(Class c) {
        Intent intent = new Intent(JjjcglActivity.this, c);
        intent.putExtra("tag", "3");
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag) {
        Intent intent = new Intent(JjjcglActivity.this, c);
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag,String tit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent intent = new Intent(JjjcglActivity.this, c);
//        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        intent.putExtra("typeId",typeid);
        intent.putExtra("partyOrgId",partyOrgId);
        intent.putExtra("isshow",isshow);
        intent.putExtra("className",className);
        startActivity(intent);
    }
    private void jumpNext(Class c,String Tag,String tit) {
        Intent intent = new Intent(JjjcglActivity.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
}