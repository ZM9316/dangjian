package a9chou.com.fangjiazhuangApp.module.dao;

import android.webkit.JavascriptInterface;

/**
 * Created by zhang on 2018/1/19.
 */

public class getData {
    private int all;
    private int i;
    private int all_i;

    public getData(int all,int i,int all_i){
        this.all=all;
        this.i=i;
        this.all_i=all_i;
    }

    @JavascriptInterface
    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
    @JavascriptInterface
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    @JavascriptInterface
    public int getAll_i() {
        return all_i;
    }

    public void setAll_i(int all_i) {
        this.all_i = all_i;
    }
}
