package a9chou.com.fangjiazhuangApp.module.activity;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;

import org.litepal.crud.DataSupport;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.db.BookList;
import a9chou.com.fangjiazhuangApp.module.MainActivity;
import a9chou.com.fangjiazhuangApp.utils.DrawableTextUtil;
import a9chou.com.fangjiazhuangApp.utils.NetUtil;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.UrlUtil;
import a9chou.com.fangjiazhuangApp.utils.WebViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

import static a9chou.com.fangjiazhuangApp.module.activity.ActivityEditActivity.REQUEST_SELECT_FILE;
import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

public class ActivitySubjectH5 extends BaseActivity {

    private static final String TAG = "ActivitySubjectH5";
    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.activity_dyxx)
    LinearLayout mActivityDyxx;
    private LoadingDailog mDialog;
    private String i="";
    private String j="";
    private  String url="";
    private String mUrl;
    private String mId;
    private String mRecordId;
    private List<BookList> bookLists;
    private BookList mBookList;
    private String mUrl1;
    private String mName;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_dyxx;
    }

    @Override
    protected void initViews() {

        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(ActivitySubjectH5.this)
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        i=getIntent().getStringExtra("tit");
        j=getIntent().getStringExtra("bftit");
        bookLists = DataSupport.findAll(BookList.class);
        switch (i){
            case "主体责任":
                url = UrlUtil.FATHER_HTML + UrlUtil.SPACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
//            case "综合部":
//                url = UrlUtil.FATHER_HTML + UrlUtil.CD + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "总经理,党委副书记":
//                url = UrlUtil.FATHER_HTML + UrlUtil.DSPACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "纪委书记":
//                url = UrlUtil.FATHER_HTML + UrlUtil.CDSPACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "党委领导班子":
//                url = UrlUtil.FATHER_HTML + UrlUtil.LRACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "政治工作部":
//                url = UrlUtil.FATHER_HTML + UrlUtil.DPWACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "监察审计部(纪检办)":
//                url = UrlUtil.FATHER_HTML + UrlUtil.DIOACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
//            case "各支部":
//                url = UrlUtil.FATHER_HTML + UrlUtil.CPBACTION + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
//                break;
            case "综合党支部":
                url = UrlUtil.FATHER_HTML + UrlUtil.RPBACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "经营党支部":
                url = UrlUtil.FATHER_HTML + UrlUtil.RPBACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "工程党支部":
                url = UrlUtil.FATHER_HTML + UrlUtil.EPTBACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "运行党支部":
                url = UrlUtil.FATHER_HTML + UrlUtil.FPBACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "设备党支部":
                url = UrlUtil.FATHER_HTML + UrlUtil.EPPBACTION + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "党务公开":
                url = UrlUtil.FATHER_HTML + UrlUtil.PGOPA + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "厂务公开":
                url = UrlUtil.FATHER_HTML + UrlUtil.PFA + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "党纪检查":
                url = UrlUtil.FATHER_HTML + UrlUtil.DIP + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "团委委员":
                url = UrlUtil.FATHER_HTML + UrlUtil.MOLCMOTLC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "基层组织":
                url = UrlUtil.FATHER_HTML + UrlUtil.MOLGRO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "一学一做":
                url = UrlUtil.FATHER_HTML + UrlUtil.MOLOSAOD + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "五小QC":
                url = UrlUtil.FATHER_HTML + UrlUtil.MOLF + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "号手岗队":
                url = UrlUtil.FATHER_HTML + UrlUtil.MOLTGT + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "党员信息":
                url = UrlUtil.FATHER_HTML + UrlUtil.DYXXCK + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "党员转入转出":
                url = UrlUtil.FATHER_HTML + UrlUtil.ZRZC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "流动信息列表":
//                url = UrlUtil.FATHER_HTML + UrlUtil.DYXXCK + UrlUtil.getWithHas(this);
//                Log.i("url1",url);
                break;
            case "积极信息列表":
                url = UrlUtil.FATHER_HTML + UrlUtil.DYXXCK + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "专题教育":
                url = UrlUtil.FATHER_HTML + UrlUtil.TE + UrlUtil.getWithHas(this);
                Log.i("url1",url);
//                ToastUtils.showToast("点击了专题教育！！！");
                break;
            case "学习计划":
                url = UrlUtil.FATHER_HTML + UrlUtil.PGLP + UrlUtil.getWithHas(this);
                Log.i("url1",url);
//                ToastUtils.showToast("点击了学习计划！！！");
                break;
            case "视频教育":
                url = UrlUtil.FATHER_HTML + UrlUtil.VE + UrlUtil.getWithHas(this);
                Log.i("url1",url);
//                ToastUtils.showToast("点击了视频教育！！！");
                break;
            case "书香宁电":
                url = UrlUtil.FATHER_HTML + UrlUtil.BF + UrlUtil.getWithHas(this);
                Log.i("url1",url);
//                ToastUtils.showToast("点击了书香宁电！！！");
                break;
            case "立项项目":
                url = UrlUtil.FATHER_HTML + UrlUtil.DIPP + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "监察建议":
                url = UrlUtil.FATHER_HTML + UrlUtil.DISRS + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "整改完成":
                url = UrlUtil.FATHER_HTML + UrlUtil.DIRD + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "监督责任":
                url = UrlUtil.FATHER_HTML + UrlUtil.DISR + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "职代会":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMTWC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "权益保障":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMPORI + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "创新创效":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMIC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "竞技竞赛":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMCC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "女工委":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMWW + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "巾帼建功":
                url = UrlUtil.FATHER_HTML + UrlUtil.GMM + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "道德讲堂":
                url = UrlUtil.FATHER_HTML + UrlUtil.CCMLH + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "文化宣贯":
                url = UrlUtil.FATHER_HTML + UrlUtil.CCC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "民族团结":
                url = UrlUtil.FATHER_HTML + UrlUtil.CCNU + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "共建共荣":
                url = UrlUtil.FATHER_HTML + UrlUtil.CCCP + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "综合治理":
                url = UrlUtil.FATHER_HTML + UrlUtil.CCCC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "团员信息":
                url = UrlUtil.FATHER_HTML + UrlUtil.TYXX + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "青年信息":
                url = UrlUtil.FATHER_HTML + UrlUtil.QNXX + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "团青推优":
                url = UrlUtil.FATHER_HTML + UrlUtil.YT + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "班组管理":
                url = UrlUtil.FATHER_HTML + UrlUtil.GM + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "统计分析":
                url = UrlUtil.FATHER_HTML + UrlUtil.SASC+"Y="+getI()+"&W="+getAll_i()+"&Z="+getAll()+ UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "党员接转":
                url = UrlUtil.FATHER_HTML + UrlUtil.ZRZC + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "流动党员":
                url = UrlUtil.FATHER_HTML + UrlUtil.DYLDXX + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "积极分子":
                url = UrlUtil.FATHER_HTML + UrlUtil.JJFZXX + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "综合党支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.PBCO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "经营党支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.PBMO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "运行党支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.PBFO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "工程党支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.PBEOG + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "设备党支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.PBEO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "综合分工会组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.DLCO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "经营分工会组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.DLMO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "工程分工会组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.DLEOG + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "设备分工会组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.DLEO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "运行分工会组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.DLFO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "综合团支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.IMBO+ UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "经营团支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.BGBO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "工程团支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.ERBO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "设备团支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.UBBO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "运行团支部组织机构":
                url = UrlUtil.FATHER_HTML + UrlUtil.OGBLO + UrlUtil.getWithHas(this);
                Log.i("url1",url);
                break;
            case "支部积极分子":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CPB0 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CPB1 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CPB2 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CPB3 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CPB4 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }
                break;
            case "支部党员信息":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMPB0 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMPB1 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMPB2 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMPB3 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMPB4 + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }

                break;
            case "党课课件":
             switch(j){
                case "综合党支部":
                    url = UrlUtil.FATHER_HTML + UrlUtil.LZH + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                    break;
                case "经营党支部":
                    url = UrlUtil.FATHER_HTML + UrlUtil.LJY + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                    break;
                case "运行党支部":
                    url = UrlUtil.FATHER_HTML + UrlUtil.LYX + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                    break;
                case "设备党支部":
                    url = UrlUtil.FATHER_HTML + UrlUtil.LSB + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                    break;
                case "工程党支部":
                    url = UrlUtil.FATHER_HTML + UrlUtil.LGC + UrlUtil.getWithHas(this);
                    Log.i("url1",url);
                    break;
            }
                break;
            case "工运课件":
                switch(j){
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.TZH + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.TJY + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.TYX + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.TSB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.TGC + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }

                break;
            case "团课课件":
                switch(j){
                    case "综合团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CZH + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CJY + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CYX + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CSB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.CGC + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }
                break;
            case "组织机构":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBCO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBMO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBFO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEOG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLCO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLMO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLFO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEO + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEOG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }
                break;
            case "责任清单":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBCLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBMLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBFLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBELR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBELRG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLCLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLMLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLFLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLELR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLELRG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMBLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.BGBLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.OGBLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.UBBLR + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.ERB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }

                break;
            case "党员发展":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBCTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBMTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBFTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBETDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBETDPMG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLCTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLMTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLFTDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLETDPM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLETDPMG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMBDL + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.BGBDL + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.OGBDL + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.UBBDL + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.ERBDLM + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }

                break;
            case "管理党务公开":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBCOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBMOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBFOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEOPAG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLCOPAS + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLMOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLFOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEOPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEOPAG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.IMBP + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.BGBP + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.OGBPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.UBBPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程团支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.ERBPA + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }
                break;
            case "支部品牌":
                switch(j){
                    case "综合党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBCBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBMBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBFBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程党支部":
                        url = UrlUtil.FATHER_HTML + UrlUtil.PBEBBG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "综合分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLCBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "经营分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLMBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "运行分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLFBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "设备分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEBB + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                    case "工程分工会":
                        url = UrlUtil.FATHER_HTML + UrlUtil.DLEBBG + UrlUtil.getWithHas(this);
                        Log.i("url1",url);
                        break;
                }

                break;
        }


        mShykTitleTitle.setText(i);
        DrawableTextUtil.setTextCompoundDrawablesLeft(ActivitySubjectH5.this, R.drawable.screen, mShykXz, 0);
        mShykXz.setVisibility(View.GONE);
