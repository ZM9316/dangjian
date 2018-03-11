package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class PartygroupActivity extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.Should_open)
    TextView mShould_open;
    @BindView(R.id.It_opened)
    TextView mIt_opened;
    @BindView(R.id.textView2)
    TextView mtextView2;
    @BindView(R.id.textView11)
    TextView mtextView11;
    @BindView(R.id.textView14)
    TextView mtextView14;
    @BindView(R.id.imageView)
    ImageView mimageView;
    @BindView(R.id.id_recyclerview)
    RecyclerView mid_recyclerview;
    @BindView(R.id.pullToRefreshLayout)
    PullToRefreshLayout mpullToRefreshLayout;
    @BindView(R.id.huiyiliebiao_rc)
    LinearLayout mhuiyiliebiao_rc;
    @BindView(R.id.Statistic)
    RelativeLayout mStatistic;
    @BindView(R.id.no_net)
    LinearLayout mNoNet;
    @BindView(R.id.jiazai)
    Button mjiazai;
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
    private List<BbsjBean> list2 = new ArrayList<>();
    private int number=0;
    private String mettId;
    private String className;
    private Boolean isShow;
    private ThreeLssonsAdapter adapter;
    private ArrayList<String> partyOrgId=new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_huiyi;
    }

    @Override
    protected void initViews() {

        loadDialog();
        mShykXz.setText(" ");
        i=getIntent().getStringExtra("tit");
        j=getIntent().getStringExtra("typeId");
        k=getIntent().getStringExtra("partyOrgId");
        l=getIntent().getStringExtra("bftit");
        className=getIntent().getStringExtra("className");
        isShow=getIntent().getBooleanExtra("isshow",true);
        mShykTitleTitle.setText(i);
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
                        list2.clear();
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
//                        if(list.size()%10==0){
//                            int s=number++;
                        list.clear();
                        list2.clear();
                            mMap.put("pageIndex", number+"");

//                        }
                        Log.i("zm9316","s:"+number);
                        Go();
                        mpullToRefreshLayout.finishLoadMore();
//                        adapter.notifyDataSetChanged();
                    }
                },1000);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Go();
            }
        }).start();

    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(PartygroupActivity.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    private void Go(){

        ViseHttp.
//                POST("mobile/wp/wpMeeting/data").
                POST("mobile/wp/wpMeeting/allDatas").
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
                            int q=0;
                            for(;q<type.length();q++){
                                JSONObject x=type.getJSONObject(q);
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
                                list.add(new BbsjBean(hashMap.get("title"),hashMap.get("startTime"), hashMap.get("address"), hashMap.get("status"),i));
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener(){
                                @Override
                                public void onItemChildClick(final BaseQuickAdapter adapter, View view,final int position){
                                    mBtn = (Button) adapter.getViewByPosition(mid_recyclerview, position + 1, R.id.fbbtn);

                                    switch (view.getId()){
                                        case R.id.hymc:
                                            Log.i("zm9316", (String) IsList.get(position)+":::"+position+":::"+IsList.size());
                                            jump(ActivityShowInfo.class,IsList.get(position),j,k,i,l,className,list.get(position).getBtnvalue());
                                            break;
                                        case R.id.fbbtn:
                                            Log.i("zm9316",(String) IsList.get(position));
                                            jump(ActivityShowInfo.class,IsList.get(position),j,k,i,l,className,list.get(position).getBtnvalue());
                                            break;
                                        case R.id.member_btn:
                                            Log.i("zm9316",(String) IsList.get(position));
                                            jump(SignInActivity.class,IsList.get(position),j,k,i,l,className,list.get(position).getBtnvalue());
                                            Log.i("zm9316",IsList.get(position));
                                            break;
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            Log.i("zm9316","err");
                            mDialog.dismiss();
                            mid_recyclerview.setVisibility(View.GONE);
                            mNoNet.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }finally {
                            if(list.size()!=0){
                                for(int s=0;s<list.size();s++){
                                    if("0".equals(list.get(s).getBtnvalue())){
                                        list2.add(list.get(s));
                                    }
                                }
                            }
                            switch (i){
                                case "党小组会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "党支部委员会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "支部委员会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "支部党员大会":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "党支部党员大会":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "党课":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "团小组会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "团支部委员大会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "团支部委员会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "支部团员大会":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "团课":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "工会小组会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "工会委员会":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "工运课堂":
                                    mShould_open.setText("4");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                case "集体学习":
                                    mShould_open.setText("12");
                                    mIt_opened.setText(list2.size()+"");
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mStatistic.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    mShould_open.setText("12");
                                    mShould_open.setVisibility(View.VISIBLE);
                                    mtextView2.setVisibility(View.VISIBLE);
                                    mtextView11.setVisibility(View.VISIBLE);
                                    mtextView14.setVisibility(View.VISIBLE);
                                    mimageView.setVisibility(View.VISIBLE);
                                    mIt_opened.setText(list2.size()+"");
//                                    mStatistic.setVisibility(View.GONE);
                                    break;
                            }
                            mDialog.dismiss();
                            number++;
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
//    private void loadDialog() {
//        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(PartygroupActivity.this)
//                .setMessage("加载中...")
//                .setCancelable(true);
//        mDialog = loadBuilder.create();
//        mDialog.show();
//    }

    @OnClick({R.id.shyk_left_back, R.id.shyk_xz,R.id.jiazai,R.id.Statistic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.shyk_xz:
                loadDialog();
                if("AAA".equals(className)){
                    mShykXz.setEnabled(true);
                    jump(ActivityInfo.class,j,k,i,l);
                    Log.i("zm9316",className);
                    mDialog.dismiss();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            GetpartyOrgId(j);
                        }
                    }).start();

                }

                break;
            case R.id.Statistic:
                switch (i){
                    case "党小组会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "党支部委员会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "支部委员会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "党支部党员大会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "支部党员大会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "党课":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "团小组会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "团支部委员会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "团支部委员大会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "支部团员大会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "团课":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "工会小组会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "工会委员会":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    case "工运课堂":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,4,list2.size(),4-list2.size());
                        break;
                    case "集体学习":
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                    default:
//                        mStatistic.setEnabled(false);
                        jumpNext(ActivitySubjectH5.class,"统计分析",l,12,list2.size(),12-list2.size());
                        break;
                }

                break;
            case R.id.jiazai:
                if (NetUtil.isNetworkAvailable(PartygroupActivity.this)) {
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

    public void GetpartyOrgId(final String typeId){
        mMap.put("orgAnId",k);
//        ViseHttp.POST("mobile/sys/sysUser/personPartyAndGroup2")
        ViseHttp.POST("mobile/wp/wpMeeting/getPartyBranchByName")
                .addParams(mMap)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
//                        partyOrgId.clear();
                        Log.i("zm9316","ThreeLssonsFragment::"+data);
                        try {
                            JSONObject o = new JSONObject(data);
                            if("true".equals(o.getString("data"))){
//                                mShykXz.setEnabled(true);
//                                    Log.i("zm9316","网络获取的：：：："+partyOrgId+"传过来的：：：：："+k);
                                    jump(ActivityInfo.class,j,k,i,l);
                                    mDialog.dismiss();
                            }else{
                                mDialog.dismiss();
                                ToastUtils.showToast("您没有权限创建此会议！");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        HashMap<String,String>  hashMap=new HashMap<>();
//                        try{
//                            JSONObject o = new JSONObject(data);
//                            if(o.has("data")){
//                                JSONArray type = o.getJSONArray("data");
//                                for(int i=0;i<type.length();i++){
//                                    JSONObject x = type.getJSONObject(i);
//                                    Iterator iterator = x.keys();
//                                    while(iterator.hasNext()){
//                                        String key = iterator.next() + "";
//                                        if(key.equals("id")){
//                                            hashMap.put("id",x.getString("id"));
//                                        }
//                                    }
//                                    partyOrgId.add(hashMap.get("id"));
//                                }
//                                if(k.equals(partyOrgId.get(0))){
////                                    mShykXz.setEnabled(true);
//                                    Log.i("zm9316","网络获取的：：：："+partyOrgId+"传过来的：：：：："+k);
//                                    jump(ActivityInfo.class,j,k,i,l);
//                                    mDialog.dismiss();
//                                }else{
////                                    mShykXz.setEnabled(false);
//                                    mDialog.dismiss();
//                                    ToastUtils.showToast("您没有权限创建此会议！");
//                                }
//                            }else{
//                                mDialog.dismiss();
//                                ToastUtils.showToast(o.getString("message")+"无法创建会议");
//                            }
//
//
//                        }catch (Exception e){
//
//                        }
//                        finally {
//
//                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }

    private void jump(Class c) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
//        i.putExtra("listids",Ids);
        i.putExtra("id",Ids);
        startActivity(i);
        finish();
    }
    private void jump(Class c,String Ids,String typeid) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
        i.putExtra("listids",Ids);
        startActivity(i);
        finish();
    }
    private void jumpNext(Class c,String tit,String bftit,int all,int num,int all_num) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        i.putExtra("all",all);
        i.putExtra("num",num);
        i.putExtra("all_num",all_num);
        startActivity(i);
    }
    private void jump(Class c,String Ids,String typeid,String partyOrgId,String tit,String bftit,String classname,String btnvalue) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("id",Ids);
        i.putExtra("listids","");
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        i.putExtra("className",classname);
        i.putExtra("btnvalue",btnvalue);
        startActivity(i);
        finish();
    }
    private void jump(Class c,String typeid,String partyOrgId,String tit,String bftit) {
        Intent i = new Intent(PartygroupActivity.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId",typeid);
        i.putExtra("partyOrgId",partyOrgId);
        i.putExtra("tit",tit);
        i.putExtra("bftit",bftit);
        startActivity(i);
        finish();
    }

}
