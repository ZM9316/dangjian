package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
import a9chou.com.fangjiazhuangApp.adapter.MenberAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class ActivityAttendMenberList extends BaseActivity{

    @BindView(R.id.big_events_left_back)
    TextView mbig_events_left_back;
    @BindView(R.id.big_events_title_title)
    TextView mbig_events_title_title;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.search_abolish)
    TextView msearch_abolish;
    @BindView(R.id.big_events_search_iv)
    ImageView mbig_events_search_iv;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.id_recyclerview)
    RecyclerView mid_recyclerview;
    @BindView(R.id.huiyiliebiao_rc)
    LinearLayout mhuiyiliebiao_rc;
//    @BindView(R.id.pullToRefreshLayout)
//    PullToRefreshLayout mpullToRefreshLayout;
    private LoadingDailog mDialog;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private Map<String, String> mmMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private CheckBox CB;
    private List<BbsjBean> list = new ArrayList<>();
    private List<BbsjBean> list2 = new ArrayList<>();
    private MenberAdapter adapter;
    private String typeId="";
    private String tit="";
    private String partyOrgId="";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_menber_list;
    }

    @Override
    protected void initViews() {
        loadDialog();
        mbig_events_title_title.setText("人员选择");
        mShykXz.setText("添加");
        mShykXz.setVisibility(View.VISIBLE);
        typeId=getIntent().getStringExtra("typeId");
        tit=getIntent().getStringExtra("tit");
        partyOrgId=getIntent().getStringExtra("partyOrgId");
        mid_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenberAdapter(R.layout.menber_list_item, list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CB= (CheckBox) adapter.getViewByPosition(mid_recyclerview,position,R.id.menber_list_cb);
                BbsjBean bb=new BbsjBean();
                Log.i("zm9316","BbsjBean："+CB.isChecked()+"");
                list.get(position).setChecked(CB.isChecked());
            }
        });
        mBigEventsSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("zm9316", "onTextChanged: "+charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                search();
            }
        });