//        mShykXz.setText("筛选");
        //        设置支持js
        WebViewUtils.setWebView(mDialog, mWeb, url, ActivitySubjectH5.this, "android");
//         获取webview中的标题，然后传到我的title中
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("ANDROID_LAB", "TITLE=" + title);
                mShykTitleTitle.setText(title);
            }
            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback< Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        };
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWeb.addJavascriptInterface(new getData(), "SubjectH5");
        mWeb.setWebChromeClient(wvcc);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            ToastUtils.showToast("上传图片失败");
    }

    public class getData{

        @JavascriptInterface
        public int getAll(){
            int all=getIntent().getIntExtra("all",0);
            return all;
        }
        @JavascriptInterface
        public int getI(){
            int i=getIntent().getIntExtra("num",0);
            return i;
        }
        @JavascriptInterface
        public int getAll_i(){
            int all_i=getIntent().getIntExtra("all_num",0);
            return all_i;
        }

    }


    public int getAll(){
        int all=getIntent().getIntExtra("all",0);
        return all;
    }
    public int getI(){
        int i=getIntent().getIntExtra("num",0);
        return i;
    }
    public int getAll_i(){
        int all_i=getIntent().getIntExtra("all_num",0);
        return all_i;
    }

    @JavascriptInterface
    public void goBackActive() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mWeb.canGoBack()) {
                    mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    mWeb.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    //在线学习 跳转 阅读角界面
    @JavascriptInterface
    public void readingCorner(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] a = str.split(",");
                mUrl = a[0];
                Log.i("zm9316","mUrl::::"+mUrl);
                mId = a[2];
                Log.i("zm9316","mId::::"+mId);
                mRecordId = a[1];
                Log.i("zm9316","mRecordId::::"+mRecordId);
                Log.d(TAG, "run: id   ==>" + mUrl + "  " + mId);
                mBookList = new BookList();
//                mBookList.setBookpath("http://111.205.44.47/treps" + mUrl);
                mBookList.setBookpath(IP+"/treps" + mUrl);
                if (bookLists.size() == 0) {
                    mBookList.save();
                } else {
                    boolean isSave = false;
                    for (int i = 0; i < bookLists.size(); i++) {

                        if (bookLists.get(i).getBookpath().equals(mBookList.getBookpath())) {
                            isSave = true;
                            mBookList = bookLists.get(i);
                            Log.i("zm9316",mBookList.getBookpath()+"::::"+mBookList.getCharset());
                        }
                    }
                    if (!isSave) {
                        mBookList.save();
                    }
                }
                ReadActivity.openBook(mBookList, ActivitySubjectH5.this, mId, mRecordId);
            }
        });
    }
    //在线学习 跳转 播放视频界面
    @JavascriptInterface
    public void goToCinema(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] array = content.split(",");
                mUrl1 = array[0];
                mName = array[1];
                if (NetUtil.isWifiConnected(ActivitySubjectH5.this)) {
                    Intent intent = new Intent(ActivitySubjectH5.this, OldMoviesActivity.class);
                    intent.putExtra("url", mUrl1);
                    intent.putExtra("name", mName);
                    startActivity(intent);
                    Log.d(TAG, "run: " + content);
                } else {
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(ActivitySubjectH5.this);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("温馨提示！");
                    dialog.setMessage("当前不是wifi状态");
                    dialog.setPositiveButton("土豪无所谓",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ActivitySubjectH5.this, OldMoviesActivity.class);
                                    intent.putExtra("url", mUrl1);
                                    intent.putExtra("name", mName);
                                    startActivity(intent);
                                }
                            });
                    dialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    // 显示
                    dialog.show();

                }
            }
        });
    }



    @OnClick({R.id.shyk_left_back, R.id.shyk_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                //                判断webview是否可以
                if (mWeb.canGoBack()) {
                    mWeb.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.shyk_xz:
                ToastUtils.showToast("筛选");
                View popupView = ActivitySubjectH5.this.getLayoutInflater().inflate(R.layout.popupwindow, null);
                //   创建PopupWindow对象，指定宽度和高度
                PopupWindow window = new PopupWindow(popupView, 300, 400);
                //  设置动画
                window.setAnimationStyle(R.style.popup_window_anim);
                //   设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                //   设置可以获取焦点
                window.setFocusable(true);
                //  设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                //  更新popupwindow的状态
                window.update();
                //  以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(mShykXz, 0, 20);
                break;
        }
    }

    //返回记住当前状态
    @Override
    protected void onRestart() {
        super.onRestart();
        bookLists = DataSupport.findAll(BookList.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWeb.canGoBack()) {
                mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mWeb.goBack();
                return true;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }
}