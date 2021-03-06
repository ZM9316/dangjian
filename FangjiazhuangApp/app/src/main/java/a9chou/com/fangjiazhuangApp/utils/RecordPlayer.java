package a9chou.com.fangjiazhuangApp.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.File;


/**
 * 播放录音
 */

public class RecordPlayer {
    private static MediaPlayer mediaPlayer;

    private Context mcontext;

    public RecordPlayer(Context context) {
        this.mcontext = context;
    }

    // 播放录音文件
    public void playRecordFile(File file) {
//        if (file.exists() && file != null) {
//        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(mcontext, uri);
        }

        mediaPlayer.start();

        //监听MediaPlayer播放完成
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer paramMediaPlayer) {

                mediaPlayer = null;
            }
        });

//        }
    }

    public void playRecordOnNet() {

    }


    // 暂停播放录音
    public void pausePalyer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Log.e("TAG", "暂停播放");
        }

    }

    // 停止播放录音
    public void stopPalyer() {
        // 这里不调用stop()，调用seekto(0),把播放进度还原到最开始
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            Log.e("TAG", "停止播放");
        }
    }
}
