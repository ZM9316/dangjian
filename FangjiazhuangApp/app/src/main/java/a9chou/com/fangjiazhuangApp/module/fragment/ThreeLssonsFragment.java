package a9chou.com.fangjiazhuangApp.module.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
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
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityShowInfo;
import a9chou.com.fangjiazhuangApp.module.activity.ActivitySubjectH5;
import a9chou.com.fangjiazhuangApp.module.activity.PartygroupActivity;
import a9chou.com.fangjiazhuangApp.module.dao.OrgId;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.vise.utils.handler.HandlerUtil.runOnUiThread;

/**
 * date: 2017/12/18.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class ThreeLssonsFragment extends BaseFragment {
    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.search)
    ImageView mSearch;
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
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    Unbinder unbinder;
    Unbinder unbinder1;
    private LoadingDailog mDialog;
    private String mUrl;
    private String typeId;
    private String bftit;
    private ArrayList<OrgId> partyOrgId=new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_three_meeting;
    }

    @Override
    protected void initViews() {
//        loadDialog();
        mTitleTitle.setText("三会一课");
        mTitleLd.setVisibility(View.GONE);
        mSearch.setVisibility(View.GONE);
        mEd=getActivity().getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);


    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    public void GetpartyOrgId(final String typeId){
        loadDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMap.put("typeId","1");
//        ViseHttp.POST("mobile/sys/sysUser/PartyAndGroup2")
                ViseHttp.POST("mobile/wp/wpMeeting/personPartyAndGroup")
//                ViseHttp.POST("mobile/wp/wpMeeting/getPartyBranchByName")
                        .addParams(mMap)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                partyOrgId.clear();
                                Log.i("zm9316","ThreeLssonsFragment:"+data);
                                HashMap<String,String>  hashMap=new HashMap<>();
                                try{
                                    JSONObject o = new JSONObject(data);
                                    if(o.has("data")){
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
                                        }
                                    }
                                    else{
                                        mDialog.dismiss();
//                                        ToastUtils.showToast("未获取到您所在党支部信息！");
                                    }
                                    switch(typeId){
                                        case "0":
                                            mDialog.dismiss();
                                            jump(PartygroupActivity.class,mzbdyh.getText().toString(),"0",partyOrgId.get(0).getId(),true,"TLF");//支部党员大会
                                            break;
                                        case "1":
                                            mDialog.dismiss();
                                            jump(PartygroupActivity.class,mzbwyh.getText().toString(),"1",partyOrgId.get(0).getId(),true,"TLF");//支部委员会
                                            break;
                                        case "2":
                                            mDialog.dismiss();
                                            jump(PartygroupActivity.class,mdxzh.getText().toString(),"2",partyOrgId.get(0).getId(),true,"TLF");//党小组会
                                            break;
                                        case "3":
                                            mDialog.dismiss();
                                            jump(PartygroupActivity.class,mwdk.getText().toString(),"3",partyOrgId.get(0).getId(),true,"TLF");//党小组会
                                            break;
                                    }
                                }catch (Exception e){

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

    @OnClick({ R.id.dxzh,R.id.zbwyh,R.id.zbdyh,R.id.wdk,R.id.wdkfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dxzh:
                jump(PartygroupActivity.class,mdxzh.getText().toString(),"2","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//党小组会
                break;
            case R.id.zbwyh:
                jump(PartygroupActivity.class,mzbwyh.getText().toString(),"1","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//支部委员会
                break;
            case R.id.zbdyh:
                jump(PartygroupActivity.class,mzbdyh.getText().toString(),"0","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//支部党员大会
                break;
            case R.id.wdk:
                jump(PartygroupActivity.class,mwdk.getText().toString(),"3","1ad4c01fbfbb11e7b9a46212970bta1s",true,"TLF");//党小组会
                break;
            case R.id.wdkfb:
                jump(ActivitySubjectH5.class,"党课课件","综合党支部");//党课课件
                break;

        }
    }

    private void jump(Class c) {
        Intent i = new Intent(getActivity(), c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
    private void jump(Class c,String tit,String bftit) {
        Intent i = new Intent(getActivity(), c);
        i.putExtra("tit", tit);
        i.putExtra("bftit", bftit);
        startActivity(i);
    }
    private void jump(Class c,String tit,String typeid,String partyOrgId,Boolean isshow,String className) {
        Intent i = new Intent(getActivity(), c);
        i.putExtra("typeId", typeid);
        i.putExtra("partyOrgId", partyOrgId);
        i.putExtra("tit",tit);
        i.putExtra("isshow",isshow);
        i.putExtra("className",className);
        startActivity(i);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mWeb.reload();
//    }



}
