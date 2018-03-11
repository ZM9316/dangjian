package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.MoreDynamicAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.DjdtBean;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class MoreDynamicActivity extends BaseActivity {

    private static final String TAG = "MoreDynamicActivity";
    @BindView(R.id.big_events_left_back)
    TextView mBigEventsLeftBack;
    @BindView(R.id.big_events_title_title)
    TextView mBigEventsTitleTitle;
    @BindView(R.id.big_events_search_iv)
    ImageView mBigEventsSearchIv;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.search_abolish)
    TextView mSearchAbolish;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.more_dynamic_rv)
    RecyclerView mMoreDynamicRv;
    @BindView(R.id.more_dynamic_swipe)
    SwipeRefreshLayout mMoreDynamicSwipe;
    @BindView(R.id.activity_more_dynamic)
    LinearLayout mActivityMoreDynamic;
    private Map<String, String> mMap;
    private MoreDynamicAdapter mDynamic;
    private int mDataSize;
    private int num;
    private DjdtBean mD;
    private List<DjdtBean.DataBean> mDataBeanList;
    private LinearLayoutManager mLinearLayoutManager;
    private String mUserName;
    private String mToken;
    private int mCurrentCounter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_more_dynamic;
    }

    @Override
    protected void initViews() {

        mBigEventsTitleTitle.setText("党建动态");
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        mUserName = sp.getString("username", "");
        mToken = sp.getString("token", "");
        mLinearLayoutManager = new LinearLayoutManager(this);
        mMoreDynamicRv.setLayoutManager(mLinearLayoutManager);
//        禁止下拉
        mMoreDynamicSwipe.setEnabled(false);


        mBigEventsSearchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //是否是回车键
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MoreDynamicActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String mEdit = mBigEventsSearchEdit.getText().toString().trim();

                    if (TextUtils.isEmpty(mEdit)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("userName", mUserName);
                        map.put("token", mToken);
                        map.put("categoryId", "37");
                        map.put("pageIndex", Integer.toString(num));
                        map.put("pageSize", "10");
                        ShowList(map);
                        ToastUtils.showToast("请输入搜索内容");
                    } else {
                        Map<String, String> map = new HashMap<>();
                        map.put("userName", mUserName);
                        map.put("token", mToken);
                        map.put("categoryId", "37");
                        map.put("pageIndex", Integer.toString(num));
                        map.put("pageSize", "10");
                        map.put("title", mEdit);
                        ShowList(map);
                    }

                } else if (keyCode == KeyEvent.KEYCODE_DEL) {
//                    mBigEventsSearchEdit.setText("");

                    String content = mBigEventsSearchEdit.getText().toString();
                     content.endsWith("-");

                }
                return false;
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("userName", mUserName);
        map.put("token", mToken);
        map.put("categoryId", "37");
        map.put("pageIndex", Integer.toString(num));
        map.put("pageSize", "10");


        ShowList(map);
    }

    private void ShowList(final Map<String, String> map) {
        ViseHttp.
                GET("mobile/cms/cmsArticle/data").
                addParams(map).
                request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d(TAG, "onSuccess: " + map.toString());
                        Gson g = new Gson();
                        final DjdtBean d = g.fromJson(data, DjdtBean.class);
                        mDataBeanList = d.getData();

                        final MoreDynamicAdapter adapter = new MoreDynamicAdapter(R.layout.home_rv_item, mDataBeanList);
                        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {

                                if (mCurrentCounter < 10) {
//            加载结束
                                    adapter.loadMoreEnd(true);
                                } else {
                                    if (mCurrentCounter >= mDataBeanList.size()) {
                                        //                隐藏脚
                                        adapter.loadMoreEnd(false);//true is gone,false is visible
                                    } else {

                                        num++;
                                        ViseHttp.
                                                GET("mobile/cms/cmsArticle/data").
                                                addParams(map).
                                                request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        Log.d(TAG, "onSuccess: data" + data + "  " + mMap.toString());
                                                        Gson g = new Gson();
                                                        final DjdtBean d = g.fromJson(data, DjdtBean.class);
                                                        Log.d(TAG, "onSuccess: aaaa" + d.getData().size());
                                                        mDataBeanList = d.getData();


                                                        adapter.addData(d.getData());
                                                        mCurrentCounter = adapter.getData().size();
                                                        adapter.loadMoreComplete();
                                                    }

                                                    @Override
                                                    public void onFail(int errCode, String errMsg) {
                                                        adapter.loadMoreFail();
                                                    }
                                                });
                                    }
                                }

                            }
                        }, mMoreDynamicRv);

                        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        mMoreDynamicRv.setAdapter(adapter);
                        mCurrentCounter = adapter.getData().size();


                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent i = new Intent(MoreDynamicActivity.this, DjdtDetailsActivity.class);
                                i.putExtra("djdtId", mDataBeanList.get(position).getId() + "");
                                startActivity(i);
                            }
                        });

                    }

                    //
                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }


    @OnClick({R.id.search_abolish, R.id.big_events_left_back, R.id.big_events_search_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_abolish:
                SearchUtil.abolishSearch(this, mBigEventsSearchEdit, mBigEventsTitleRelative, mSearchLinear);
                break;
            case R.id.big_events_left_back:
                finish();
                break;
            case R.id.big_events_search_iv:
                //显示edittext
                SearchUtil.showSearch(mBigEventsSearchEdit, this, mBigEventsTitleRelative, mSearchLinear);
                break;
        }
    }


}