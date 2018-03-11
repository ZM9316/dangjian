package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sh.shvideolibrary.VideoInputDialog;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.XdthAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.XdBean;
import a9chou.com.fangjiazhuangApp.utils.ImageUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class XdthActivity extends BaseActivity implements VideoInputDialog.VideoCall {

    private static final String TAG = "XdthActivity";
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.xzlrfs)
    TextView mXzlrfs;
    @BindView(R.id.lrwz)
    TextView mLrwz;
    @BindView(R.id.scly)
    TextView mScly;
    @BindView(R.id.scsp)
    TextView mScsp;
    @BindView(R.id.activity_xdth2)
    LinearLayout mActivityXdth2;
    @BindView(R.id.xdth_rv)
    RecyclerView mXdthRv;
    private XdthAdapter mAdapter;
    String path;//视频录制输出地址
    private List<XdBean.DataBean> mDatBeen;
    private Bitmap mBitmap;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_xdth;
    }

    @Override
    protected void initViews() {
        mShykXz.setVisibility(View.GONE);
        mShykTitleTitle.setText("心得体会");
        final SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);

        String token = sp.getString("token", "");
        String username = sp.getString("username", "");
        String userId = sp.getString("userId", "");

        LinearLayoutManager l = new LinearLayoutManager(this);
        mXdthRv.setLayoutManager(l);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("userName", username);
        map.put("userId", userId);

        ViseHttp.
                GET("mobile/wp/wpExperience/data").
                addParams(map).
                request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Gson gson = new Gson();
                        XdBean bean = gson.fromJson(data, XdBean.class);
                        mDatBeen = bean.getData();

                        mAdapter = new XdthAdapter(XdthActivity.this, mDatBeen);
//                        刷新列表
                        mAdapter.notifyDataSetChanged();
                        mXdthRv.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new XdthAdapter.OnWzClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(XdthActivity.this, MyWzActivity.class);
                                i.putExtra("title", mDatBeen.get(position).getContentTitle());
                                i.putExtra("content", mDatBeen.get(position).getContent());
                                startActivity(i);
                            }
                        }, new XdthAdapter.OnRecordClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                播放录音
                                Log.d(TAG, "onItemClick: 录音" + mDatBeen.get(position).getRecord());
                                MediaPlayer player = new MediaPlayer();
                                try {
                                    player.setDataSource("http://111.205.44.47/treps" + mDatBeen.get(position).getRecord());
                                    player.prepare();
                                    player.start();
                                } catch (Exception e) {
                                    Log.d(TAG, "onItemClick: " + e.toString());
                                    e.printStackTrace();
                                }
                            }
                        }, new XdthAdapter.OnVideoClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String url = mDatBeen.get(position).getVideo();
                                Log.d(TAG, "onItemClick:视频 " + url);

                                Intent intent = new Intent(XdthActivity.this, OldMoviesActivity.class);
                                intent.putExtra("url", mDatBeen.get(position).getVideo());
                                intent.putExtra("name", mDatBeen.get(position).getVideoTitle());
                                startActivity(intent);


                            }
                        });
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {


                    }
                });


    }


    @OnClick({R.id.shyk_left_back, R.id.shyk_title_title, R.id.shyk_xz, R.id.xzlrfs, R.id.lrwz, R.id.scly, R.id.scsp, R.id.activity_xdth2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case R.id.shyk_title_title:
                break;
            case R.id.shyk_xz:
                break;
            case R.id.xzlrfs:
                break;
            case R.id.lrwz:
                jump(LrwzActivity.class);
                break;
            case R.id.scly:
                jump(RecordActivity.class);
                break;
            case R.id.scsp:
                //显示视频录制控件
                VideoInputDialog.show(getSupportFragmentManager(), XdthActivity.this, VideoInputDialog.Q720, XdthActivity.this);
//                Intent intent = new Intent(XdthActivity.this, XdthActivity.class);
//                startActivity(intent);
                break;
            case R.id.activity_xdth2:
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(XdthActivity.this, c);
        startActivity(i);
        finish();
    }

    @Override
    public void videoPathCall(final String path) {
        Log.e("地址:", path);
        //根据视频地址获取缩略图
        this.path = path;

        final Map<String, Object> map = new HashMap<>();
        map.put("typeId", "2");
        map.put("videoTitle", "我的视频");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageUtils.imageUpload(getSharedPreferences("config", MODE_PRIVATE), map, path, "file");
                mBitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND);

            }
        }).start();
    }
}