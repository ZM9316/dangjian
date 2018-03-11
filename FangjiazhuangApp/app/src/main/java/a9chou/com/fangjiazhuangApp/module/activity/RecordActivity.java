package a9chou.com.fangjiazhuangApp.module.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.AudioRecoderUtils;
import a9chou.com.fangjiazhuangApp.utils.DrawableTextUtil;
import a9chou.com.fangjiazhuangApp.utils.ImageUtils;
import a9chou.com.fangjiazhuangApp.utils.RecordPlayer;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.XsnowPermissionUtil;
import butterknife.BindView;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity {

    private static final String TAG = "RecordActivity";
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
    @BindView(R.id.bf)
    TextView mBf;
    @BindView(R.id.start)
    TextView mStart;
    @BindView(R.id.sc)
    TextView mSc;
    @BindView(R.id.activity_xdth)
    LinearLayout mActivityXdth;
    @BindView(R.id.timer)
    Chronometer mTimer;
    //录音
    private AudioRecoderUtils mAudioRecoderUtils;
    //播放录音
    private RecordPlayer player;
    //录音存放路径
    private String filepath;
    private String mStr;
    private File mDir;
    private String mToken;
    private String mUsername;
    private String mUserId;
    private SharedPreferences mSp;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_record;
    }

    @Override
    protected void initViews() {
//
//        mSp = ;

//        mToken = mSp.getString("token", "");
//        mUsername = mSp.getString("username", "");
//        mUserId = mSp.getString("userId", "");


        Log.d(TAG, "initViews: " + mUserId + "  " + mUsername + "  " + mToken);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mStr = formatter.format(curDate);

        mBigEventsTitleTitle.setText("心得体会");
        mBigEventsSearchIv.setVisibility(View.GONE);
        mAudioRecoderUtils = new AudioRecoderUtils();
        player = new RecordPlayer(this);
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {

            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {

                filepath = filePath;
                Log.i("录音保存位置", filePath + "   " + filepath);
            }
        });
        //Button的touch监听
        mStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        XsnowPermissionUtil.getPermission(RecordActivity.this, Manifest.permission.RECORD_AUDIO);

                        mTimer.setBase(SystemClock.elapsedRealtime());//计时器清零
                        int hour = (int) ((SystemClock.elapsedRealtime() - mTimer.getBase()) / 1000 / 60);
                        mTimer.setFormat("0" + String.valueOf(hour) + ":%s");
                        mTimer.setTextColor(RecordActivity.this.getResources().getColor(R.color.text_red));
                        filepath = "";
                        mTimer.start();
                        DrawableTextUtil.setTextCompoundDrawables(RecordActivity.this, R.drawable.begin1, mStart, 20);
                        mAudioRecoderUtils.startRecord();
                        break;

                    case MotionEvent.ACTION_UP:
                        mTimer.setTextColor(RecordActivity.this.getResources().getColor(R.color.text_gray));
                        mTimer.stop();

                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
                        DrawableTextUtil.setTextCompoundDrawables(RecordActivity.this, R.drawable.begin0, mStart, 20);

                        break;
                }
                return true;
            }
        });

    }


    @OnClick({R.id.big_events_left_back, R.id.bf, R.id.start, R.id.sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.big_events_left_back:
                finish();
                break;
            case R.id.bf:
                if (TextUtils.isEmpty(filepath)) {
                    ToastUtils.showToast("请先录音");
                } else {
                    File file = new File(filepath);
                    player.playRecordFile(file);
                }
                Log.d("录音播放位置", "onViewClicked: " + filepath);
                break;
            case R.id.start:


                break;
            case R.id.sc:

                if (TextUtils.isEmpty(filepath)) {
                    ToastUtils.showToast("请先录音");
                } else {
                    final Map<String, Object> map = new HashMap<>();
                    map.put("typeId", "1");
                    map.put("recordTitle", "我的录音");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ImageUtils.imageUpload(getSharedPreferences("config", MODE_PRIVATE),map, filepath, "file");
                            Intent i = new Intent(RecordActivity.this, XdthActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }).start();
                }


                break;
        }
    }


}