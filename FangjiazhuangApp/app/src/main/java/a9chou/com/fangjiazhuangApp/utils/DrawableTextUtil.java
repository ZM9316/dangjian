package a9chou.com.fangjiazhuangApp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * date: 2017/10/9.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class DrawableTextUtil {

    public static void setTextCompoundDrawables(Context c, int i, TextView t, int distance) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, drawable, null, null);
        t.setCompoundDrawablePadding(distance);
    }

    public static void setTextCompoundDrawablesLeft(Context c, int i, TextView t, int distance) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(drawable, null, null, null);
        t.setCompoundDrawablePadding(distance);
    }

    public static void setTextCompoundDrawablesRight(Context c, int i, TextView t, int distance) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, null, drawable, null);
        t.setCompoundDrawablePadding(distance);
    }

    public static void setTextCompoundDrawablesDown(Context c, int i, TextView t, int distance) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, null, null, drawable);
        t.setCompoundDrawablePadding(distance);
    }


    public static void setTextCompoundDrawables(Context c, int i, TextView t, int distance, String str) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, drawable, null, null);
        t.setCompoundDrawablePadding(distance);
        t.setText(str);
    }

    public static void setTextCompoundDrawablesLeft(Context c, int i, TextView t, int distance, String str) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(drawable, null, null, null);
        t.setCompoundDrawablePadding(distance);
        t.setText(str);
    }

    public static void setTextCompoundDrawablesRight(Context c, int i, TextView t, int distance, String str) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, null, drawable, null);
        t.setCompoundDrawablePadding(distance);
        t.setText(str);
    }

    public static void setTextCompoundDrawablesDown(Context c, int i, TextView t, int distance, String str) {
        Drawable drawable = c.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
        t.setCompoundDrawables(null, null, null, drawable);
        t.setCompoundDrawablePadding(distance);
        t.setText(str);
    }


}
