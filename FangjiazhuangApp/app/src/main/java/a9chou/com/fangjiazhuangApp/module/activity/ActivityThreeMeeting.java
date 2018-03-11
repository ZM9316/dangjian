package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.tencent.mm.opensdk.utils.Log;
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
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.module.activity.PartygroupActivity;
import a9chou.com.fangjiazhuangApp.module.dao.OrgId;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date: 2017/12/18.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class ActivityThreeMeeting extends BaseActivity {
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.dxzh)
    TextView mdxzh;
    @BindView(R.id.zbwyh)
    TextView mzbwyh;
    @BindView(R.id.zbdyh)
    TextView mzbdyh;
    @BindView(R.id.wdk)
    TextView mwdk;
    @BindView(R.id.wdkfb)
    TextView mwdkfb;
    @BindView(R.id.activity_threee_lssons)
    LinearLayout mActivityThreeeLssons;
    Unbinder unbinder;
    Unbinder unbinder1;
    private LoadingDailog mDialog;
    private String mUrl;
    private String i="";
    private String j="";
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private ArrayList<OrgId> partyOrgId=new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_threee_lssons;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityThreeMeeting.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        i=getIntent().getStringExtra("tit");
        j=getIntent().getStringExtra("bftit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.GONE);
        mEd=getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);
        if(j.endsWith("党支部")){
            mzbdyh.setVisibility(View.VISIBLE);
            mzbdyh.setText("支部党员大会");
            mzbwyh.setText("党支部委员会");
            mwdk.setText("党课");
            mwdkfb.setText("党课课件");
            mdxzh.setText("党小组会");
            mShykXz.setVisibility(View.GONE);
        }else if(j.endsWith("分工会")){
            mzbdyh.setVisibility(View.GONE);
            mwdk.setText("工运课堂");
            mwdkfb.setText("工运课件");
            mdxzh.setText("工会小组会");
            mzbwyh.setText("工会委员会");
            mShykXz.setVisibility(View.GONE);
        }else if(j.endsWith("团支部")){
            mzbdyh.setVisibility(View.VISIBLE);
            mzbdyh.setText("支部团员大会");
            mzbwyh.setText("团支部委员大会");
            mwdk.setText("团课");
            mwdkfb.setText("团课课件");
            mdxzh.setText("团小组会");
            mShykXz.setVisibility(View.GONE);
        }

//        if (SpPermissionUtil.returnX(getActivity()) == 1) {
//            mShykXz.setVisibility(View.VISIBLE);
//        } else {
//            mShykXz.setVisibility(View.GONE);
//        }
//        mUrl =
//                UrlUtil.FATHER_HTML + UrlUtil.SHYK +
//                        UrlUtil.getWithNo(getActivity()) +
//                        "&wp=" + SpPermissionUtil.getThreeLssonsWpPermission(getActivity()) +
//                        "&mt=" + SpPermissionUtil.getThreeLssonsMtPermission(getActivity());
//
//        Log.d(TAG, "initViews:murl" + mUrl);

