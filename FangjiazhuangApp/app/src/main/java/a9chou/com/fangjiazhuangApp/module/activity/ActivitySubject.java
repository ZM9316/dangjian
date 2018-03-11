package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.GridViewAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ActivitySubject extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.position_grid)
    GridView mGridView;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private LoadingDailog mDialog;
    private GridViewAdapter adapter;
    private ArrayList<String> mDatas;
//    @BindView(R.id.dwsj)
//    TextView mdwsj;
////    @BindView(R.id.zjl)
////    TextView mzjl;
//    @BindView(R.id.dwfsj)
//    TextView mdwfsj;
//    @BindView(R.id.ldbz)
//    TextView mldbz;
//    @BindView(R.id.zzgzb)
//    TextView mzzgzb;
//    @BindView(R.id.jjb)
//    TextView mjjb;
//    @BindView(R.id.zhdzb)
//    TextView mzhdzb;
//    @BindView(R.id.zhb)
//    TextView mzhb;
//    @BindView(R.id.gcdzb)
//    TextView mgcdzb;
//    @BindView(R.id.yxdzb)
//    TextView myxdzb;
//    @BindView(R.id.sbdzb)
//    TextView msbdzb;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_subject;
    }

    @Override
    protected void initViews() {
        Intent i=getIntent();
        mShykTitleTitle.setText(i.getStringExtra("tit"));
        mShykXz.setVisibility(View.GONE);
        Go();
    }

    public void Go(){
        mDatas=new ArrayList<>();
        mEd=getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);
        ViseHttp.POST("mobile/wp/WpResponsibilityPerson/orgList")
                .addParams(mMap)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {

                        try {
                            JSONObject o=new JSONObject(data);
                            JSONArray type = o.getJSONArray("data");
                            for(int i=0;i<type.length();i++){
                                JSONObject x = type.getJSONObject(i);
                                mDatas.add(x.getString("orgName"));
                                Log.i("zm9316",mDatas.get(i));
                            }

                            adapter=new GridViewAdapter(ActivitySubject.this,mDatas);
                            mGridView.setAdapter(adapter);
                            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    jump(ActivitySubjectH5.class,"主体责任");
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }
    public void GoX(){
        mDatas=new ArrayList<>();
        String data="{\n" +
                "\t\"data\": [{\n" +
                "\t\t\"orgId\": \"71f344679d0011e681d906d12201009b\",\n" +
                "\t\t\"orgName\": \"通用角色\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"1ad4c01fbfbb11e7b9a46212970bta1s\",\n" +
                "\t\t\"orgName\": \"综合党支部\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}, {\n" +
                "\t\t\"orgId\": \"5bb125c3d45611e7a6706212970bda1b\",\n" +
                "\t\t\"orgName\": \"党委书记\"\n" +
                "\t}],\n" +
                "\t\"type\": \"S\"\n" +
                "}";
        try {
            JSONObject o=new JSONObject(data);
            JSONArray type = o.getJSONArray("data");
            for(int i=0;i<type.length();i++){
                JSONObject x = type.getJSONObject(i);
                mDatas.add(x.getString("orgName"));
                Log.i("zm9316",mDatas.get(i));
            }

            adapter=new GridViewAdapter(ActivitySubject.this,mDatas);
            mGridView.setAdapter(adapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    jump(ActivitySubjectH5.class,"主体责任");
//                    ToastUtils.showToast(mDatas.get(position));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //,R.id.jydzb, R.id.gcdzb, R.id.yxdzb,R.id.sbdzb R.id.zjl,, R.id.dwsj,R.id.dwfsj, R.id.jwsj,R.id.ldbz, R.id.zzgzb, R.id.jjb, R.id.zhdzb,R.id.zhb
    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
//            case R.id.dwsj://党委书记
//                jump(ActivitySubjectH5.class,"党委书记");//党委书记
//                break;
//            case R.id.zhb://综合部
//                jump(ActivitySubjectH5.class,"综合部");//综合部
//                break;
//            case R.id.dwfsj://总经理及党委副书记
//                jump(ActivitySubjectH5.class,"总经理,党委副书记");//总经理及党委副书记
////                jump(ActivitySubjectH5.class,"党委副书记");//党委副书记
//                break;
//            case R.id.jwsj://纪委书记
//                jump(ActivitySubjectH5.class,"纪委书记");//纪委书记
//                break;
//            case R.id.ldbz://党委领导班子
//                jump(ActivitySubjectH5.class,"党委领导班子");//党委领导班子
//                break;
//            case R.id.zzgzb://政治工作部
//                jump(ActivitySubjectH5.class,"政治工作部");//政治工作部
//                break;
//            case R.id.jjb://监察审计部(纪检办)
//                jump(ActivitySubjectH5.class,"监察审计部(纪检办)");//监察审计部(纪检办)
//                break;
//            case R.id.zhdzb://各支部
//                jump(ActivitySubjectH5.class,"各支部");//各党支部
////                jump(ActivitySubjectH5.class,"综合党支部");//综合党支部
//                break;
//            case R.id.jydzb://经营党支部
//                jump(ActivitySubjectH5.class,"经营党支部");//经营党支部
//                break;
//            case R.id.gcdzb://工程党支部
//                jump(ActivitySubjectH5.class,"工程党支部");//工程党支部
//                break;
//            case R.id.yxdzb://运行党支部
//                jump(ActivitySubjectH5.class,"运行党支部");//运行党支部
//                break;
//            case R.id.sbdzb://设备党支部
//                jump(ActivitySubjectH5.class,"设备党支部");//设备党支部
//                break;
        }
    }

    private void jump(Class c,String tag) {
        Intent i = new Intent(ActivitySubject.this, c);
        i.putExtra("tit", tag);
        startActivity(i);
    }

}