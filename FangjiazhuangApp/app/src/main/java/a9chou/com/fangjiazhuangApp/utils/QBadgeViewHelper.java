package a9chou.com.fangjiazhuangApp.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.vise.xsnow.http.ViseHttp.getContext;

/**
 * date: 2017/9/21.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class QBadgeViewHelper {

    public static void setQBadgeByImage(QBadgeView qBadgeView,boolean isStateChanged, ImageView imageView, int number) {

        //        图标数字
        qBadgeView.bindTarget(imageView).setBadgeNumber(number).setBadgeGravity(Gravity.TOP | Gravity.END);

        if (isStateChanged == true) {
            qBadgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                }

            });
        }
    }
    public static void hideQBadgeByImage(boolean isStateChanged, ImageView imageView, int number) {
        QBadgeView qBadgeView = new QBadgeView(getContext());

        //        图标数字
        qBadgeView.bindTarget(imageView).setBadgeNumber(number).setBadgeGravity(Gravity.TOP | Gravity.END);

        if (isStateChanged == true) {
            qBadgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                }

            });
        }
    }


}