//        //        设置支持js
//        WebViewUtils.setWebView(mDialog, mWeb, mUrl, getActivity(), "android");
//        //         获取webview中的标题，然后传到我的title中
//        WebChromeClient wvcc = new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                mShykTitleTitle.setText(title);
//                if (mWeb.canGoBack()) {
//                    mShykXz.setVisibility(View.GONE);
//                } else {
//                    if (SpPermissionUtil.returnX(getActivity()) == 1) {
//                        mShykXz.setVisibility(View.VISIBLE);
//                    } else {
//                        mShykXz.setVisibility(View.GONE);
//                    }
//                }
//            }
//        };
//        mWeb.setWebChromeClient(wvcc);
    }

    public void GetpartyOrgId(final String typeId){
        mMap.put("typeId",typeId);
        partyOrgId.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                ViseHttp.POST("mobile/sys/sysUser/personPartyAndGroup2")
                ViseHttp.POST("mobile/wp/wpMeeting/partyAndGroup2")
                        .addParams(mMap)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                partyOrgId.clear();
                                Log.i("zm9316","ActivityThreeMeeting:"+data);
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
                                        Log.i("zm9316",typeId);
                                        switch(typeId){
                                            case "0":
                                                switch(j){
                                                    case "综合党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//党支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "经营党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//党支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "工程党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//党支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "运行党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//党支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "设备党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//党支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "综合分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "经营分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "工程分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "运行分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "设备分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部党员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部党员大会
                                                            }
                                                        }
                                                        break;
                                                    case "综合团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部团员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部团员大会
                                                            }
                                                        }
                                                        break;
                                                    case "经营团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部团员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部团员大会
                                                            }
                                                        }
                                                        break;
                                                    case "工程团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部团员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部团员大会
                                                            }
                                                        }
                                                        break;
                                                    case "运行团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部团员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部团员大会
                                                            }
                                                        }
                                                        break;
                                                    case "设备团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"支部团员大会",j,"0",partyOrgId.get(i).getId(),false,"ATM");//支部团员大会
                                                            }
                                                        }
                                                        break;
                                                }
                                                break;
                                            case "1":
                                                Log.i("zm9316","j:"+j);
                                                switch(j){
                                                    case "综合党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//党支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "经营党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//党支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "工程党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//党支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "运行党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//党支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "设备党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//党支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "综合分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//工会委员会
                                                            }
                                                        }
                                                        break;
                                                    case "经营分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//工会委员会
                                                            }
                                                        }
                                                        break;
                                                    case "工程分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//工会委员会
                                                            }
                                                        }
                                                        break;
                                                    case "运行分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//工会委员会
                                                            }
                                                        }
                                                        break;
                                                    case "设备分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//工会委员会
                                                            }
                                                        }
                                                        break;
                                                    case "综合团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//团支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "经营团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//团支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "工程团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//团支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "运行团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//团支部委员会
                                                            }
                                                        }
                                                        break;
                                                    case "设备团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团支部委员会",j,"1",partyOrgId.get(i).getId(),false,"ATM");//团支部委员会
                                                            }
                                                        }
                                                        break;
                                                }
                                                break;
                                            case "2":
                                                switch(j){
                                                    case "综合党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//党小组会
                                                            }
                                                        }
                                                        break;
                                                    case "经营党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//党小组会
                                                            }
                                                        }
                                                        break;
                                                    case "工程党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//党小组会
                                                            }
                                                        }
                                                        break;
                                                    case "运行党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//党小组会
                                                            }
                                                        }
                                                        break;
                                                    case "设备党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//党小组会
                                                            }
                                                        }
                                                        break;
                                                    case "综合分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//工会小组会
                                                            }
                                                        }
                                                        break;
                                                    case "经营分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//工会小组会
                                                            }
                                                        }
                                                        break;
                                                    case "工程分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//工会小组会
                                                            }
                                                        }
                                                        break;
                                                    case "运行分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//工会小组会
                                                            }
                                                        }
                                                        break;
                                                    case "设备分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工会小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//工会小组会
                                                            }
                                                        }
                                                        break;
                                                    case "综合团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//团小组会
                                                            }
                                                        }
                                                        break;
                                                    case "经营团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//团小组会
                                                            }
                                                        }
                                                        break;
                                                    case "工程团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//团小组会
                                                            }
                                                        }
                                                        break;
                                                    case "运行团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//团小组会
                                                            }
                                                        }
                                                        break;
                                                    case "设备团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团小组会",j,"2",partyOrgId.get(i).getId(),false,"ATM");//团小组会
                                                            }
                                                        }
                                                        break;
                                                }
                                                break;
                                            case "3":
                                                switch(j){
                                                    case "综合党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//党课
                                                            }
                                                        }
                                                        break;
                                                    case "经营党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//党课
                                                            }
                                                        }

                                                        break;
                                                    case "工程党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//党课
                                                            }
                                                        }
                                                        break;
                                                    case "运行党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//党课
                                                            }
                                                        }
                                                        break;
                                                    case "设备党支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备党支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"党课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//党课
                                                            }
                                                        }
                                                        break;
                                                    case "综合分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工运课堂",j,"3",partyOrgId.get(i).getId(),false,"ATM");//工运课堂
                                                            }
                                                        }
                                                        break;
                                                    case "经营分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工运课堂",j,"3",partyOrgId.get(i).getId(),false,"ATM");//工运课堂
                                                            }
                                                        }
                                                        break;
                                                    case "工程分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工运课堂",j,"3",partyOrgId.get(i).getId(),false,"ATM");//工运课堂
                                                            }
                                                        }
                                                        break;
                                                    case "运行分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工运课堂",j,"3",partyOrgId.get(i).getId(),false,"ATM");//工运课堂
                                                            }
                                                        }
                                                        break;
                                                    case "设备分工会":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备分工会".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"工运课堂",j,"3",partyOrgId.get(i).getId(),false,"ATM");//工运课堂
                                                            }
                                                        }
                                                        break;
                                                    case "综合团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("综合团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//团课
                                                            }
                                                        }
                                                        break;
                                                    case "经营团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("经营团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//团课
                                                            }
                                                        }
                                                        break;
                                                    case "工程团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("工程团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//团课
                                                            }
                                                        }
                                                        break;
                                                    case "运行团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("运行团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//团课
                                                            }
                                                        }
                                                        break;
                                                    case "设备团支部":
                                                        for(int i=0;i<partyOrgId.size();i++){
                                                            if("设备团青支部".equals(partyOrgId.get(i).getName())){
                                                                jump(PartygroupActivity.class,"团课",j,"3",partyOrgId.get(i).getId(),false,"ATM");//团课
                                                            }
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                    }

                                }catch (Exception e){
                                    Log.i("zm9316",e+"");
                                }
                            }
                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
            }
        }).start();
    }

    @OnClick({R.id.shyk_left_back, R.id.dxzh,R.id.zbwyh,R.id.zbdyh,R.id.wdk,R.id.wdkfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                jump(MainActivity.class);
                break;
            case R.id.dxzh:
                GetpartyOrgId("2");
                break;
            case R.id.zbwyh:
                GetpartyOrgId("1");
                break;
            case R.id.zbdyh:
                GetpartyOrgId("0");
                break;
            case R.id.wdk:
                GetpartyOrgId("3");
                break;
            case R.id.wdkfb:
                if(j.endsWith("党支部")){
                    jump(ActivitySubjectH5.class,"党课课件",j);//党课课件
                }else if(j.endsWith("分工会")){
                    jump(ActivitySubjectH5.class,"工运课件",j);//工运课件
                }else if(j.endsWith("团支部")){
                    jump(ActivitySubjectH5.class,"团课课件",j);//团课课件
                }

                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityThreeMeeting.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
    private void jump(Class c,String tit,String bftit) {
        Intent i = new Intent(ActivityThreeMeeting.this, c);
        i.putExtra("tit", tit);
        i.putExtra("bftit", bftit);
        startActivity(i);
    }
    private void jump(Class c,String tit,String bftit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent i = new Intent(ActivityThreeMeeting.this, c);
        i.putExtra("tit", tit);
        i.putExtra("bftit", bftit);
        i.putExtra("typeId", typeid);
        i.putExtra("partyOrgId", partyOrgId);
        i.putExtra("isshow", isshow);
        i.putExtra("className", className);
        startActivity(i);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mWeb.reload();
//    }



}
