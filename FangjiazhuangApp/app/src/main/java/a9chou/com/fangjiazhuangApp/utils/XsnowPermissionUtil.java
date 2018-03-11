package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;

import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

/**
 * date: 2017/12/5.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class XsnowPermissionUtil {

    public static void getPermission(Activity a, String permission) {
        PermissionManager.instance().with(a).request(new OnPermissionCallback() {
            @Override
            public void onRequestAllow(String permissionName) {
            }

            @Override
            public void onRequestRefuse(String permissionName) {
            }

            @Override
            public void onRequestNoAsk(String permissionName) {
            }
        }, permission);

    }

}
