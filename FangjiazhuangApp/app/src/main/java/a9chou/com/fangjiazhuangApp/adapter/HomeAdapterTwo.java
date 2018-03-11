package a9chou.com.fangjiazhuangApp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.utils.cipher.MD5;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityBranchManagement;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityCulturalcivilization;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityGroupBranch;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityLabour;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityNoticeBulletin;
import a9chou.com.fangjiazhuangApp.module.activity.ActivityPartyBranch;
import a9chou.com.fangjiazhuangApp.module.activity.ActivitySubjectH5;
import a9chou.com.fangjiazhuangApp.module.activity.DzjxActivity;
import a9chou.com.fangjiazhuangApp.module.activity.DzzglActivity;
import a9chou.com.fangjiazhuangApp.module.activity.GhgzglActivity;
import a9chou.com.fangjiazhuangApp.module.activity.JjjcglActivity;
import a9chou.com.fangjiazhuangApp.module.activity.LxyzhActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MoreDynamicActivity;
import a9chou.com.fangjiazhuangApp.module.activity.MyXXActivity;
import a9chou.com.fangjiazhuangApp.module.activity.PartyServiceActivity;
import a9chou.com.fangjiazhuangApp.module.activity.PartygroupActivity;
import a9chou.com.fangjiazhuangApp.module.activity.TqgzglActivity;
import a9chou.com.fangjiazhuangApp.module.dao.DjdtBean;
import a9chou.com.fangjiazhuangApp.module.dao.OrgId;
import a9chou.com.fangjiazhuangApp.utils.Constants;
import a9chou.com.fangjiazhuangApp.utils.ToastUtils;
import a9chou.com.fangjiazhuangApp.utils.Util;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

import static a9chou.com.fangjiazhuangApp.R.id.djdt_relative;
import static a9chou.com.fangjiazhuangApp.utils.Config.IP;
import static android.content.Context.MODE_PRIVATE;

