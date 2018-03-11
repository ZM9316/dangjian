package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.ThreeLssonsAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zm9316 on 2017/12/22.
 */

public class ActivityStatisticalAnalysis extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.id_recyclerview)
    RecyclerView mid_recyclerview;
    @BindView(R.id.pullToRefreshLayout)
    PullToRefreshLayout mpullToRefreshLayout;
    @BindView(R.id.huiyiliebiao_rc)
    LinearLayout mhuiyiliebiao_rc;
    @BindView(R.id.no_net)
    LinearLayout mNoNet;
    @BindView(R.id.jiazai)
    Button mjiazai;
    @BindView(R.id.Should_open)
    TextView mShould_open;
    @BindView(R.id.It_opened)
    TextView mIt_opened;
    @BindView(R.id.Statistic)
    RelativeLayout mStatistic;
    private LoadingDailog mDialog;
    private Button mBtn;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private String i="";
    private String j="";
    private String k="";
    private String l="";
    private ArrayList<String> IsList=new ArrayList<>();
    private List<BbsjBean> list = new ArrayList<>();
    private int number=0;
    private ThreeLssonsAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_statistical_analysis;
    }

    @Override
    protected void initViews() {

        loadDialog();
        i=getIntent().getStringExtra("tit");
        j=getIntent().getStringExtra("typeId");
        k=getIntent().getStringExtra("partyOrgId");
        l=getIntent().getStringExtra("bftit");
        mShykTitleTitle.setText(i);
        mShykXz.setVisibility(View.VISIBLE);
        mid_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mid_recyclerview.setItemAnimator(new DefaultItemAnimator());
        mEd=getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);
        mMap.put("pageSize", "10");
        mMap.put("pageIndex", number+"");
        mMap.put("typeId", j);
        mMap.put("partyOrgId",k);
        adapter = new ThreeLssonsAdapter(R.layout.activity_threelssons_item,list);
        mid_recyclerview.setAdapter(adapter);
        mpullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        number=0;
                        mMap.put("pageIndex", number+"");
                        Go();
                        mpullToRefreshLayout.finishRefresh();
//                        adapter.notifyDataSetChanged();
                    }
                },1000);
            }

            @Override
            public void loadMore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(list.size()/10==0){
                            int s=number++;
                            mMap.put("pageIndex", s+"");
                        }
                        Go();
                        mpullToRefreshLayout.finishLoadMore();
//                        adapter.notifyDataSetChanged();
                    }
                },1000);
            }
        });
        Go();
    }
    private void Go(){

        ViseHttp.
                POST("mobile/wp/wpMeeting/data").
//                POST("mobile/wp/wpMeeting/allDatas").
//                POST("mobile/wp/wpMeeting/allData").
        addParams(mMap).
                request(new ACallback<String>(){
                    @Override
                    public void onSuccess(String data) {
                        Log.i("zm9316",data);
                        HashMap<String,String> hashMap = new HashMap<String,String>();
                        HashMap<String,String> hashMapId = new HashMap<String,String>();
                        try {
                            JSONObject o = new JSONObject(data);
                            JSONArray type = o.getJSONArray("data");
                            int i=0;
                            for(;i<type.length();i++){
                                JSONObject x=type.getJSONObject(i);
                                Iterator iterator = x.keys();                       // joData是JSONObject对象
                                while(iterator.hasNext()){
                                    String key = iterator.next() + "";
                                    if(key.equals("title")||key.equals("startTime")||key.equals("address")||key.equals("status")){
                                        hashMap.put(key,x.getString(key));
                                    }
                                    if(key.equals("id")){
                                        hashMapId.put(key,x.getString(key));
                                        Log.i("zm9316","Ids:"+x.getString(key));
                                        IsList.add(x.getString(key));
                                    }
                                }
                                list.add(new BbsjBean(hashMap.get("title"),hashMap.get("startTime"), hashMap.get("address"), hashMap.get("status")));
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener(){
                                @Override
                                public void onItemChildClick(final BaseQuickAdapter adapter, View view,final int position){
                                    mBtn = (Button) adapter.getViewByPosition(mid_recyclerview, position + 1, R.id.fbbtn);

                                    switch (view.getId()){
                                        case R.id.hymc:
                                            Log.i("zm9316", (String) IsList.get(position)+":::"+position+":::"+IsList.size());
                                            jump(ActivityShowInfo.class,IsList.get(position),j,k);
                                            break;
                                        case R.id.fbbtn:
                                            Log.i("zm9316",(String) IsList.get(position));
                                            jump(ActivityShowInfo.class,IsList.get(position),j,k);
                                            break;
                                        case R.id.member_btn:
                                            Log.i("zm9316",(String) IsList.get(position));
                                            jump(SignInActivity.class,IsList.get(position));
                                            break;
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            Log.i("zm9316","err");
                            mDialog.dismiss();
                            mid_recyclerview.setVisibility(View.GONE);
                            mStatistic.setVisibility(View.GONE);
                            mNoNet.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }finally {
                            mDialog.dismiss();
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.i("zm9316","pg:fail"+errCode+errMsg);
                        mDialog.dismiss();
                        mid_recyclerview.setVisibility(View.GONE);
                        mStatistic.setVisibility(View.GONE);
                        mNoNet.setVisibility(View.VISIBLE);
                    }
                });
    }





    //    @Override
//    public void onResume(){
//
//    }
    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityStatisticalAnalysis.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    @OnClick({R.id.shyk_left_back, R.id.shyk_xz,R.id.jiazai,R.id.Statistic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.shyk_xz:
                jump(ActivityInfo.class,"",j,k,i,l);
                break;
            case R.id.Statistic:
                jump(ActivitySubjectH5.class,i);
                break;
            case R.id.jiazai:
                if (NetUtil.isNetworkAvailable(ActivityStatisticalAnalysis.this)) {
                    loadDialog();
                    Go();
                    mid_recyclerview.setVisibility(View.VISIBLE);
                    mStatistic.setVisibility(View.VISIBLE);
                    mNoNet.setVisibility(View.GONE);
                } else {
                    ToastUtils.showToast("当前网络不可用,请检查网络");
                }
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityStatisticalAnalysis.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids) {
        Intent i = new Intent(ActivityStatisticalAnalysis.this, c);
        i.putExtra("tag", "1");
        i.putExtra("listids",Ids);
        i.putExtra("tit",Ids);
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids,String typeid) {
        Intent i = new Intent(ActivityStatisticalAnalysis.this, c);
        i.putExtra("tag", "1");
        i.putExtra("listids",Ids);
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids,String typeid,String partyOrgId) {
        Intent i = new Intent(ActivityStatisticalAnalysis.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("listids",Ids);
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids,String typeid,String partyOrgId,String tit,String bftit) {
        Intent i = new Intent(ActivityStatisticalAnalysis.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("listids",Ids);
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        startActivity(i);
        finish();
    }

}
