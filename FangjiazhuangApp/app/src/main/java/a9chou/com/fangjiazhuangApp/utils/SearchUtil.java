package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * date: 2017/10/10.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class SearchUtil {

    public static void showSearch(EditText ed,Activity a,RelativeLayout r, LinearLayout l) {
//                标题隐藏
       r.setVisibility(View.GONE);
//                搜索框显示
        l.setVisibility(View.VISIBLE);
//                弹出软键盘
        InputMethodManager inputMethodManager = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

       ed.setFocusable(true);
       ed.setFocusableInTouchMode(true);
       ed.requestFocus();

    }



    public static void abolishSearch(Activity a, EditText e, RelativeLayout r, LinearLayout l) {
        //取消键
        e.setText("");
//                标题显示
        r.setVisibility(View.VISIBLE);
//                搜索框隐藏
        l.setVisibility(View.GONE);
//   隐藏软键盘
        InputMethodManager im = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(a.getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
