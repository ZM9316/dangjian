package a9chou.com.fangjiazhuangApp.module.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.callback.UCallback;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.GridImageAdapter;
import a9chou.com.fangjiazhuangApp.adapter.ImageUrlAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;
import a9chou.com.fangjiazhuangApp.utils.TimeUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UploadUtil;
import a9chou.com.fangjiazhuangApp.weight.dialog.FullyGridLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class ActivityShowInfo extends BaseActivity {


    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
//    @BindView(R.id.meet_type)
//    EditText mmeet_type;
    @BindView(R.id.huiyi_name_tv)
    TextView mhuiyi_name_tv;
    @BindView(R.id.huiyi_didian_tv)
    TextView mhuiyi_didian_tv;
    @BindView(R.id.canhui_renyuan_tv)
    TextView mcanhui_renyuan_tv;
    @BindView(R.id.meet_name)
    EditText mmeet_name;
    @BindView(R.id.choose_join)
    EditText mchoose_join;
    @BindView(R.id.choose_attend)
    EditText mchoose_attend;
    @BindView(R.id.place)
    EditText mplace;
    @BindView(R.id.choose_start)
    EditText mchoose_start;
    @BindView(R.id.choose_end)
    EditText mchoose_end;
    @BindView(R.id.meet_agenda)
    EditText mmeet_agenda;
    @BindView(R.id.end_time)
    LinearLayout mend_time;
    @BindView(R.id.release_btn_show)
    Button mrelease_btn_show;
    @BindView(R.id.xjyh_item3_rightiv)
    ImageView mxjyh_item3_rightiv;
    @BindView(R.id.xjyh_item3_rv)
    RecyclerView mXjyhItem3Rv;
    @BindView(R.id.im_recyclerview)
    RecyclerView mim_recyclerview;
    @BindView(R.id.list_content)
    LinearLayout mlist_content;
    @BindView(R.id.liexiren_renyuan)
    LinearLayout mliexiren_renyuan;
    @BindView(R.id.enclosure)
    RelativeLayout menclosure;
    @BindView(R.id.no_net)
    LinearLayout mNoNet;
    @BindView(R.id.relsel)
    LinearLayout mrelsel;
    @BindView(R.id.jiazai)
    Button mjiazai;
    private GridImageAdapter mAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private HashMap<String, File> ImageMap = new HashMap<>();
    private ArrayList<HashMap<String, File>> ImageMapList = new ArrayList<>();
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private Map<String, String> mmMap;
    private String mUserId;
    private String mToken;
    private String mUserName;
    private Bitmap im_up;
    private String Ids="";
    private String j="";
    private String k="";
    private String tit="";
    private String bftit="";
    private String sumListId="";
    private String sumListAttendId="";
    private String className="";
    private String btnvalue="";
    private LoadingDailog mDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_showinfo;
    }

    @Override
    protected void initViews() {
        loadDialog();
        //设置EditText的显示方式为多行文本输入
        mchoose_join.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mmeet_agenda.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //改变默认的单行模式
        mchoose_join.setSingleLine(false);
        mmeet_agenda.setSingleLine(false);
        //水平滚动设置为False
        mchoose_join.setHorizontallyScrolling(false);
        mmeet_agenda.setHorizontallyScrolling(false);
        j=getIntent().getStringExtra("typeId");
        k=getIntent().getStringExtra("partyOrgId");
        tit=getIntent().getStringExtra("tit");
        bftit=getIntent().getStringExtra("bftit");
        btnvalue=getIntent().getStringExtra("btnvalue");
        Ids=getIntent().getStringExtra("id");
        Log.i("zm9316","bftit:::"+bftit);
        className=getIntent().getStringExtra("className");
        if("ATM".equals(className)){
//            mrelease_btn_show.setEnabled(false);
            mrelease_btn_show.setVisibility(View.GONE);
        }
        if("TLF".equals(className)||"AAA".equals(className)){
//            mrelease_btn_show.setEnabled(true);
            mrelease_btn_show.setVisibility(View.VISIBLE);
        }
        if("集体学习".equals(tit)||"党课".equals(tit)){
            mShykTitleTitle.setText(tit);
            mhuiyi_name_tv.setText("名    称");
            mmeet_name.setHint("请输入名称");
            mhuiyi_didian_tv.setText("地    点");
            mcanhui_renyuan_tv.setText("人    员");
            mchoose_join.setHint("请输入参加人员");
            mplace.setHint("请输入地点");
            mmeet_agenda.setHint("请输入学习内容");
            mliexiren_renyuan.setVisibility(View.GONE);
        }else{
            mShykTitleTitle.setText(tit);
            mhuiyi_name_tv.setText("会议名称");
            mmeet_name.setHint("请输入会议名称");
            mhuiyi_didian_tv.setText("会议地点");
            mcanhui_renyuan_tv.setText("参会人员");
            mchoose_join.setHint("请输入参会人员");
            mplace.setHint("请输入会议地点");
            mmeet_agenda.setHint("请输入会议议程");
            mliexiren_renyuan.setVisibility(View.VISIBLE);
        }
        if("0".equals(btnvalue)){
            mShykTitleTitle.setText(tit);
            mrelease_btn_show.setVisibility(View.GONE);
            menclosure.setVisibility(View.GONE);
        }else{
            mrelease_btn_show.setVisibility(View.VISIBLE);
            menclosure.setVisibility(View.VISIBLE);
        }
        mShykXz.setText("上传");
        mShykXz.setVisibility(View.GONE);
        mend_time.setVisibility(View.GONE);
//        mim_recyclerview.setVisibility(View.GONE);
        mEd = getSharedPreferences("config", MODE_PRIVATE);
        mUserId = mEd.getString("userId", "");
        mToken = mEd.getString("token", "");
        mUserName = mEd.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("userId", mUserId);
        mMap.put("token", mToken);
        mMap.put("userName", mUserName);
        mMap.put("pageSize", "10");
        mMap.put("pageIndex", "0");
        mMap.put("typeId", j);
        mMap.put("partyOrgId",k);
        Log.i("zm9316","partyOrgId:"+k);
        Log.i("zm9316","showinfo:"+tit);
        Go();
        initPicture();
    }

    private void getIMage(final ArrayList<String> imgUrl) {
        mim_recyclerview.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityShowInfo.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mim_recyclerview.setLayoutManager(linearLayoutManager);
        if(imgUrl.size()!=0){
            final String[] url=imgUrl.get(0).split(",");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final ArrayList<Bitmap> bitmaps=new ArrayList<>();

                        for (int i = 0; i < url.length; i++) {
                            Log.i("zm9316", url[i]);
                            bitmaps.add(getBitmap(IP+"/treps/"+url[i]));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageUrlAdapter adapter = new ImageUrlAdapter(ActivityShowInfo.this, bitmaps);
                                    mim_recyclerview.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        mDialog.dismiss();
                    }
                }
            }).start();
        }else{
            ArrayList<Bitmap> bitmapsn=new ArrayList<>();
            ImageUrlAdapter adapter = new ImageUrlAdapter(ActivityShowInfo.this, bitmapsn);
            mim_recyclerview.setAdapter(adapter);
            mDialog.dismiss();
            adapter.notifyDataSetChanged();

        }


    }

    private void Go() {
        ViseHttp.
                POST("mobile/wp/wpMeeting/allDatas").
//                POST("mobile/wp/wpMeeting/allDatas").
//        POST("mobile/wp/wpMeeting/allData").
                addParams(mMap).
                request(new ACallback<String>() {
                    List<BbsjBean> list = new ArrayList<>();
//                    ArrayList<Integer> mDatas=new ArrayList<Integer>();
                    @Override
                    public void onSuccess(String data) {
                        Log.i("zm9316","Ashowinfo:"+data);
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        HashMap<String, String> hashMapList = new HashMap<String, String>();
                        ArrayList<String> imgUrl = new ArrayList<>();
                        String sumList = "";
                        Log.i("zm9316","info::"+data);
                        try {
                            JSONObject o = new JSONObject(data);
                            JSONArray type = o.getJSONArray("data");
                            int i = 0;
                            for (; i < type.length(); i++) {
                                JSONObject x = type.getJSONObject(i);
                                Iterator iterator = x.keys();                       // joData是JSONObject对象
                                while (iterator.hasNext()) {
                                    String key = iterator.next() + "";
                                    if (key.equals("attendPerson")||
                                            key.equals("summaryUrl")||
                                            key.equals("id")||
                                            key.equals("title") ||
                                            key.equals("createDate") ||
                                            key.equals("address") ||
                                            key.equals("startTime") ||
                                            key.equals("typeId") ||
                                            key.equals("content")||
                                            key.equals("endTime")) {
                                        if (Ids.equals(x.getString("id"))) {
                                            if(x.has("title")){
                                                mmeet_name.setText(x.getString("title"));
                                            }
                                            if(x.has("startTime")){
                                                mchoose_start.setText(x.getString("startTime"));
                                            }
                                            if(x.has("endTime")){
                                                mchoose_end.setText(x.getString("endTime"));
                                            }
                                            if(x.has("address")){
                                                mplace.setText(x.getString("address"));
                                            }
                                            if(x.has("content")){
                                                mmeet_agenda.setText(x.getString("content"));
                                            }
//                                            if(x.has("typeId")&&Ids.equals(x.getString("id"))){
//                                                mmeet_type.setText(x.getString("typeId"));
//                                            }
                                            if(x.has("attendPersonName")){
                                                mchoose_attend.setText(x.getString("attendPersonName"));
                                            }
                                            if(x.has("attendPerson")){
                                                sumListAttendId=x.getString("attendPerson");
                                            }
                                            if(x.has("summaryUrl")&&Ids.equals(x.getString("id"))){
//                                                Log.i("zm9316",Ids+":::"+x.getString("id"));
                                                imgUrl.add(x.getString("summaryUrl"));
//                                    imgUrl.add("http://img06.tooopen.com/images/20170720/tooopen_sy_217426757833.jpg");
                                            }
                                        }

                                    }else if(key.equals("personList")&&Ids.equals(x.getString("id"))){
                                        JSONArray y = x.getJSONArray("personList");
                                        Log.i("zm9316","参会人员："+y.getString(0));
                                        for (int j = 0; j < y.length(); j++) {
                                            JSONObject z = y.getJSONObject(j);
                                            Iterator iteratorZ = z.keys();
                                            while (iteratorZ.hasNext()) {
                                                String keyZ = iteratorZ.next() + "";
                                                if (keyZ.equals("userId")) {
                                                    sumListId+=z.getString("userId")+",";
                                                    sumList+=z.getString("userName")+",";
                                                    Log.i("zm9316",sumListId);
                                                    mchoose_join.setText(sumList);
                                                    Log.i("zm9316","参会人员："+z.getString("userName"));
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            Log.i("zm9316", "err");
                            e.printStackTrace();
                        }finally {
                            getIMage(imgUrl);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.i("zm9316", "fail");
                        mDialog.dismiss();
                        mlist_content.setVisibility(View.GONE);
                        mrelsel.setVisibility(View.GONE);
                        mNoNet.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void loadDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivityShowInfo.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
    }


//    public Bitmap getRemoteImage(final URL aURL) {
//        try {
//            final URLConnection conn = aURL.openConnection();
//            conn.connect();
//            final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//            final Bitmap bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            return bm;
//        } catch (IOException e) {}
//        return null;
//    }

    public Bitmap getBitmap(String path) throws IOException {
        try {
//            ViseHttp.GET(path).baseUrl()
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


//    private void initDatas(ArrayList<String> path) {
//        Log.i("zm9316","图片路径:"+path);
//        mDatas = new ArrayList<>();
//        for(int i=0;i<path.size();i++){
////            mDatas.add(IP+path.get(i));
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener =
            new GridImageAdapter.onAddPicClickListener() {
                @Override
                public void onAddPicClick() {
//            boolean mode = cb_mode.isChecked();
                    // 进入相册 以下是例子：不需要的api可以不写
                    PictureSelector.create(ActivityShowInfo.this)
                            .openGallery(1)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                            .maxSelectNum(4)// 最大图片选择数量
                            .minSelectNum(1)// 最小选择数量
                            .imageSpanCount(4)// 每行显示个数
                            .selectionMode(2)// 多选 or 单选
                            .previewImage(true)// 是否可预览图片
                            .previewVideo(true)// 是否可预览视频
                            .isCamera(true)// 是否显示拍照按钮
                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                            .synOrAsy(true)//同步true或异步false 压缩 默认同步
                            .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                            .selectionMedia(selectList)// 是否传入已选图片
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

                }

            };

    private void initPicture() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ActivityShowInfo.this, 4, GridLayoutManager.VERTICAL, false);
        mXjyhItem3Rv.setLayoutManager(manager);
        mAdapter = new GridImageAdapter(ActivityShowInfo.this, onAddPicClickListener);
        mAdapter.setList(selectList);
//        图片最大数量
        mAdapter.setSelectMax(4);
        mXjyhItem3Rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ActivityShowInfo.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ActivityShowInfo.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ActivityShowInfo.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

        //6.0运行时权限
        PermissionManager.instance().with(this).request(new OnPermissionCallback() {
            @Override
            public void onRequestAllow(String permissionName) {
                PictureFileUtils.deleteCacheDirFile(ActivityShowInfo.this);
            }

            @Override
            public void onRequestRefuse(String permissionName) {
                Toast.makeText(ActivityShowInfo.this,
                        getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestNoAsk(String permissionName) {
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    Handler handler = new Handler();

    @OnClick({R.id.shyk_left_back, R.id.release_btn_show, R.id.xjyh_item3_rightiv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                jump(PartygroupActivity.class,j,k,tit,bftit,className);
                finish();
                break;
            case R.id.xjyh_item3_rightiv:
                //上传图片
                mXjyhItem3Rv.setVisibility(View.VISIBLE);
                break;
            case R.id.jiazai:
                //加载
                loadDialog();
                Go();
                mNoNet.setVisibility(View.GONE);
                mlist_content.setVisibility(View.VISIBLE);
                mrelsel.setVisibility(View.VISIBLE);
                break;
            case R.id.release_btn_show:
                //结束会议   /mobile/wp/wpMeeting/updateMeeting
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityShowInfo.this);
                //    设置Title的图标
//                builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("提示");
                //    设置Content来显示一个信息
                if(tit.endsWith("党课")||tit.endsWith("集体学习")){
                    builder.setMessage("是否结束学习？");
                }else{
                    builder.setMessage("是否结束会议？");
                }
//                builder.set
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadDialog();
                        Timer timer = new Timer();
                        if(selectList.size()>0){
                            int a =0;
                            for (LocalMedia media : selectList) {
                                Log.i("图片-----》", media.getPath());
                                a++;
                                File file = new File(selectList.get(a - 1).getPath());
                                ImageMap.put("Image" + a, file);
//                            ImageMap.put("Image", file);

                            }
                            Date dt=new Date();
//                        if(ImageMapList.size()!=0){
                            mmMap=new HashMap<>();
                            mmMap.put("id",Ids);
                            mmMap.put("userName",mUserName);
                            mmMap.put("token",mToken);
                            mmMap.put("userId",mUserId);
//                            mmMap.put("isNewRecord","false");
                            mmMap.put("title",mmeet_name.getText().toString());
                            mmMap.put("address",mplace.getText().toString());
                            mmMap.put("startTime",mchoose_start.getText().toString());
                            mmMap.put("endTime",TimeUtil.getTime1(dt).toString());
                            mmMap.put("content",mmeet_agenda.getText().toString());
                            mmMap.put("ids",sumListId);
                            mmMap.put("attendPerson",sumListAttendId);
                            mmMap.put("typeId",j);
                            ViseHttp.UPLOAD("mobile/wp/wpMeeting/updateMeeting?"+"token="+mToken+"&userName="+mUserName+"&userId="+mUserId)
                                    .addParams(mmMap)
                                    .addFiles(ImageMap)
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String data) {
                                            mDialog.dismiss();
                                            Log.i("data",data);
                                            jump(PartygroupActivity.class,j,k,tit,bftit,className);
                                            finish();
                                        }

                                        @Override
                                        public void onFail(int errCode, String errMsg) {
                                            mDialog.dismiss();
                                        }
                                    });
                        }else{
                            mDialog.dismiss();
                            ToastUtils.showToast("请至少上传一张图片！");
                        }


//                        for(int y=0;y<selectList.size();y++){
//                            ImageMap.put("Image", new File(selectList.get(y).getPath()));
//                            Log.i("zm9316","path:"+selectList.get(y).getPath());
//                            ImageMapList.add(ImageMap);
//                        }
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
//                Log.i("zm9316", "imgurl:" + ImageMapList.get(0).get("Image"));

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
//                    Log.i("data", data.getDataString() + "");
                    Log.i("data",selectList + "");


                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的


//                    file = new File(selectList.get(0).getPath());

                    mAdapter.setList(selectList);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityShowInfo.this, c);
        i.putExtra("tag", "1");
//        startActivity(i);
        startActivityForResult(i, 0);
//        finish();
    }
    private void jump(Class c,String typeid,String partyOrgId,String tit,String bftit,String className) {
        Intent i = new Intent(ActivityShowInfo.this, c);
        i.putExtra("tag", "1");
        i.putExtra("typeId", typeid);
        i.putExtra("partyOrgId", partyOrgId);
        i.putExtra("tit", tit);
        i.putExtra("bftit", bftit);
        i.putExtra("className", className);
//        startActivity(i);
        startActivityForResult(i, 0);
//        finish();
    }

    //    private void back(Class c) {
//        Intent i = new Intent(ActivityShowInfo.this, c);
//        i.putExtra("tag", "1");
////        startActivity(i);
//        startActivityForResult(i,0);
////        finish();
//    }
    private void jump(Class c, int state) {
        Intent i = new Intent(ActivityShowInfo.this, c);
        i.putExtra("state", state);
//        startActivity(i);
        startActivityForResult(i, 0);
//        finish();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ActivityShowInfo Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            jump(PartygroupActivity.class,j,k,tit,bftit,className);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
