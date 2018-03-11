package a9chou.com.fangjiazhuangApp.module.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.vise.log.ViseLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.SwitchVideoModel;
import a9chou.com.fangjiazhuangApp.utils.OnTransitionListener;
import a9chou.com.fangjiazhuangApp.weight.SampleVideo;
import butterknife.BindView;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;


public class OldMoviesActivity extends BaseActivity {
    private static final String TAG = "OldMoviesActivity";
    @BindView(R.id.activity_old_movies)
    RelativeLayout mActivityOldMovies;
    @BindView(R.id.old_video_player)
    SampleVideo mOldVideoPlayer;
    private OrientationUtils mOrientationUtils;
    private boolean isTransition;
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    private Transition mTransition;
    private String mAa;
    private String mMovieUrl;
    private String mMovieName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_old_movies;
    }

    @Override
    protected void initViews() {

        Intent intent = getIntent();
        mMovieUrl = intent.getStringExtra("url");
        mMovieName = intent.getStringExtra("name");
        Log.d(TAG, "initViews: " + mMovieName + "  " + mMovieUrl);

        init();

    }

    private void init() {
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getPath() + "/movies.mp4";
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
//         边缓边存   视频下载地址  亲测可用
        String downLoadMovie = "https://raw.githubusercontent.com/danikula/AndroidVideoCache/master/files/orange1.mp4";
        String source1 = IP+"/treps" + mMovieUrl;
//        String source1 = IP+"/treps" + "/static/mobile/wp/files/nimajiangcun.mp4";
        ViseLog.e(mMovieUrl);
        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);
        String source2 = IP+"/treps" + mMovieUrl;
        String name2 = "清晰";
        SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);
        list.add(switchVideoModel2);

        mOldVideoPlayer.setUp(list, true, mMovieName);
        //增加title
        mOldVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        mOldVideoPlayer.getBackButton().setVisibility(View.VISIBLE);

        //设置旋转
        mOrientationUtils = new OrientationUtils(this, mOldVideoPlayer);

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        mOldVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrientationUtils.resolveByClick();
            }
        });

        //是否可以滑动调整
        mOldVideoPlayer.setIsTouchWiget(true);

        //设置返回按键功能
        mOldVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //过渡动画
        initTransition();
    }


    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(mOldVideoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            mOldVideoPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        mTransition = getWindow().getSharedElementEnterTransition();
        if (mTransition != null) {
            mTransition.addListener(new OnTransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    mOldVideoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        mOldVideoPlayer.onVideoPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        mOldVideoPlayer.onVideoResume();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOrientationUtils != null)
            mOrientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (mOrientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mOldVideoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        mOldVideoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }

}