//        mpullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
//            @Override
//            public void refresh() {
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        list.clear();
////                        list2.clear();
//////                        number=0;
//////                        mMap.put("pageIndex", number+"");
////                        Go();
//                        mpullToRefreshLayout.finishRefresh();
////                        adapter.notifyDataSetChanged();
//                    }
//                },500);
//            }
//
//            @Override
//            public void loadMore() {
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//////                        if(list.size()%10==0){
//////                            int s=number++;
////                        list.clear();
////                        list2.clear();
//////                        mMap.put("pageIndex", number+"");
//////
////////                        }
//////                        Log.i("zm9316","s:"+number);
////                        Go();
//                        mpullToRefreshLayout.finishLoadMore();
////                        adapter.notifyDataSetChanged();
//                    }
//                },500);
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Go();
            }
        }).start();

    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityAttendMenberList.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    private void Go(){
        mEd=getSharedPreferences("config",MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        if("53c435ca02a611e8b47f6212970bda1b".equals(partyOrgId)
                ||"0e15612102a611e8b47f6212970bda1b".equals(partyOrgId)
                ||"a8e19d8e02a511e8b47f6212970bda1b".equals(partyOrgId)
                ||"76af0d6202a511e8b47f6212970bda1b".equals(partyOrgId)){
            mmMap = new HashMap<>();
            mmMap.put("userId", mUserId);
            mmMap.put("token", mToken);
            mmMap.put("userName", mUserName);
            switch (tit){
                case "党委办公会":
                    mmMap.put("types", "6");
                    break;
//                case "集体学习":
//                    mMap.put("types", "2");
//                    break;
//                case "纪委办公会":
//                    mMap.put("types", "3");
//                    break;
//                case "办公会":
//                    mMap.put("types", "4");
//                    break;
//                case "团委会":
//                    mMap.put("types", "5");
//                    break;
            }
            ViseHttp.POST("mobile/wp/wpFlow/getPersonDatas")
                    .addParams(mmMap)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.i("zm9316","success"+data);
                            HashMap<String,String> hashMap = new HashMap<String,String>();
//                            list.clear();
                            List<BbsjBean> listt = new ArrayList<>();
                            list2.clear();
                            JSONObject o = null;
                            try {
                                o = new JSONObject(data);
                                if(o.has("data")){
                                    JSONArray type = o.getJSONArray("data");
                                    Log.i("zm9316","success:"+type.toString());
                                    for(int i=0;i<type.length();i++){

                                        JSONObject x=type.getJSONObject(i);
                                        Iterator iterator = x.keys();
                                        while(iterator.hasNext()){
                                            String key = iterator.next() + "";
                                            if(key.equals("name")){
                                                hashMap.put(key,x.getString(key));
                                            }
                                            if(key.equals("userId")){
                                                hashMap.put(key,x.getString(key));
                                            }
                                        }
                                        listt.add(new BbsjBean(hashMap.get("name"),hashMap.get("userId")));
                                        list2.add(new BbsjBean(hashMap.get("name"),hashMap.get("userId")));
                                    }
                                    adapter.addData(listt);
                                    mid_recyclerview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    mDialog.dismiss();

                                } else{
                                    if(o.has("message")){
                                        ToastUtils.showToast(o.getString("message"));
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }else{
            mMap = new HashMap<>();
            mMap.put("userId", mUserId);
            mMap.put("token", mToken);
            mMap.put("userName", mUserName);
            mMap.put("typeId", typeId);
            if("4".equals(typeId)||"5".equals(typeId)){
                mMap.put("orgAnId", partyOrgId);
            }else{
                mMap.put("orgAnId", partyOrgId);
            }
//        mMap.put("dutiesId", "1,2,3,4,5");

            ViseHttp.POST("mobile/wp/wpMeeting/getPersonListByorgAnId").
                    addParams(mMap).
                    request(new ACallback<String>() {

                        @Override
                        public void onSuccess(String data) {
                            Log.i("zm9316","success"+data);
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            List<BbsjBean> listt = new ArrayList<>();
                            list2.clear();
                            try {
                                JSONObject o = new JSONObject(data);
                                JSONArray type = o.getJSONArray("data");
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
                                    listt.add(new BbsjBean(hashMap.get("name"),hashMap.get("id")));
                                    list2.add(new BbsjBean(hashMap.get("name"),hashMap.get("id")));
                                }
                                adapter.addData(listt);
                                mid_recyclerview.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                mDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }

    }

    private void search() {
        String data = mBigEventsSearchEdit.getText().toString().trim();
        list.clear();

        for (int i = 0; i < list2.size(); i++) {
            BbsjBean studentBean = list2.get(i);
            if(studentBean.getPersonName().contains(data) ){
                list.add(studentBean);
            }
        }

        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.big_events_left_back, R.id.shyk_xz,R.id.big_events_search_iv,R.id.search_abolish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.big_events_left_back:
                finish();
                break;
            case R.id.big_events_search_iv:
                SearchUtil.showSearch(mBigEventsSearchEdit, this, mBigEventsTitleRelative, mSearchLinear);
                break;
            case R.id.search_abolish:
                SearchUtil.abolishSearch(this, mBigEventsSearchEdit, mBigEventsTitleRelative, mSearchLinear);
                break;
            case R.id.shyk_xz:
                String sumId="";
                String sumName="";
                for(int i=0;i<list.size();i++){
                    int j=0;
                    if(list.get(i).getChecked()){
                        sumId+=list.get(i).getPersonId()+",";
                        sumName+=list.get(i).getPersonName()+",";
                        Log.i("zm9316","添加："+list.get(i).getChecked()+""+"Ids:"+sumId);
                    }else{

                    }

                }
                Bundle bundle=getIntent().getExtras();
                bundle.putString("attend",sumId);
                bundle.putString("attendN",sumName);
                jump(ActivityInfo.class,bundle);
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityAttendMenberList.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
    private void jump(Class c,Bundle attend) {
//        Intent i = new Intent(ActivityAttendMenberList.this, c);
//        i.putExtra("tag", "1");
//        i.putExtras( attend);
//        startActivity(i);
//        finish();
        Intent i=new Intent(ActivityAttendMenberList.this, c);
        i.putExtras( attend);
        setResult(1,i);
        finish();
//        ActivityAttendMenberList.onDestory();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
//            jump(PartygroupActivity.class);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
