package a9chou.com.fangjiazhuangApp.module.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.litepal.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityMessage;
import a9chou.com.fangjiazhuangApp.module.activity.ClipImageActivity;
import a9chou.com.fangjiazhuangApp.module.activity.LoginActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MyActiveActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MyMaterialActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MyMeetingActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MyReadActivity;
import a9chou.com.fangjiazhuangApp.module.activity.PayActivity;
import a9chou.com.fangjiazhuangApp.module.activity.SetPassActivity;
import a9chou.com.fangjiazhuangApp.module.activity.XdthActivity;
import a9chou.com.fangjiazhuangApp.module.dao.PhotoAndNameBean;
import a9chou.com.fangjiazhuangApp.utils.FileUtils;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.PopupWindowUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.weight.CircleImageView;
import a9chou.com.fangjiazhuangApp.weight.GlideCircleTransform;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;
import static a9chou.com.fangjiazhuangApp.utils.FileUtils.getRealFilePathFromUri;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.luck.picture.lib.config.PictureConfig.REQUEST_CAMERA;

/**
 * date: 2017/9/29.
 * author: 王艺凯 (lenovo )
 * function:
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    //请求相机
    private static final int REQUEST_CAPTURE = 909;
    private static int REQUEST_CAMERA_1 = 1;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private static final String TAG = "MineFragment";
    @BindView(R.id.title_left_iv)
    ImageView mTitleLeftIv;
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_ld)
    ImageView mTitleLd;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.big_events_title_relative)
    RelativeLayout mBigEventsTitleRelative;
    @BindView(R.id.big_events_search_edit)
    EditText mBigEventsSearchEdit;
    @BindView(R.id.search_abolish)
    TextView mSearchAbolish;
    @BindView(R.id.Version)
    TextView mVersion;
    @BindView(R.id.search_linear)
    LinearLayout mSearchLinear;
    @BindView(R.id.tool_bar)
    RelativeLayout mToolBar;
    @BindView(R.id.circleImageView)
    CircleImageView mCircleImageView;
//    @BindView(R.id.dfjn)
//    RelativeLayout mDfjn;
//    @BindView(R.id.wdxx)
//    RelativeLayout mWdxx;
//    @BindView(R.id.wdxd)
//    RelativeLayout mWdxd;
//    @BindView(R.id.wcjdhy)
//    RelativeLayout mWcjdhy;
//    @BindView(R.id.wcjdhd)
//    RelativeLayout mWcjdhd;
//    Unbinder unbinder;
    @BindView(R.id.login_out)
    Button mLoginOut;
    @BindView(R.id.my_name_tv)
    TextView mMyNameTv;
    @BindView(R.id.mine_linear)
    ScrollView mMineLinear;
    @BindView(R.id.jiazai)
    Button mJiazai;
    @BindView(R.id.net_err_linear)
    LinearLayout mNetErrLinear;
    @BindView(R.id.wdzl)
    RelativeLayout mWdzl;
//    @BindView(R.id.wyddsj)
//    RelativeLayout mWyddsj;
    @BindView(R.id.xgmm)
    RelativeLayout mXgmm;
    //    路径
    private String APPLICATION_ID = "fangjiazhuangApp";
    //调用照相机返回图片文件
    private File tempFile;
    private Button mBtnPay;
    private RelativeLayout mWeiChat;
    private RelativeLayout mALi;
    private TextView mPrice;
    private ImageView mAlid;
    private ImageView mWxd;
    private LoadingDailog mDialog;
    private Map<String, String> mMap;
    private String mToken;
    private String mUserId;
    private String mUsername;
    private SharedPreferences mSp;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getContext())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mTitleTitle.setText("我 的");
        mVersion.setText("V"+getLocalVersionName(getActivity())+"");
        mSearch.setVisibility(View.INVISIBLE);
        mTitleLd.setVisibility(View.INVISIBLE);
//        mWdxx.setVisibility(View.GONE);
        mSp = getContext().getSharedPreferences("config", MODE_PRIVATE);
        mToken = mSp.getString("token", "");
        mUserId = mSp.getString("userId", "");
        mUsername = mSp.getString("username", "");
        mMap = new HashMap<>();
        mMap.put("token", mToken);
        mMap.put("id", mUserId);
        mMap.put("userName", mUsername);
        netWork(mMap);

    }

    private void netWork(Map<String, String> map) {
        mDialog.show();
        ViseHttp.GET("mobile/sys/sysUser/getSimple").addParams(map).request(new ACallback<String>() {
            @Override
            public void onSuccess(String data) {

                mMineLinear.setVisibility(View.VISIBLE);
                mNetErrLinear.setVisibility(View.INVISIBLE);
                Gson gson = new Gson();
                PhotoAndNameBean photoAndNameBean = gson.fromJson(data, PhotoAndNameBean.class);

                String name = photoAndNameBean.getData().getName();
                String photo = photoAndNameBean.getData().getPhoto();
                mMyNameTv.setText(name);
//                Glide.with(mContext).load(IP + photo).transform(new GlideCircleTransform(getContext())).into(mCircleImageView);
                Glide.with(mContext).load(IP + photo).into(mCircleImageView);
                mDialog.dismiss();
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                ToastUtils.showToast("当前网络不可用,请检查网络");
                mMineLinear.setVisibility(View.INVISIBLE);
                mNetErrLinear.setVisibility(View.VISIBLE);
                mDialog.dismiss();


            }
        });
    }
    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        PopupWindowUtil.initBottomPopup(getActivity(), R.layout.layout_popupwindow);
        TextView btnCarema = (TextView) PopupWindowUtil.getView(R.id.btn_camera);
        TextView btnPhoto = (TextView) PopupWindowUtil.getView(R.id.btn_photo);
        TextView btnCancel = (TextView) PopupWindowUtil.getView(R.id.btn_cancel);
        btnCarema.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        //创建拍照存储的图片文件
        tempFile = new File(FileUtils.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
//
//        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            Uri contentUri = FileProvider.getUriForFile(getActivity(), APPLICATION_ID + ".fileProvider", tempFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoPhoto();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    mCircleImageView.setImageBitmap(bitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getContext(), ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @OnClick({R.id.xgmm, R.id.wdzl, R.id.jiazai, R.id.login_out, R.id.title_ld, R.id.search, R.id.search_abolish, R.id.circleImageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xgmm:
                jump(SetPassActivity.class);
                break;
//            case R.id.wyddsj:
//                jump(MyReadActivity.class);
//                break;
            case R.id.wdzl:
//                个人资料
                jump(MyMaterialActivity.class);

                break;
            case R.id.jiazai:
                if (NetUtil.isNetworkAvailable(getActivity())) {
                    netWork(mMap);
                    mNetErrLinear.setVisibility(View.INVISIBLE);
                    mMineLinear.setVisibility(View.VISIBLE);

                } else {
                    ToastUtils.showToast("当前网络不可用,请检查网络");
                }
                break;
            case R.id.title_ld:
                ToastUtils.showToast("消息");
                break;
            case R.id.search:
                ToastUtils.showToast("搜索");
                break;
            case R.id.search_abolish:
                ToastUtils.showToast("abolish");
                break;
            case R.id.circleImageView:
//                uploadHeadImage();
                break;
//            case R.id.dfjn:
//                jump(PayActivity.class);
////                initPopupWindow();
//                break;
//            case R.id.wdxx:
//                jump(ActivityMessage.class);
//                break;
//            case R.id.wdxd:
//                jump(XdthActivity.class);
//                break;
//
//            case R.id.wcjdhy:
//                jump(MyMeetingActivity.class);
//                break;
//            case R.id.wcjdhd:
//                jump(MyActiveActivity.class);
//
//                break;
            case R.id.login_out:
                Map<String, String> logout = new HashMap<>();
                logout.put("userName", mUsername);
                logout.put("token", mToken);
                logout.put("mobileLogin", "true");
                ViseHttp.GET("logout").addParams(logout).request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d(TAG, "onSuccess: " + data);
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        getActivity().finish();
                        SharedPreferences.Editor ed = mSp.edit();
                        SharedPreferences.Editor perEd = getActivity().getSharedPreferences("per", MODE_PRIVATE).edit();
                        perEd.clear();
                        perEd.commit();
                        ed.clear();
                        ed.commit();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.d(TAG, "onFail: " + errCode + "  " + errMsg);
                    }
                });


                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(getContext(), c);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_camera:
//                //权限判断
//                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    //申请WRITE_EXTERNAL_STORAGE权限
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//                } else {
//                    //跳转到调用系统相机
//                    gotoCamera();
//                }
//                PopupWindowUtil.popupDismiss();
//                break;
//            case R.id.btn_photo:
//                //权限判断
//                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    //申请READ_EXTERNAL_STORAGE权限
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
//                } else {
//                    //跳转到相册
//                    gotoPhoto();
//                }
//                PopupWindowUtil.popupDismiss();
//                break;
//            case R.id.btn_cancel:
//                PopupWindowUtil.popupDismiss();
//                break;

        }
    }

}
