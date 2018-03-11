package a9chou.com.fangjiazhuangApp.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.utils.Config;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class PageModeDialog extends Dialog {
    @BindView(R.id.tv_simulation)
    TextView mTvSimulation;
    @BindView(R.id.tv_cover)
    TextView mTvCover;
    @BindView(R.id.tv_slide)
    TextView mTvSlide;
    @BindView(R.id.tv_none)
    TextView mTvNone;

//    @Bind(R.id.tv_simulation)
//    TextView tv_simulation;
//    @Bind(R.id.tv_cover)
//    TextView tv_cover;
//    @Bind(R.id.tv_slide)
//    TextView tv_slide;
//    @Bind(R.id.tv_none)
//    TextView tv_none;

    private Config config;
    private PageModeListener pageModeListener;

    private PageModeDialog(Context context, boolean flag, OnCancelListener listener) {
        super(context, flag, listener);
    }

    public PageModeDialog(Context context) {
        this(context, R.style.setting_dialog);
    }

    public PageModeDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_pagemode);
        // 初始化View注入
        ButterKnife.bind(this);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);

        config = Config.getInstance();
        selectPageMode(config.getPageMode());
    }

    @OnClick({R.id.tv_simulation, R.id.tv_cover, R.id.tv_slide, R.id.tv_none})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_simulation:
                selectPageMode(Config.PAGE_MODE_SIMULATION);
                setPageMode(Config.PAGE_MODE_SIMULATION);
                break;
            case R.id.tv_cover:
                selectPageMode(Config.PAGE_MODE_COVER);
                setPageMode(Config.PAGE_MODE_COVER);
                break;
            case R.id.tv_slide:
                selectPageMode(Config.PAGE_MODE_SLIDE);
                setPageMode(Config.PAGE_MODE_SLIDE);
                break;
            case R.id.tv_none:
                selectPageMode(Config.PAGE_MODE_NONE);
                setPageMode(Config.PAGE_MODE_NONE);
                break;
        }
    }

    //设置翻页
    public void setPageMode(int pageMode) {
        config.setPageMode(pageMode);
        if (pageModeListener != null) {
            pageModeListener.changePageMode(pageMode);
        }
    }

    //选择怕翻页
    private void selectPageMode(int pageMode) {
        if (pageMode == Config.PAGE_MODE_SIMULATION) {
            setTextViewSelect(mTvSimulation, true);
            setTextViewSelect(mTvCover, false);
            setTextViewSelect(mTvSlide, false);
            setTextViewSelect(mTvNone, false);
        } else if (pageMode == Config.PAGE_MODE_COVER) {
            setTextViewSelect(mTvSimulation, false);
            setTextViewSelect(mTvCover, true);
            setTextViewSelect(mTvSlide, false);
            setTextViewSelect(mTvNone, false);
        } else if (pageMode == Config.PAGE_MODE_SLIDE) {
            setTextViewSelect(mTvSimulation, false);
            setTextViewSelect(mTvCover, false);
            setTextViewSelect(mTvSlide, true);
            setTextViewSelect(mTvNone, false);
        } else if (pageMode == Config.PAGE_MODE_NONE) {
            setTextViewSelect(mTvSimulation, false);
            setTextViewSelect(mTvCover, false);
            setTextViewSelect(mTvSlide, false);
            setTextViewSelect(mTvNone, true);
        }
    }

    //设置按钮选择的背景
    private void setTextViewSelect(TextView textView, Boolean isSelect) {
        if (isSelect) {
            textView.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.button_select_bg));
            textView.setTextColor(getContext().getResources().getColor(R.color.read_dialog_button_select));
        } else {
            textView.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.button_bg));
            textView.setTextColor(getContext().getResources().getColor(R.color.white));
        }
    }

    public void setPageModeListener(PageModeListener pageModeListener) {
        this.pageModeListener = pageModeListener;
    }
    public interface PageModeListener {
        void changePageMode(int pageMode);
    }
}