package a9chou.com.fangjiazhuangApp.module.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.MyPagerAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.db.BookCatalogue;
import a9chou.com.fangjiazhuangApp.utils.Config;
import a9chou.com.fangjiazhuangApp.utils.FileUtils;
import a9chou.com.fangjiazhuangApp.utils.PageFactory;
import butterknife.BindView;

import static a9chou.com.fangjiazhuangApp.R.id.toolbar;


/**
 * Created by Administrator on 2017/9/11.
 */
public class MarkActivity extends BaseActivity {
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.pager)
    ViewPager mPager;
    private PageFactory pageFactory;
    private Config config;
    private Typeface typeface;
    private ArrayList<BookCatalogue> catalogueList = new ArrayList<>();
    private DisplayMetrics dm;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_mark;
    }

    @Override
    protected void initViews() {
        pageFactory = PageFactory.getInstance();
        config = Config.getInstance();
        dm = getResources().getDisplayMetrics();
        typeface = config.getTypeface();

        setSupportActionBar(mToolbar);
        //设置导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(FileUtils.getFileName(pageFactory.getBookPath()));
        }

        setTabsValue();
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), pageFactory.getBookPath()));
        mTabs.setViewPager(mPager);
    }

    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mTabs.setShouldExpand(true);//所有初始化要在setViewPager方法之前
        // 设置Tab的分割线是透明的
        mTabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mTabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        mTabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        mTabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        //设置Tab标题文字的字体
        mTabs.setTypeface(typeface, 0);
        // 设置Tab Indicator的颜色
        mTabs.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        // 取消点击Tab时的背景色
        mTabs.setTabBackground(0);

        // pagerSlidingTabStrip.setDividerPadding(18);
    }
}
