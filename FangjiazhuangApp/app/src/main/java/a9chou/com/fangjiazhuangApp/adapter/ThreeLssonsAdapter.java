package a9chou.com.fangjiazhuangApp.adapter;

/**
 * Created by zm9316 on 2017/12/22.
 */

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;

public class ThreeLssonsAdapter extends BaseQuickAdapter<BbsjBean, BaseViewHolder> {
    public ThreeLssonsAdapter(@LayoutRes int layoutResId, @Nullable List<BbsjBean> data) {//
        super(layoutResId, data);//
    }

    @Override
    protected void convert(BaseViewHolder helper, BbsjBean item) {
        helper.setText(R.id.hymc, item.getTitle());
        helper.setText(R.id.hytime, item.getTime());
        helper.setText(R.id.dy, item.getContent());
        if("0".equals(item.getBtnvalue())){
            helper.setText(R.id.fbbtn, "已结束");
        }else{
            if("党课".equals(item.getBiaoshi())||"集体学习".equals(item.getBiaoshi())){
                helper.setText(R.id.fbbtn, "学习中");
            }else{
                helper.setText(R.id.fbbtn, "会议中");
            }

        }

        helper.addOnClickListener(R.id.hymc).addOnClickListener(R.id.fbbtn).addOnClickListener(R.id.member_btn);
    }
}