package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.MenberMessage;

/**
 * Created by zm9316 on 2017/12/29.
 */

public class MenberMessageAdapter extends BaseQuickAdapter<MenberMessage, BaseViewHolder> {

    public MenberMessageAdapter(@LayoutRes int layoutResId, @Nullable List<MenberMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenberMessage item) {
        helper.setText(R.id.message_name,item.getMenberName());
//        helper.addOnClickListener(R.id.message_name);
    }
}
