package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.List;
import java.util.Map;

import a9chou.com.fangjiazhuangApp.module.dao.PermissionBean;

import static android.content.Context.MODE_PRIVATE;

/**
 * date: 2017/12/18.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class SpPermissionUtil {
    private static final String TAG = "SpPermissionUtil";
    private static String sWp = "";
    private static String sWp1= "";
    private static String sWp2= "";
    private static String sMt= "";

    public static void sharedPermission(final Activity activity, Map<String, String> map) {

        ViseHttp.GET("mobile/sys/sysUser/getMenuList")
                .addParams(map).request(new ACallback<String>() {

            private int mZ;
            private String mIssEdit;
            private String mIssView;
            private int mY;
            private String mToEdit;
            private String mToDel;
            private String mToView;
            private int mX;
            private String mMetEdit;
            private String mMetDel;
            private String mMetView;
            private String mDk;
            private String mDxzh;
            private String mZbwyh;
            private String mZbdy;

            @Override
            public void onSuccess(String data) {
                Log.i("json", data);
                Gson g = new Gson();
                PermissionBean permissionBean = g.fromJson(data, PermissionBean.class);
                if(permissionBean.getType().equals("S")){
                    List<PermissionBean.DataBean> perDataBean = permissionBean.getData();
                    Log.d(TAG, "onSuccess:size==>s " + perDataBean.size());

                    for (int i = 0; i < perDataBean.size(); i++) {
                        Log.d(TAG, "onSuccess: =======>" + perDataBean.get(i).getPermission());
                        String permission = perDataBean.get(i).getPermission();
//                    会议
                        if ("wp:wpMeeting:add:zbdy".equals(permission)) {
                            mZbdy = "zbdy";
                        }
                        if ("wp:wpMeeting:add:zbwyh".equals(permission)) {
                            mZbwyh = "zbwyh";
                        }
                        if ("wp:wpMeeting:add:dxzh".equals(permission)) {
                            mDxzh = "dxzh";
                        }
                        if ("wp:wpMeeting:add:dk".equals(permission)) {
                            mDk = "dk";
                        }

//                    ******************************


                        if ("wp:wpMeeting:view".equals(permission)) {
                            mMetView = "view";
                        }


                        if ("wp:wpMeeting:del".equals(permission)) {
                            mMetDel = "del";
                        }
                        if ("wp:wpMeeting:edit".equals(permission)) {
                            mMetEdit = "edit";
                        }
                        if ("wp:wpMeeting:add".equals(permission)) {
                            mX = 1;
                        }
//活动
                        if ("wp:wpTogether:view".equals(permission)) {

                            mToView = "view";
                        }

                        if ("wp:wpTogether:del".equals(permission)) {

                            mToDel = "del";
                        }

                        if ("wp:wpTogether:edit".equals(permission)) {

                            mToEdit = "edit";
                        }

                        if ("wp:wpTogether:add".equals(perDataBean.get(i).getPermission())) {
                            mY = 1;
                        }
//书记在线
                        if ("wp:wpIssue:view".equals(permission)) {

                            mIssView = "view";
                        }
                        if ("wp:wpIssue:edit".equals(permission)) {
                            mIssEdit = "edit";
                        }
                        if ("wp:wpIssue:add".equals(permission)) {
                            mZ = 1;
                        }
                    }
                    SharedPreferences.Editor ed = activity.getSharedPreferences("per", MODE_PRIVATE).edit();
                    ed.putString("metView", mMetView);
                    ed.putString("metDel", mMetDel);
                    ed.putString("metEdit", mMetEdit);
//                **********************
                    ed.putString("zbdy", mZbdy);
                    ed.putString("zbwyh", mZbwyh);
                    ed.putString("dxzh", mDxzh);
                    ed.putString("dk", mDk);
                    ed.putInt("x", mX);
                    ed.putString("toView", mToView);
                    ed.putString("toDel", mToDel);
                    ed.putString("toEdit", mToEdit);
                    ed.putInt("y", mY);
                    ed.putString("issView", mIssView);
                    ed.putString("issEdit", mIssEdit);
                    ed.putInt("z", mZ);
                    ed.commit();
                }

            }

            @Override
            public void onFail(int errCode, String errMsg) {
            }
        });

    }

    //   获取人员三会一课权限
    public static String getThreeLssonsWpPermission(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", MODE_PRIVATE);
        String metDel = perShared.getString("metDel", "");
        String metEdit = perShared.getString("metEdit", "");
        String metView = perShared.getString("metView", "");

        Log.d(TAG, "initViews: " + metDel + metEdit + metView);

        if (!TextUtils.isEmpty(metDel)) {
            sWp += metDel + ",";
        }
        if (!TextUtils.isEmpty(metEdit)) {
            sWp += metEdit + ",";
        }
        if (!TextUtils.isEmpty(metView)) {
            sWp += metView + ",";
        }

        if (sWp != "") {
            sWp = sWp.substring(0, sWp.length() - 1);
        }
        return sWp;
    }

    public static String getThreeLssonsMtPermission(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", MODE_PRIVATE);
        String zbdy = perShared.getString("zbdy", "");
        String zbwyh = perShared.getString("zbwyh", "");
        String dxzh = perShared.getString("dxzh", "");
        String dk = perShared.getString("dk", "");
        if (!TextUtils.isEmpty(zbdy)) {
            sMt += zbdy + ",";
        }
        if (!TextUtils.isEmpty(zbwyh)) {
            sMt += zbwyh + ",";
        }
        if (!TextUtils.isEmpty(dxzh)) {
            sMt += dxzh + ",";
        }
        if (!TextUtils.isEmpty(dk)) {
            sMt += dk + ",";
        }
        if (sMt != "") {
            sMt = sMt.substring(0, sMt.length() - 1);
        }
        return sMt;
    }


    //            获取新增会议权限
    public static int returnX(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", MODE_PRIVATE);
        int x = perShared.getInt("x", 0);
        if (x == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String getActivesPermission(Activity activity) {

        SharedPreferences perShared = activity.getSharedPreferences("per", Context.MODE_PRIVATE);
        String toView = perShared.getString("toView", "");
        String toDel = perShared.getString("toDel", "");
        String toEdit = perShared.getString("toEdit", "");


        if (!TextUtils.isEmpty(toView)) {
            sWp1 += toView + ",";
        }
        if (!TextUtils.isEmpty(toDel)) {
            sWp1 += toDel + ",";
        }
        if (!TextUtils.isEmpty(toEdit)) {
            sWp1 += toEdit + ",";
        }
        if (sWp1 != null & sWp1.length() > 0) {
            sWp1 = sWp1.substring(0, sWp1.length() - 1);
        }
        Log.d(TAG, "getActivesPermission: "+sWp1);
        return sWp1;
    }

    public static int returnY(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", Context.MODE_PRIVATE);
        int y = perShared.getInt("y", 0);
        if (y == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //   获取书记在线权限
    public static String getSecretaryPermission(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", MODE_PRIVATE);
        String issView = perShared.getString("issView", "");
        String issEdit = perShared.getString("issEdit", "");

        if (!TextUtils.isEmpty(issEdit)) {
            sWp2 += issEdit + ",";
        }
        if (!TextUtils.isEmpty(issView)) {
            sWp2 += issView + ",";
        }

        if (sWp2 != null) {
            sWp2 = sWp2.substring(0, sWp2.length() - 1);
        }
        return sWp2;
    }

    public static int returnZ(Activity activity) {
        SharedPreferences perShared = activity.getSharedPreferences("per", MODE_PRIVATE);
        int z = perShared.getInt("z", 0);
        if (z == 1) {
            return 1;
        } else {
            return 0;
        }
    }

}
