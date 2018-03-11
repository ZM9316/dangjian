package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import a9chou.com.fangjiazhuangApp.adapter.ConferenceSelectionAdapter;
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

public class ActivityConferenceSelection extends BaseActivity {

    @BindView(R.id.big_events_left_back)
    TextView mbig_events_left_back;
    @BindView(R.id.big_events_title_title)
    TextView mbig_events_title_title;
    @BindView(R.id.big_events_search_iv)
    ImageView mbig_events_search_iv;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.place_et)
    EditText mplace_et;
    @BindView(R.id.id_recyclerview)
    RecyclerView mid_recyclerview;
    private CheckBox CB;
    private ConferenceSelectionAdapter adapter;
    private List<BbsjBean> list = new ArrayList<>();
    private List<String> listp = new ArrayList<>();
    @BindView(R.id.pullToRefreshLayout)
    PullToRefreshLayout mpullToRefreshLayout;

    @Override
    protected int attachLayoutRes() {
        return R.layout.conference_selection;
    }

    @Override
    protected void initViews() {
        mbig_events_title_title.setText("地点选择");
        mbig_events_search_iv.setVisibility(View.GONE);
        listp.add("第一会议室313");
        listp.add("第二会议室317");
        listp.add("第三会议室207");
        listp.add("第四会议室225");
        listp.add("第五会议室228");
        listp.add("实验楼多功能厅");
        list.clear();
        for(int i=0;i<listp.size();i++){
            int x=i+1;
            list.add(new BbsjBean(listp.get(i),"地址"+x+":",null));
        }
        mid_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConferenceSelectionAdapter(R.layout.conference_item, list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CheckBox CBX;
                CB= (CheckBox) adapter.getViewByPosition(mid_recyclerview,position,R.id.place_cb);
                BbsjBean bb=new BbsjBean();
                Log.i("zm9316","BbsjBean："+CB.isChecked()+"");
                for(int i=0;i<listp.size();i++){
                    if(CB.isChecked()){
                        if(i==position){
                            list.get(i).setChecked(true);
                            continue;
                        }else{
                            list.get(i).setChecked(false);
                            CBX= (CheckBox) adapter.getViewByPosition(mid_recyclerview,i,R.id.place_cb);
                            CBX.setChecked(false);
                        }
                    }else{
//                        if(i==position){
//                            list.get(i).setChecked(true);
//                            continue;
//                        }else{
//                            list.get(i).setChecked(false);
//                            CBX= (CheckBox) adapter.getViewByPosition(mid_recyclerview,i,R.id.place_cb);
//                            CBX.setChecked(false);
//                        }
                    }

                }
                list.get(position).setChecked(CB.isChecked());
            }
        });
        mid_recyclerview.setAdapter(adapter);
        mpullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mpullToRefreshLayout.finishRefresh();
                        adapter.notifyDataSetChanged();
                    }
                },500);
            }

            @Override
            public void loadMore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mpullToRefreshLayout.finishLoadMore();
                        adapter.notifyDataSetChanged();
                    }
                },500);
            }
        });

    }

    @OnClick({R.id.big_events_left_back, R.id.shyk_xz,R.id.place_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.big_events_left_back:
                finish();
                break;
            case R.id.place_et:

                break;
            case R.id.shyk_xz:
                String placeName="";
                if(mplace_et.getText().toString().equals("")){
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getChecked()){
                            placeName+=list.get(i).getPlace();
                        }
                    }
                }else{
                    placeName=mplace_et.getText().toString();
                }
                if(placeName.equals("")){
                    ToastUtils.showToast("请选择或输入一个地址！");
                }else{
                    Bundle bundle=getIntent().getExtras();
                    bundle.putString("placeName",placeName);
                    jump(ActivityInfo.class,bundle);
                }

                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityConferenceSelection.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
    private void jump(Class c,Bundle join) {
        Intent i=new Intent(ActivityConferenceSelection.this, c);
        i.putExtras(join);
        setResult(1,i);
        finish();
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