/**
 * date: 2017/9/29.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class HomeAdapterTwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int XBANNER = 0;
    public static final int GROUP = 1;
    public static final int TITLE = 2;
    public static final int LIST = 3;
    public static final int FOOTER = 4;
    private static final String TAG = "HomeAdapterTwo";
    private Context mContext;
    private List<DjdtBean.DataBean> mDataBeen;
    private List<Integer> mImages;
    private List<String> mTitles;
    private OnItemClickListener mOnItemClickListener = null;
    //    private int x = 0;
    private String mMas;
    private int type;
    private Button mBtnPay;
    private RelativeLayout mWeiChat;
    private RelativeLayout mALi;
    private TextView mPrice;
    private ImageView mAlid;
    private ImageView mWxd;
    private PayReq req;
    private HashMap<String, String> resultunifiedorder;
    private StringBuffer sb;
    private final IWXAPI msgApi;
    private SharedPreferences mEd;
    private Map<String, String> mMap;
    private String mUserId;
    private String mToken;
    private String mUserName;

    public HomeAdapterTwo(int x, Context context, List<DjdtBean.DataBean> dataBeen, List<Integer> images, List<String> titles) {

        type = x;
        mContext = context;
        mDataBeen = dataBeen;
        mImages = images;
        mTitles = titles;
        msgApi = WXAPIFactory.createWXAPI(mContext, null);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == XBANNER) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.home_rv_vp_item, parent, false);
            XbannerViewHolder xbannerViewHolder = new XbannerViewHolder(v);
            return xbannerViewHolder;
        } else if (viewType == GROUP) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.home_rv_item_group, parent, false);
            GroupViewHolder groupViewHolder = new GroupViewHolder(v);
            return groupViewHolder;
        } else if (viewType == TITLE) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.home_list_titleitem, parent, false);
            TitleViewHolder titleViewHolder = new TitleViewHolder(v);
            return titleViewHolder;
        } else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.home_rv_item, parent, false);
            ListViewHolder listViewHolder = new ListViewHolder(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        //注意这里使用getTag方法获取position
                        mOnItemClickListener.onItemClick(v, (int) v.getTag());
                        Log.d(TAG, "onClick: +tag" + v.getTag());
                    }
                }
            });
            return listViewHolder;
        }
//        else {
//            return new FooterView(LayoutInflater.from(mContext).inflate(R.layout.footer, parent, false));
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof XbannerViewHolder) {
            ((XbannerViewHolder) holder).mXBanner.setData(mImages, mTitles);

            ((XbannerViewHolder) holder).mXBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(mContext).load(mImages.get(position)).into((ImageView) view);
                }
            });

//                设置XBanner的页面切换特效
//            Rotate
            ((XbannerViewHolder) holder).mXBanner.setPageTransformer(Transformer.Default);
//                设置XBanner页面切换的时间，即动画时长
            ((XbannerViewHolder) holder).mXBanner.setPageChangeDuration(0);
        } else if (holder instanceof GroupViewHolder) {

            ((GroupViewHolder) holder).mLxyz.setOnClickListener(this);
            ((GroupViewHolder) holder).mDqjx.setOnClickListener(this);
            ((GroupViewHolder) holder).mDwgl.setOnClickListener(this);
            ((GroupViewHolder) holder).mJjgl.setOnClickListener(this);
            ((GroupViewHolder) holder).mGhgl.setOnClickListener(this);
            ((GroupViewHolder) holder).mTqgl.setOnClickListener(this);
            ((GroupViewHolder) holder).mTzgg.setOnClickListener(this);
            ((GroupViewHolder) holder).mDqdt.setOnClickListener(this);
//            ((GroupViewHolder) holder).mFfxdzz.setOnClickListener(this);
            ((GroupViewHolder) holder).mDzb.setOnClickListener(this);
            ((GroupViewHolder) holder).mFgh.setOnClickListener(this);
            ((GroupViewHolder) holder).mTqzb.setOnClickListener(this);


        } else if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).mRv.setOnClickListener(this);
        } else if (holder instanceof ListViewHolder) {
//            通过这一块判断，确保多条目展示不条目不紊乱
//            并且界面可以改变getItemCount的值
            DjdtBean.DataBean dataBean = null;
            if (position <= mDataBeen.size() + 3) {
                dataBean = mDataBeen.get(position - 3);
            }
            if (dataBean.getImage() != null) {
                if (!dataBean.getImage().equals("") && !dataBean.getImage().equals("null")) {
                    Glide.with(mContext).load(IP + dataBean.getImage()).into(((ListViewHolder) holder).mListIv);
                } else {
                    Glide.with(mContext).load(R.drawable.banner1).into(((ListViewHolder) holder).mListIv);

                }
            } else {
                Glide.with(mContext).load(R.drawable.banner1).into(((ListViewHolder) holder).mListIv);

            }

            String content = dataBean.getDescription();
            if (content != null) {
                if (content.length() >= 41) {
                    String subContent = content.substring(0, 41) + "...";
                    ((ListViewHolder) holder).mContent.setText(subContent);
                } else {
                    ((ListViewHolder) holder).mContent.setText(content);
                }
            } else {
                ((ListViewHolder) holder).mContent.setText("");
            }
            if (dataBean.getTitle() != null) {
                if (dataBean.getTitle().length() >= 11) {
                    ((ListViewHolder) holder).mName.setText(dataBean.getTitle().substring(0, 11) + "...");
                } else {
                    ((ListViewHolder) holder).mName.setText(dataBean.getTitle());
                }
            }
            ((ListViewHolder) holder).mTime.setText(dataBean.getCreateDate());
        }
        holder.itemView.setTag(position - 3);
    }

    @Override
    public int getItemCount() {
        return mDataBeen == null ? 0 : mDataBeen.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return XBANNER;
        } else if (position == 1) {
            return GROUP;
        } else if (position == 2) {
            return TITLE;
        } else {
            return LIST;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lxyz:
//                ToastUtils.showToast("文化文明");
                jump(ActivityCulturalcivilization.class,"文化文明");
                break;
            case R.id.tqgl:
//                ToastUtils.showToast("团青管理");
                jump(TqgzglActivity.class);
                break;
            case R.id.tzgg:
//                ToastUtils.showToast("通知公告");
                jump(ActivityNoticeBulletin.class);
                break;

            case R.id.dqdt:
//                ToastUtils.showToast("学习计划");
                jump(ActivitySubjectH5.class,"学习计划");
                break;
//            case R.id.ffxdzz:
//                ToastUtils.showToast("党员服务");
////                jump(ServiceOraginationActivity.class);
//                jump(PartyServiceActivity.class);
//
//                break;
            case R.id.dzb:
//                ToastUtils.showToast("党支部");
//                jump(IdeologicalBuildingActivity.class);
                jump(ActivityBranchManagement.class,"党支部");
                break;
            case R.id.fgh:
//                ToastUtils.showToast("分工会");
                jump(ActivityLabour.class,"分工会");
                break;
            case R.id.tqzb:
//                ToastUtils.showToast("团(青)支部");
                jump(ActivityGroupBranch.class,"团(青)支部");
                break;
            case R.id.ghgl:
//                ToastUtils.showToast("工会管理");
                jump(GhgzglActivity.class);
                break;
            case R.id.dqjx:
//                ToastUtils.showToast("党群绩效");
                jump(DzjxActivity.class);
                break;
            case R.id.dwgl:
                jump(DzzglActivity.class,"党建政工");
//                ToastUtils.showToast("党政建工");
                break;
            case R.id.jjgl:
//                ToastUtils.showToast("纪律检查");
                jump(JjjcglActivity.class,"纪律检查");
                break;
            case R.id.djdt_relative:
                jump(MoreDynamicActivity.class);
                break;
        }

    }

    private void jump(Class c) {
        Intent intent = new Intent(mContext, c);
        mContext.startActivity(intent);
    }
    private void jump(Class c,String tit) {
        Intent intent = new Intent(mContext, c);
        intent.putExtra("tit",tit);
        mContext.startActivity(intent);
    }

    public static class XbannerViewHolder extends RecyclerView.ViewHolder {
        private final XBanner mXBanner;

        XbannerViewHolder(View view) {
            super(view);
            mXBanner = (XBanner) view.findViewById(R.id.home_rv_item_xbanner);
        }
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {


        private final TextView mLxyz;
        private final TextView mDqjx;
        private final TextView mDwgl;
        private final TextView mJjgl;
        private final TextView mGhgl;
        private final TextView mTqgl;
        private final TextView mTzgg;
        private final TextView mDqdt;
//        private final TextView mFfxdzz;
        private final TextView mDzb;
        private final TextView mFgh;
        private final TextView mTqzb;

        GroupViewHolder(View view) {
            super(view);

            mLxyz = (TextView) view.findViewById(R.id.lxyz);
            mDqjx = (TextView) view.findViewById(R.id.dqjx);
            mDwgl = (TextView) view.findViewById(R.id.dwgl);
            mJjgl = (TextView) view.findViewById(R.id.jjgl);
            mGhgl = (TextView) view.findViewById(R.id.ghgl);
            mTqgl = (TextView) view.findViewById(R.id.tqgl);
            mTzgg = (TextView) view.findViewById(R.id.tzgg);
            mDqdt = (TextView) view.findViewById(R.id.dqdt);
//            mFfxdzz = (TextView) view.findViewById(R.id.ffxdzz);
            mDzb = (TextView) view.findViewById(R.id.dzb);
            mFgh = (TextView) view.findViewById(R.id.fgh);
            mTqzb = (TextView) view.findViewById(R.id.tqzb);

        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout mRv;

        TitleViewHolder(View view) {
            super(view);
            mRv = (RelativeLayout) view.findViewById(djdt_relative);


        }
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mListIv;
        private final TextView mName;
        private final TextView mTime;
        private final TextView mContent;

        ListViewHolder(View view) {
            super(view);
            mListIv = (ImageView) view.findViewById(R.id.home_rv_item_iv);
            mName = (TextView) view.findViewById(R.id.home_rv_item_name);
            mTime = (TextView) view.findViewById(R.id.home_rv_item_time);
            mContent = (TextView) view.findViewById(R.id.home_rv_item_content);

        }
    }

    //点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private void genPayReq() {
        req.appId = Constants.APP_ID;
        req.partnerId = Constants.MCH_ID;
        if (resultunifiedorder != null) {
            req.prepayId = resultunifiedorder.get("prepay_id");
            req.packageValue = "prepay_id=" + resultunifiedorder.get("prepay_id");
//            req.packageValue = "Sign=WXPay";
        } else {
            Toast.makeText(mContext, "prepayid为空", Toast.LENGTH_SHORT).show();
        }
        req.nonceStr = getNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());


        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);

        sb.append("sign\n" + req.sign + "\n\n");

//        textView.setText(sb.toString());

        Log.e("Simon", "----" + signParams.toString());

    }

    //生成随机号，防重发
    private String getNonceStr() {
        // TODO Auto-generated method stub
        Random random = new Random();

        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        this.sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        Log.e("Simon", "----" + appSign);
        return appSign;
    }

    /*
    * 调起微信支付
    */
    private void sendPayReq() {
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
        Log.i(">>>>>", req.partnerId);
    }

    private class PrePayIdAsyncTask extends AsyncTask<String, Void, HashMap<String, String>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(mContext, "提示", "正在提交订单");

        }

        @Override
        protected HashMap<String, String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url = String.format(params[0]);
            String entity = getProductArgs();
            Log.e("Simon", ">>>>" + entity);
            byte[] buf = Util.httpPost(url, entity);
            String content = new String(buf);
            Log.e("orion", "----" + content);
            HashMap<String, String> xml = decodeXml(content);

            return xml;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (dialog != null) {
                dialog.dismiss();
            }
            sb.append("prepay_id\n" + result.get("prepay_id") + "\n\n");
