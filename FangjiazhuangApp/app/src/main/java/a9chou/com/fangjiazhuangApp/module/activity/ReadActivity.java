package a9chou.com.fangjiazhuangApp.module.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.db.BookList;
import a9chou.com.fangjiazhuangApp.db.BookMarks;
import a9chou.com.fangjiazhuangApp.utils.BrightnessUtil;
import a9chou.com.fangjiazhuangApp.utils.Config;
import a9chou.com.fangjiazhuangApp.utils.PageFactory;
import a9chou.com.fangjiazhuangApp.weight.PageWidget;
import a9chou.com.fangjiazhuangApp.weight.dialog.PageModeDialog;
import a9chou.com.fangjiazhuangApp.weight.dialog.SettingDialog;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.R.id.appbar;
import static a9chou.com.fangjiazhuangApp.R.id.rl_bottom;
import static a9chou.com.fangjiazhuangApp.R.id.rl_progress;
import static a9chou.com.fangjiazhuangApp.R.id.tv_dayornight;
import static a9chou.com.fangjiazhuangApp.R.id.tv_progress;

public class ReadActivity extends BaseActivity {
    private final static String EXTRA_BOOK = "bookList";
    private final static int MESSAGE_CHANGEPROGRESS = 1;
    private static final String TAG = "ReadActivity";
    @BindView(R.id.bookpage)
    PageWidget mBookpage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(appbar)
    AppBarLayout mAppbar;
    @BindView(tv_progress)
    TextView mTvProgress;
    @BindView(rl_progress)
    RelativeLayout mRlProgress;
    @BindView(R.id.tv_pre)
    TextView mTvPre;
    @BindView(R.id.sb_progress)
    SeekBar mSbProgress;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.tv_directory)
    TextView mTvDirectory;
    @BindView(tv_dayornight)
    TextView mTvDayornight;
    @BindView(R.id.tv_pagemode)
    TextView mTvPagemode;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.bookpop_bottom)
    LinearLayout mBookpopBottom;
    @BindView(rl_bottom)
    RelativeLayout mRlBottom;

    private Config config;
    private BookList bookList;
    private PageFactory pageFactory;
    private int screenWidth, screenHeight;
    // popwindow是否显示
    private Boolean isShow = false;
    private SettingDialog mSettingDialog;
    private PageModeDialog mPageModeDialog;
    private Boolean mDayOrNight;
    private String mP;
    private String mUsername;
    private String mUserId;
    private String mToken;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_read;
    }

    @Override
    protected void initViews() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        mUsername = sp.getString("username", "");
        mUserId = sp.getString("userId", "");
        mToken = sp.getString("token", "");


        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 19) {
            mBookpage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.return_button);
//        返回按钮
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + mP);
                finish();

            }
        });
        config = Config.getInstance();
        pageFactory = PageFactory.getInstance();
        IntentFilter mfilter = new IntentFilter();
        mfilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mfilter.addAction(Intent.ACTION_TIME_TICK);
        mSettingDialog = new SettingDialog(this);
        mPageModeDialog = new PageModeDialog(this);
        //获取屏幕宽高
        WindowManager manage = getWindowManager();
        Display display = manage.getDefaultDisplay();
        Point displaysize = new Point();
        display.getSize(displaysize);
        screenWidth = displaysize.x;
        screenHeight = displaysize.y;
        //保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //隐藏
        hideSystemUI();
        //改变屏幕亮度
        if (!config.isSystemLight()) {
            BrightnessUtil.setBrightness(this, config.getLight());
        }
        //获取intent中的携带的信息
        Intent intent = getIntent();
        bookList = (BookList) intent.getSerializableExtra(EXTRA_BOOK);
