package a9chou.com.fangjiazhuangApp.module.activity;

import android.util.Log;
import com.sh.shvideolibrary.VideoInputDialog;
import java.util.HashMap;
import java.util.Map;
import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.utils.ImageUtils;

public class ScspActivity extends BaseActivity implements VideoInputDialog.VideoCall {

    private String path;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_scsp;
    }

    @Override
    protected void initViews() {
//        显示视频录制控件
        VideoInputDialog.show(getSupportFragmentManager(), ScspActivity.this, VideoInputDialog.Q720, ScspActivity.this);
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
            }
        }).start();

    }
}