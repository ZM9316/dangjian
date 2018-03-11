package a9chou.com.fangjiazhuangApp.module.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.HomeAdapterTwo;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.activity.DjdtDetailsActivity;
import a9chou.com.fangjiazhuangApp.module.activity.WdxxActivity;
import a9chou.com.fangjiazhuangApp.module.dao.DjdtBean;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.QBadgeViewHelper;
import a9chou.com.fangjiazhuangApp.utils.SearchUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;

/**
 * date: 2017/9/8.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = "home";
    @BindView(R.id.home_recyclerview)
    RecyclerView mHomeRecyclerview;
    @BindView(R.id.home_swipe)
    SwipeRefreshLayout mHomeSwipe;
    Unbinder unbinder;
    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.tool_bar)
    RelativeLayout mToolBar;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.search_abolish)
    TextView mSearchAbolish;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.jiazai)
    Button mJiazai;
    @BindView(R.id.no_net)
    LinearLayout mNoNet;
    private List<Integer> mImages;
    private List<String> mTitles;
    private HomeAdapterTwo mHomeAdapterTwo;
    private LoadingDailog mDialog;
    private Map<String, String> mMap;
    private List<DjdtBean.DataBean> mDataBeanList;
    private String mToken;
    private String mUserName;
    private String mUserId;
    private SharedPreferences mSp;
    private LinearLayoutManager mLinearLayoutManager;
    private Map<String, String> mGetCount;
    private QBadgeView mQBadgeView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViews() {
        Log.i("zm9316",getActivity()+"");
        initData();
        setRecyclerView();
        loadDialog();
        Go();
    }

    private void setRecyclerView() {
        //        禁止下拉
        mHomeSwipe.setEnabled(false);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mHomeRecyclerview.setLayoutManager(mLinearLayoutManager);
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }

    private void initData() {
        mTitleTitle.setText("首  页");
        mSearch.setVisibility(View.GONE);
        mSp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        mUserId = mSp.getString("userId", "");
        mToken = mSp.getString("token", "");
        mUserName = mSp.getString("username", "");
        mGetCount = new HashMap<>();
        mGetCount.put("userId", mUserId);
        mGetCount.put("token", mToken);
        mGetCount.put("userName", mUserName);
        Log.d(TAG, "initViews: " + mUserId + mToken + mUserName);
        mMap = new HashMap<>();
        mMap.put("categoryId", "37");
        mMap.put("userName", mUserName);
        mMap.put("pageIndex", "0");
        mMap.put("token", mToken);
        mMap.put("pageSize", "40");


        mQBadgeView = new QBadgeView(getContext());
        mQBadgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WdxxActivity.class);
                startActivity(i);
            }
        });
    }

    private void getCount() {
        ViseHttp
                .GET("mobile/wp/wpMessagePerson/getMessageCount")
                .addParams(mGetCount)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mHomeSwipe.setVisibility(View.VISIBLE);
                        mNoNet.setVisibility(View.GONE);
                        Log.d(TAG, "onSuccess: " + data);
                        try {
                            JSONObject o = new JSONObject(data);
                            String type = o.getString("type");
                            if (type.equals("s")) {
                                //        图标数字
                                QBadgeViewHelper.setQBadgeByImage(mQBadgeView, true, mTitleLd, Integer.parseInt(o.getString("data")));
                            } else {
                                mQBadgeView.hide(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.d(TAG, "onFail: " + errCode + "  " + errMsg);
                        ToastUtils.showToast("当前网络不可用，请检查网络");
                        mDialog.dismiss();
                        mHomeSwipe.setVisibility(View.GONE);
                        mNoNet.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void Go() {
        ViseHttp.
                GET("mobile/cms/cmsArticle/data").
                addParams(mMap).
                request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {

                        mHomeSwipe.setVisibility(View.VISIBLE);
                        mNoNet.setVisibility(View.GONE);
                        Log.d(TAG, "onSuccess: data" + data + "  " + mMap.toString());
                        Gson g = new Gson();
                        DjdtBean d = g.fromJson(data, DjdtBean.class);
                        Log.d(TAG, "onSuccess: aaaa" + d.getData().size());
                        mDataBeanList = d.getData();
                        initXBanner();
                        mHomeAdapterTwo = new HomeAdapterTwo(d.getData().size(), getContext(), mDataBeanList, mImages, mTitles);
                        mHomeAdapterTwo.notifyDataSetChanged();
                        mHomeRecyclerview.setAdapter(mHomeAdapterTwo);
                        mDialog.dismiss();
//                        if (d.getData().size() >= 10) {
//                            up();
//                        }
                        mHomeAdapterTwo.setOnItemClickListener(new HomeAdapterTwo.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), DjdtDetailsActivity.class);
                                intent.putExtra("djdtId", mDataBeanList.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });

                    }

                    //                    private void up() {
                    //                        上拉加载
//                        mHomeRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                                super.onScrollStateChanged(recyclerView, newState);
//                            }
//
//                            @Override
//                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                                super.onScrolled(recyclerView, dx, dy);
//                                final int lastItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
//                                if (lastItemPosition + 1 == mHomeAdapterTwo.getItemCount()) {
//                                    index++;
//                                    Log.d(TAG, "onScrolled:xxxxxxxxxx " + index);
//                                    Map<String, String> upMap = new HashMap<>();
//                                    upMap.put("categoryId", "37");
//                                    upMap.put("userName", mUserName);
//                                    upMap.put("pageIndex", Integer.toString(index));
//                                    upMap.put("token", mToken);
//                                    upMap.put("pageSize", "10");
////                                    添加数据
//                                    ViseHttp.
//                                            GET("mobile/cms/cmsArticle/data").
//                                            addParams(mMap).
//                                            request(new ACallback<String>() {
//                                                @Override
//                                                public void onSuccess(String data) {
//                                                    Gson g = new Gson();
//
//                                                    DjdtBean d = g.fromJson(data, DjdtBean.class);
//
//                                                    Log.d(TAG, "onSuccess: xxxxxxxxxxxxxxxxx" + d.getData().size());
//
//
//                                                    if (d.getData().size() != 0) {
//                                                        mDataBeanList.addAll(d.getData());
//                                                        //         刷新适配器
//                                                        mHomeAdapterTwo.notifyDataSetChanged();
//                                                        mHomeAdapterTwo.notifyItemRemoved(mHomeAdapterTwo.getItemCount());
//                                                    } else {
//                                                        ToastUtils.showToast("已经到底了");
//                                                    }
//
//                                                }
//
//                                                @Override
//                                                public void onFail(int errCode, String errMsg) {
//                                                }
//                                            });
//                                }
//                            }
//                        });
//                    }
//                        //
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtils.showToast("当前网络不可用，请检查网络");
                        mDialog.dismiss();
                        mHomeSwipe.setVisibility(View.GONE);
                        mNoNet.setVisibility(View.VISIBLE);
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        getCount();

    }

    // 设置xbanner数据
    private void initXBanner() {
        mImages = new ArrayList<>();
        mTitles = new ArrayList<>();
        mImages.add(R.drawable.banner11);
        mTitles.add("党的十九大");
        mImages.add(R.drawable.banner6);
        mTitles.add("行业领先 世界一流");
        mImages.add(R.drawable.bannery);
        mTitles.add("国电浙能宁东发电有限公司");
    }

    //     点击事件
    @OnClick({R.id.jiazai, R.id.title_ld, R.id.search, R.id.search_abolish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jiazai:
                loadDialog();
                if (NetUtil.isNetworkAvailable(getActivity())) {
                    Go();
                    getCount();
                    mHomeSwipe.setVisibility(View.VISIBLE);
                    mNoNet.setVisibility(View.GONE);
                } else {
                    ToastUtils.showToast("当前网络不可用,请检查网络");
                }


                break;
            case R.id.title_ld:

                break;
            case R.id.search:
                SearchUtil.showSearch(mBigEventsSearchEdit, getActivity(), mBigEventsTitleRelative, mSearchLinear);

                break;
            case R.id.search_abolish:
                SearchUtil.abolishSearch(getActivity(), mBigEventsSearchEdit, mBigEventsTitleRelative, mSearchLinear);
                break;
        }
    }
}