//
        mBookpage.setPageMode(config.getPageMode());
        pageFactory.setPageWidget(mBookpage);

        try {
            pageFactory.openBook(bookList);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("zm9316","e:"+e+"bookList:::"+bookList);
            Toast.makeText(this, "打开电子书失败", Toast.LENGTH_SHORT).show();
        }

        initDayOrNight();


        mSbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float pro;

            // 触发操作，拖动
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro = (float) (progress / 10000.0);
                showProgress(pro);
            }

            // 表示进度条刚开始拖动，开始拖动时候触发的操作
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // 停止拖动时候
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pageFactory.changeProgress(pro);
            }
        });

        mPageModeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                hideSystemUI();
            }
        });

        mPageModeDialog.setPageModeListener(new PageModeDialog.PageModeListener() {
            @Override
            public void changePageMode(int pageMode) {
                mBookpage.setPageMode(pageMode);
            }
        });

        mSettingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                hideSystemUI();
            }
        });

        mSettingDialog.setSettingListener(new SettingDialog.SettingListener() {
            @Override
            public void changeSystemBright(Boolean isSystem, float brightness) {
                if (!isSystem) {
                    BrightnessUtil.setBrightness(ReadActivity.this, brightness);
                } else {
                    int bh = BrightnessUtil.getScreenBrightness(ReadActivity.this);
                    BrightnessUtil.setBrightness(ReadActivity.this, bh);
                }
            }

            @Override
            public void changeFontSize(int fontSize) {
                pageFactory.changeFontSize(fontSize);
            }

            @Override
            public void changeTypeFace(Typeface typeface) {
                pageFactory.changeTypeface(typeface);
            }

            @Override
            public void changeBookBg(int type) {
                pageFactory.changeBookBg(type);
            }
        });

        pageFactory.setPageEvent(new PageFactory.PageEvent() {
            @Override
            public void changeProgress(float progress) {
                Message message = new Message();
                message.what = MESSAGE_CHANGEPROGRESS;
                message.obj = progress;
                mHandler.sendMessage(message);
            }
        });

        mBookpage.setTouchListener(new PageWidget.TouchListener() {
            @Override
            public void center() {
                if (isShow) {
                    hideReadSetting();
                } else {
                    showReadSetting();
                }
            }

            @Override
            public Boolean prePage() {
                if (isShow) {
                    return false;
                }

                pageFactory.prePage();
                if (pageFactory.isfirstPage()) {
                    return false;
                }

                return true;
            }

            @Override
            public Boolean nextPage() {
                if (isShow) {
                    return false;
                }

                pageFactory.nextPage();
                if (pageFactory.islastPage()) {
                    return false;
                }
                return true;
            }

            @Override
            public void cancel() {
                pageFactory.cancelPage();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

//        setProgress()

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_CHANGEPROGRESS:
                    float progress = (float) msg.obj;
                    setSeekBarProgress(progress);
                    break;
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        if (!isShow) {
            hideSystemUI();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pageFactory.clear();
        mBookpage = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isShow) {
                hideReadSetting();
                return true;
            }
            if (mSettingDialog.isShowing()) {
                mSettingDialog.hide();
                return true;
            }
            if (mPageModeDialog.isShowing()) {
                mPageModeDialog.hide();
                return true;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_bookmark) {
            List<BookMarks> bookMarksList = DataSupport.where("bookpath = ? and begin = ?", pageFactory.getBookPath(), pageFactory.getCurrentPage().getBegin() + "").find(BookMarks.class);

            if (!bookMarksList.isEmpty()) {
                Toast.makeText(ReadActivity.this, "该书签已存在", Toast.LENGTH_SHORT).show();
            } else {
                BookMarks bookMarks = new BookMarks();
                String word = "";
                for (String line : pageFactory.getCurrentPage().getLines()) {
                    word += line;
                }
                try {
                    SimpleDateFormat sf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm ss");
                    String time = sf.format(new Date());
                    bookMarks.setTime(time);
                    bookMarks.setBegin(pageFactory.getCurrentPage().getBegin());
                    bookMarks.setText(word);
                    bookMarks.setBookpath(pageFactory.getBookPath());
                    bookMarks.save();

                    Toast.makeText(ReadActivity.this, "书签添加成功", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(ReadActivity.this, "该书签已存在", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ReadActivity.this, "添加书签失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public static boolean openBook(final BookList bookList, Activity context, String id, String recordId) {
        if (bookList == null) {
            throw new NullPointerException("BookList can not be null");
        }

        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra(EXTRA_BOOK, bookList);
        intent.putExtra("id", id);
        intent.putExtra("recordId", recordId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        context.startActivity(intent);
        return true;
    }

    /**
     * 隐藏菜单。沉浸式阅读
     */
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    //显示书本进度
    public void showProgress(float progress) {
        if (mRlProgress.getVisibility() != View.VISIBLE) {
            mRlProgress.setVisibility(View.VISIBLE);
        }
        setProgress(progress);
    }

    //隐藏书本进度
    public void hideProgress() {
        mRlProgress.setVisibility(View.GONE);
    }

    public void initDayOrNight() {
        mDayOrNight = config.getDayOrNight();
        if (mDayOrNight) {
            mTvDayornight.setText(getResources().getString(R.string.read_setting_day));
        } else {
            mTvDayornight.setText(getResources().getString(R.string.read_setting_night));
        }
    }

    //改变显示模式
    public void changeDayOrNight() {
        if (mDayOrNight) {
            mDayOrNight = false;
            mTvDayornight.setText(getResources().getString(R.string.read_setting_night));
        } else {
            mDayOrNight = true;
            mTvDayornight.setText(getResources().getString(R.string.read_setting_day));
        }
        config.setDayOrNight(mDayOrNight);
        pageFactory.setDayOrNight(mDayOrNight);
    }

    private void setProgress(float progress) {
        DecimalFormat decimalFormat = new DecimalFormat("00.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        //format 返回的是字符串
        mP = decimalFormat.format(progress * 100.0);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", mUsername);
        map.put("userId", mUserId);
        map.put("token", mToken);
        map.put("recordId", getIntent().getStringExtra("recordId"));
        map.put("nowProgress", mP);
        map.put("typeId", "party_book");
        Log.d(TAG, "setProgress: " + map.toString());
        ViseHttp.GET("mobile/wp/wpMyRecord/updateData").addParams(map).request(new ACallback<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "onSuccess: " + data);
                mTvProgress.setText(mP + "%");
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                Log.d(TAG, "onFail: " + errMsg + "  " + errCode);
                mTvProgress.setText(mP + "%");
            }
        });
        Log.d(TAG, "setProgress: " + mP);

    }

    public void setSeekBarProgress(float progress) {
        mSbProgress.setProgress((int) (progress * 10000));
    }

    private void showReadSetting() {
        isShow = true;
        mRlProgress.setVisibility(View.GONE);

        showSystemUI();

        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.dialog_top_enter);
        mRlBottom.startAnimation(topAnim);
        mAppbar.startAnimation(topAnim);
        mRlBottom.setVisibility(View.VISIBLE);
        mAppbar.setVisibility(View.VISIBLE);
    }

    private void hideReadSetting() {
        isShow = false;
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.dialog_top_exit);
        if (mRlBottom.getVisibility() == View.VISIBLE) {
            mRlBottom.startAnimation(topAnim);
        }
        if (mAppbar.getVisibility() == View.VISIBLE) {
            mAppbar.startAnimation(topAnim);
        }
//        ll_top.startAnimation(topAnim);
        mRlBottom.setVisibility(View.GONE);
        mAppbar.setVisibility(View.GONE);
        hideSystemUI();
    }


    @OnClick({tv_progress, rl_progress, R.id.tv_pre, R.id.sb_progress, R.id.tv_next, R.id.tv_directory, tv_dayornight, R.id.tv_pagemode, R.id.tv_setting, R.id.bookpop_bottom, rl_bottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case tv_progress:
                break;
            case rl_progress:
                break;
            case R.id.tv_pre:
                pageFactory.preChapter();
                break;
            case R.id.sb_progress:
                break;
            case R.id.tv_next:
                pageFactory.nextChapter();
                break;
            case R.id.tv_directory:
                Intent intent = new Intent(ReadActivity.this, MarkActivity.class);
                startActivity(intent);
                break;
            case tv_dayornight:
                changeDayOrNight();
                break;
            case R.id.tv_pagemode:
                hideReadSetting();
                mPageModeDialog.show();
                break;
            case R.id.tv_setting:
                hideReadSetting();
                mSettingDialog.show();
                break;
            case R.id.bookpop_bottom:
                break;
            case rl_bottom:
                break;
        }
    }


}
