package a9chou.com.fangjiazhuangApp.base;


/**
 * date: 2017/9/.
 * author: 王艺凯 (lenovo )
 * function:IBasePresenter
 */
public interface IBasePresenter {
    /**
     * 获取商品数据
     */
    void getData(boolean isRefresh);

    /**
     * 加载更多数据
     */
    void getMoreData();
}
