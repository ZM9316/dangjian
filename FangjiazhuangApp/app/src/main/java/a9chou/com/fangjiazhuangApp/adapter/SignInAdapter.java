package a9chou.com.fangjiazhuangApp.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.dao.SignInBean;

/**
 * date: 2017/10/12.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class SignInAdapter extends BaseQuickAdapter<SignInBean.DataBean.PersonListBean, BaseViewHolder> {

    private Button mSignIn;

    public SignInAdapter(@Nullable List<SignInBean.DataBean.PersonListBean> data) {
        super(R.layout.sign_in_item_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignInBean.DataBean.PersonListBean item) {
        Log.d(TAG, "convert: " + item.getTelephone());
        mSignIn = helper.getView(R.id.sign_in_sign_btn);
        String comtime = "0002-11-30 00:00:00";
        if (item.getSignature() != "") {
            if (item.getSignature().equals(comtime)) {
                mSignIn.setBackgroundResource(R.drawable.sign_in_btn);
                mSignIn.setText("签 到");
            } else {
                mSignIn.setBackgroundResource(R.drawable.yi_sign_in_btn);
                mSignIn.setText("已 到");
            }
        }
        helper.setText(R.id.sign_in_item_list_name, item.getUserName());
        ImageView icon = helper.getView(R.id.sign_in_item_list_icon);
//        Glide.with(mContext).load(IP + item.getPhoto()).transform(new GlideCircleTransform(getContext())).into(icon);
        helper.addOnClickListener(R.id.sign_in_sign_btn).addOnClickListener(R.id.sign_in_tel_btn);
    }
}