//            textView.setText(sb.toString());
            resultunifiedorder = result;
        }
    }

    private String getProductArgs() {
        // TODO Auto-generated method stub
        StringBuffer xml = new StringBuffer();
        try {
            String nonceStr = getNonceStr();
            xml.append("<xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));  //应用ID
            packageParams.add(new BasicNameValuePair("body", "APP pay test"));   //商品描述
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));//商户订单号
            packageParams.add(new BasicNameValuePair("notify_url", "https://www.baidu.com"));//写你们的回调地址
            packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo()));//商户订单号
            packageParams.add(new BasicNameValuePair("total_fee", "1"));  //总金额
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));   //交易类型
            packageParams.add(new BasicNameValuePair("spbill_create_ip", getIPAddress(mContext)));   //获取ip

            String sign = getPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlString = toXml(packageParams);
            return xmlString;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    //生成订单号,测试用，在客户端生成
    private String genOutTradNo() {
        Random random = new Random();
//      return "dasgfsdg1234"; //订单号写死的话只能支付一次，第二次不能生成订单
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    public HashMap<String, String> decodeXml(String content) {

        try {
            HashMap<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if ("xml".equals(nodeName) == false) {
                            //实例化student对象
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("Simon", "----" + e.toString());
        }
        return null;

    }

    /**
     * 生成签名
     */
    private String getPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);


        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("Simon", ">>>>" + packageSign);
        return packageSign;
    }

    /*
  * 转换成xml
  */
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");


            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");

        Log.e("Simon", ">>>>" + sb.toString());
        return sb.toString();
    }


    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
