package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.module.dao.BbsjBean;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class ShowInfoImAdapter  extends BaseQuickAdapter<BbsjBean, BaseViewHolder> {

    public ShowInfoImAdapter(@LayoutRes int layoutResId, @Nullable List<BbsjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BbsjBean item) {
//        helper.setImageResource(R.id.showinfo_im, "测试");
    }

}
