package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class MenberAdapter extends BaseQuickAdapter<BbsjBean, BaseViewHolder> {

    public MenberAdapter(@LayoutRes int layoutResId, @Nullable List<BbsjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,BbsjBean item) {
        helper.setText(R.id.menber_list_cb, item.getPersonName());
        helper.setChecked(R.id.menber_list_cb,item.getChecked());
        helper.addOnClickListener(R.id.menber_list_cb);
    }

}
