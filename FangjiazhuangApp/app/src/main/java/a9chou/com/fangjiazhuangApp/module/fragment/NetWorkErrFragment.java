package a9chou.com.fangjiazhuangApp.module.fragment;

import android.widget.Button;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import butterknife.BindView;
import butterknife.Unbinder;

/**
 * date: 2017/12/14.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class NetWorkErrFragment extends BaseFragment {
    @BindView(R.id.jiazai)
    Button mJiazai;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_net_work_err;
    }

    @Override
    protected void initViews() {
            
    }
}
