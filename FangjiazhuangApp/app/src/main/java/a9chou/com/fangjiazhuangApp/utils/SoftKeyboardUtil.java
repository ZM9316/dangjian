package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * date: 2017/9/29.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class SoftKeyboardUtil {

    public static void setSoftKeyboard(final Context context, final EditText edit, final WebView web, final String way) {
        edit.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //是否是回车键
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏键盘
                    ((InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    WebViewUtils.setJava(web, way, edit.getText().toString());
                }
                return false;
            }
        });
    }
}
