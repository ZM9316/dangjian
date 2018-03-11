package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.Message;

/**
 * Created by zm9316 on 2017/12/29.
 */

public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {

    public MessageAdapter(@LayoutRes int layoutResId, @Nullable List<Message> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        helper.setText(R.id.message_name,item.getNoticeName());
        helper.addOnClickListener(R.id.message_name);
    }

}
