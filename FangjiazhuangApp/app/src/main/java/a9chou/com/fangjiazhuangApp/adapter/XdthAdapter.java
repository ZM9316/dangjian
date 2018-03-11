package a9chou.com.fangjiazhuangApp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.XdBean;

import static android.media.CamcorderProfile.get;

/**
 * date: 2017/11/14.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class XdthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "XdthAdapter";
    private final int Item2 = 1;
    private final int Item3 = 2;
    private final int Item4 = 3;
    private Context mContext;
    private List<XdBean.DataBean> mDataBeen;
    private final String mUsername;
    private final String mUserId;
    private final String mToken;
    private OnWzClickListener mOnWzClickListener = null;
    private OnRecordClickListener mOnRecordClickListener = null;
    private OnVideoClickListener mOnVideoClickListener = null;

    public XdthAdapter(Context context, List<XdBean.DataBean> mBean) {
        mDataBeen = mBean;
        mContext = context;
        SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
        mUsername = sp.getString("username", "");
        mUserId = sp.getString("userId", "");
        mToken = sp.getString("token", "");


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item2) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.xd_item2, parent, false);
            AutoUtils.autoSize(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnWzClickListener != null) {
                        //注意这里使用getTag方法获取position
                        mOnWzClickListener.onItemClick(v, (int) v.getTag());
                        Log.d(TAG, "onClick: +tag" + v.getTag());
                    }
                }
            });
            return new Item2ViewHolder(v);
        } else if (viewType == Item3) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.xd_item3, parent, false);
            AutoUtils.autoSize(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnRecordClickListener != null) {
                        //注意这里使用getTag方法获取position
                        mOnRecordClickListener.onItemClick(v, (int) v.getTag());
                        Log.d(TAG, "onClick: +tag" + v.getTag());
                    }
                }
            });
            return new Item3ViewHolder(v);
        } else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.xd_item4, parent, false);
            AutoUtils.autoSize(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnVideoClickListener != null) {
                        //注意这里使用getTag方法获取position
                        mOnVideoClickListener.onItemClick(v, (int) v.getTag());
                        Log.d(TAG, "onClick: +tag" + v.getTag());
                    }
                }
            });
            return new Item4ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mItem2Title.setText(mDataBeen.get(position).getContentTitle());
            ((Item2ViewHolder) holder).mItem2Content.setText(mDataBeen.get(position).getContent());
            ((Item2ViewHolder) holder).mItem2Time.setText(mDataBeen.get(position).getCreateDate());
            ((Item2ViewHolder) holder).mItem2Sc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", mDataBeen.get(position).getId());
                    map.put("userName", mUsername);
                    map.put("token", mToken);
                    map.put("userId", mUserId);

                    ViseHttp.
                            GET("mobile/wp/wpExperience/delete").
                            addParams(map).
                            request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.d(TAG, "onSuccess: " + data);
                                    mDataBeen.remove(position);
                                    notifyItemRemoved(position);
                                    if (position != mDataBeen.size()) {
                                        notifyItemRangeChanged(position, mDataBeen.size() - position);
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d(TAG, "onFail: " + errMsg);
                                }
                            });


                }
            });
            //将position保存在itemView的Tag中，以便点击时进行获取
        } else if (holder instanceof Item3ViewHolder) {

            ((Item3ViewHolder) holder).mItem3Title.setText(mDataBeen.get(position).getRecordTitle());
            ((Item3ViewHolder) holder).mItem3Time.setText(mDataBeen.get(position).getCreateDate());
            ((Item3ViewHolder) holder).mItem3Sc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", mDataBeen.get(position).getId());
                    map.put("userName", mUsername);
                    map.put("token", mToken);
                    map.put("userId", mUserId);

                    ViseHttp.
                            GET("mobile/wp/wpExperience/delete").
                            addParams(map).
                            request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.d(TAG, "onSuccess: " + data);
                                    mDataBeen.remove(position);
                                    notifyItemRemoved(position);
                                    if (position != mDataBeen.size()) {
                                        notifyItemRangeChanged(position, mDataBeen.size() - position);
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d(TAG, "onFail: " + errMsg);
                                }
                            });


                }
            });
        } else if (holder instanceof Item4ViewHolder) {
            ((Item4ViewHolder) holder).mItem4Title.setText(mDataBeen.get(position).getVideoTitle());
            ((Item4ViewHolder) holder).mItem4Time.setText(mDataBeen.get(position).getCreateDate());
            ((Item4ViewHolder) holder).mItem4Sc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", mDataBeen.get(position).getId());
                    map.put("userName", mUsername);
                    map.put("token", mToken);
                    map.put("userId", mUserId);

                    ViseHttp.
                            GET("mobile/wp/wpExperience/delete").
                            addParams(map).
                            request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.d(TAG, "onSuccess: " + data);
                                    mDataBeen.remove(position);
                                    notifyItemRemoved(position);
                                    if (position != mDataBeen.size()) {
                                        notifyItemRangeChanged(position, mDataBeen.size() - position);
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.d(TAG, "onFail: " + errMsg);
                                }
                            });
                }
            });
        }

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mDataBeen == null ? 0 : mDataBeen.size();
    }

    @Override
    public int getItemViewType(int position) {

        String content = mDataBeen.get(position).getContent();
        String contentTitle = mDataBeen.get(position).getContentTitle();
        String recordTitle = mDataBeen.get(position).getRecordTitle();
        String record = mDataBeen.get(position).getRecord();
        String video = mDataBeen.get(position).getVideo();
        String videoTitle = mDataBeen.get(position).getVideoTitle();

        Log.d(TAG, "getItemViewType: 1" + content + "  " + contentTitle + "  " + position);
        Log.d(TAG, "getItemViewType: 2" + record + "  " + recordTitle + "  " + position);
        Log.d(TAG, "getItemViewType: 3" + video + "  " + videoTitle + "  " + position);
        if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(contentTitle) && TextUtils.isEmpty(video) && TextUtils.isEmpty(videoTitle) && TextUtils.isEmpty(record) && TextUtils.isEmpty(recordTitle)) {
            return Item2;
        } else if (!TextUtils.isEmpty(record) && !TextUtils.isEmpty(recordTitle) && TextUtils.isEmpty(video) && TextUtils.isEmpty(videoTitle) && TextUtils.isEmpty(content) && TextUtils.isEmpty(contentTitle)) {
            return Item3;
        } else if (TextUtils.isEmpty(content) && TextUtils.isEmpty(contentTitle) && TextUtils.isEmpty(record) && TextUtils.isEmpty(recordTitle) && !TextUtils.isEmpty(video) && !TextUtils.isEmpty(videoTitle)) {
            return Item4;
        }
        return -1;
    }


    static class Item2ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mItem2Time;
        private final TextView mItem2Title;
        private final TextView mItem2Content;
        private final TextView mItem2Sc;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mItem2Time = (TextView) itemView.findViewById(R.id.xd_item2_time);
            mItem2Title = (TextView) itemView.findViewById(R.id.xd_item2_title);
            mItem2Content = (TextView) itemView.findViewById(R.id.xd_item2_content);
            mItem2Sc = (TextView) itemView.findViewById(R.id.xd_item2_sc);
        }
    }

    static class Item3ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mItem3Time;
        private final TextView mItem3Ly;
        private final TextView mItem3Title;
        private final TextView mItem3Sc;

        public Item3ViewHolder(View itemView) {
            super(itemView);
            mItem3Time = (TextView) itemView.findViewById(R.id.xd_item3_time);
            mItem3Ly = (TextView) itemView.findViewById(R.id.xd_item3_ly);
            mItem3Title = (TextView) itemView.findViewById(R.id.xd_item3_title);
            mItem3Sc = (TextView) itemView.findViewById(R.id.xd_item3_sc);
        }
    }

    static class Item4ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mItem4Title;
        private final TextView mItem4Time;
        private final TextView mItem4Sc;

        public Item4ViewHolder(View itemView) {
            super(itemView);
            mItem4Title = (TextView) itemView.findViewById(R.id.xd_item4_title);
            mItem4Time = (TextView) itemView.findViewById(R.id.xd_item4_time);
            mItem4Sc = (TextView) itemView.findViewById(R.id.xd_item4_sc);
        }
    }

    //点击事件
    public static interface OnWzClickListener {
        void onItemClick(View view, int position);
    }

    //点击事件
    public static interface OnRecordClickListener {
        void onItemClick(View view, int position);
    }

    //点击事件
    public static interface OnVideoClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnWzClickListener wzClickListener, OnRecordClickListener recordClickListener, OnVideoClickListener videoClickListener) {
        this.mOnWzClickListener = wzClickListener;
        this.mOnRecordClickListener = recordClickListener;
        this.mOnVideoClickListener = videoClickListener;
    }
}
