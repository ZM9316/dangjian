package a9chou.com.fangjiazhuangApp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import a9chou.com.fangjiazhuangApp.MyApplication;
import butterknife.ButterKnife;


/**
 * date: 2017/9/.
 * author: 王艺凯 (lenovo )
 * function:BaseFragment基类
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment {
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            setTextType();
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
        }
    }

    /**
     * 获取 ApplicationComponent
     *
     * @return ApplicationComponent
     */
    protected MyApplication getAppComponent() {
        return MyApplication.getAppComponent();
//        return ((MvpApplication) getActivity().getApplication().get).getAppComponent();
    }

    /**
     * 初始化 Toolbar
     * <p>
     * //     * @param back
     * //     * @param title
     * //     * @param content
     */
    protected void initToolBar(int leftView, String title, int rightView1, int rightView2) {
        ((BaseActivity) getActivity()).initToolBar(leftView, title, rightView1, rightView2);
    }


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    protected void setTextType() {
    }
}
