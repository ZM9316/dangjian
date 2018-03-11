package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import a9chou.com.fangjiazhuangApp.R;

/**
 * date: 2017/11/13.
 * author: 王艺凯 (lenovo )
 * function: popupwindow 工具类
 */
public class PopupWindowUtil {
    private static PopupWindow sPopupWindow;
    private static View sView;

    /**
     * 在底部，弹出动画为渐变
     *
     * @param activity
     * @param layout   xml  layout
     */
    public static void initBottomPopup(final Activity activity, int layout) {
        sView = LayoutInflater.from(activity).inflate(layout, null);

        sPopupWindow = new PopupWindow(sView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        sPopupWindow.setOutsideTouchable(true);
        sPopupWindow.setFocusable(true);
        View parent = LayoutInflater.from(activity).inflate(R.layout.activity_main, null);
        sPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.5f;
        activity.getWindow().setAttributes(params);
        sPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                activity.getWindow().setAttributes(params);
            }
        });

    }

    /**
     * 获取内部控件
     *
     * @param id 控件id
     * @return
     */
    public static View getView(int id) {
        View v = sView.findViewById(id);
        return v;
    }

    /**
     * 取消
     */
    public static void popupDismiss() {
        sPopupWindow.dismiss();
    }



}
