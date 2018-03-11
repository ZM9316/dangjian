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

public class GhgzglActivity extends BaseActivity {


    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.ldbh)
    TextView mLdbh;
    @BindView(R.id.wthd)
    TextView mWthd;
    @BindView(R.id.bzgl)
    TextView mbzgl;
    @BindView(R.id.mzgl)
    TextView mMzgl;
    @BindView(R.id.zzjs)
    TextView mzzjs;
//    @BindView(R.id.fghgl)
//    TextView mfghgl;
    @BindView(R.id.ghbgh)
    TextView mghbgh;
    private ArrayList<OrgId> partyOrgId=new ArrayList<>();
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private LoadingDailog mDialog;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_ghgzgl;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("工会管理");
        mShykXz.setVisibility(View.GONE);
    }

    @OnClick({R.id.shyk_left_back, R.id.ghbgh,R.id.ldbh, R.id.wthd, R.id.bzgl, R.id.mzgl, R.id.zzjs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.ldbh://素质提升
                jump(ActivityQualityImprovement.class,"素质提升");
                break;
            case R.id.wthd://靓彩女工
                jump(ActivityBeautifulWoman.class,"靓彩女工");
                break;
            case R.id.bzgl://班组管理
                jump(ActivitySubjectH5.class,"班组管理");
                break;
            case R.id.mzgl://民主管理
                jump(ActivityDemocraticManagement.class,"民主管理");
                break;
//            case R.id.fghgl://分工会管理
//                jump(ActivityTradeUnionManagement.class,"分工会管理");
//                break;
            case R.id.ghbgh://办公会
//                ToastUtils.showToast("点击了办公会！！！");
                loadDialog();
                Go("4");
//                jump(PartygroupActivity.class,"工会办公会"," ","4","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//支部委员会
                break;
            case R.id.zzjs://组织建设
                jump(ActivityOrganization_construction.class,"组织建设","工会管理");//纪委
                break;
        }
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(GhgzglActivity.this)
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
                                                    if("工建政工".equals(partyOrgId.get(s).getName())){
                                                        mDialog.dismiss();
                                                        jump(PartygroupActivity.class,"办公会","  ",typeId,partyOrgId.get(s).getId(),true,"AAA");//支部委员会
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
        Intent i = new Intent(GhgzglActivity.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }

    private void jump(Class c,String Tag) {
        Intent intent = new Intent(GhgzglActivity.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        startActivity(intent);
    }
    private void jump(Class c,String Tag,String tit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent intent = new Intent(GhgzglActivity.this, c);
//        intent.putExtra("tag", "0");
        intent.putExtra("bftit",tit);
        intent.putExtra("tit",Tag);
        intent.putExtra("typeId",typeid);
        intent.putExtra("partyOrgId",partyOrgId);
        intent.putExtra("isshow",isshow);
        intent.putExtra("className",className);
        startActivity(intent);
    }
    private void jump(Class c,String Tag,String tit) {
        Intent intent = new Intent(GhgzglActivity.this, c);
        intent.putExtra("tag", "0");
        intent.putExtra("tit",Tag);
        intent.putExtra("bftit",tit);
        startActivity(intent);
    }
}