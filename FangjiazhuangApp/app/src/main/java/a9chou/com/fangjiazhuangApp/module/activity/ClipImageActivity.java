package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.weight.ClipViewLayout;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.R.id.clipViewLayout1;
import static a9chou.com.fangjiazhuangApp.R.id.clipViewLayout2;

/**
 * 头像裁剪Activity
 */
public class ClipImageActivity extends BaseActivity {
    private static final String TAG = "ClipImageActivity";
    @BindView(R.id.btn_cancel)
    TextView mBtnCancel;
    @BindView(R.id.bt_ok)
    TextView mBtOk;
    @BindView(R.id.bottom)
    RelativeLayout mBottom;
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.clipViewLayout1)
    ClipViewLayout mClipViewLayout1;
    @BindView(R.id.clipViewLayout2)
    ClipViewLayout mClipViewLayout2;

    private int type;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_clip_image;
    }

    @Override
    protected void initViews() {
        mShykTitleTitle.setText("移动和缩放");
        mShykXz.setVisibility(View.GONE);

        type = getIntent().getIntExtra("type", 1);

    }

    @OnClick({R.id.shyk_left_back, clipViewLayout1, clipViewLayout2, R.id.btn_cancel, R.id.bt_ok, R.id.bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
            case clipViewLayout1:
                break;
            case clipViewLayout2:
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:

                generateUriAndReturn();
                break;
            case R.id.bottom:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == 1) {
            mClipViewLayout1.setVisibility(View.VISIBLE);
            mClipViewLayout2.setVisibility(View.GONE);
            //设置图片资源
            mClipViewLayout1.setImageSrc(getIntent().getData());
        } else {
            mClipViewLayout2.setVisibility(View.VISIBLE);
            mClipViewLayout1.setVisibility(View.GONE);
            //设置图片资源
            mClipViewLayout2.setImageSrc(getIntent().getData());
        }
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        if (type == 1) {
            zoomedCropBitmap = mClipViewLayout1.clip();
        } else {
            zoomedCropBitmap = mClipViewLayout2.clip();
        }
        if (zoomedCropBitmap == null) {
            Log.d(TAG, "generateUriAndReturn: zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.d(TAG, "generateUriAndReturn:Cannot open file: " + mSaveUri, ex);

            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    @OnClick(R.id.shyk_left_back)
    public void onViewClicked() {
    }
}
