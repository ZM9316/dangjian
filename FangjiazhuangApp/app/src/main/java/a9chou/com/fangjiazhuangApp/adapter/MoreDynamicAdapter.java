package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.DjdtBean;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

/**
 * date: 2017/12/8.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MoreDynamicAdapter extends BaseQuickAdapter<DjdtBean.DataBean, BaseViewHolder> {

    public MoreDynamicAdapter(@LayoutRes int layoutResId, @Nullable List<DjdtBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DjdtBean.DataBean item) {


        if (TextUtils.isEmpty(item.getImage())) {
            Glide.with(mContext).load(R.drawable.banner1).into((ImageView) helper.getView(R.id.home_rv_item_iv));

        } else {
            Glide.with(mContext).load(IP + item.getImage()).into((ImageView) helper.getView(R.id.home_rv_item_iv));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            if (item.getTitle().length() >= 11) {
                helper.setText(R.id.home_rv_item_name, item.getTitle().substring(0, 11) + "...");
            } else {
                helper.setText(R.id.home_rv_item_name, item.getTitle());
            }
        }
        if (!TextUtils.isEmpty(item.getDescription())) {
            if (item.getDescription().length() >= 41) {
                helper.setText(R.id.home_rv_item_content, item.getDescription().substring(0,41)+"...");

            }else {
                helper.setText(R.id.home_rv_item_content, item.getDescription());

            }
        }
        helper.setText(R.id.home_rv_item_time, item.getCreateDate());

    }
}