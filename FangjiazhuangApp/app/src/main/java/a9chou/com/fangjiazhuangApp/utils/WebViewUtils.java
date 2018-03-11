package a9chou.com.fangjiazhuangApp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.tu.loadingdialog.LoadingDailog;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * date: 2017/9/27.
 * author: 王艺凯 (lenovo )
 * function: webview工具类
 */

public class WebViewUtils {
    //     设置android 支持 js
    public static void setWebView(final LoadingDailog dialog, WebView webView, String url, Context context, String tag) {
        webView.setVerticalScrollbarOverlay(true); //指定的垂直滚动条有叠加样式
        //        设置支持js
        WebSettings webSettings = webView.getSettings();

//设置加载进来的页面自适应手机屏幕
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//设定支持viewport

        webSettings.setLoadWithOverviewMode(true);

        webSettings.setBuiltInZoomControls(true);

        webSettings.setSupportZoom(true);//设定支持缩放

        // 清除cookie即可彻底清除缓存
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().removeSessionCookie();
        webView.clearCache(true);
        webView.clearHistory();
//        设置在app中打开h5界面
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                view.loadUrl(url.toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });

        webView.addJavascriptInterface(context, tag);
        webView.loadUrl(url);
    }


    public static void setWebView(final LoadingDailog dialog, final WebView webView, String url, Context context, String tag, final String wayName, final String someThing) {
//            dialog.show();
        //        设置支持js
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 清除cookie即可彻底清除缓存

        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().removeSessionCookie();
        webView.clearCache(true);
        webView.clearHistory();
//        设置在app中打开h5界面
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                view.loadUrl(url.toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
                webView.loadUrl("javascript:" + wayName + "('" + someThing + "')");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        webView.addJavascriptInterface(context, tag);
        webView.loadUrl(url);
    }

    //java代码调用js方法，并传递一个参数
    public static void setJava(WebView webView, String wayName, String someThing) {
        webView.loadUrl("javascript:" + wayName + "('" + someThing + "')");
        Log.d(TAG, "setJava: " + "javascript:" + wayName + "('" + someThing + "')");
    }

    //java代码调用js方法，并传递一个参数
    public static void setJava(WebView webView, String wayName) {
        webView.loadUrl("javascript:" + wayName);
        Log.d(TAG, "setJava: " + "javascript:" + wayName);
    }
}
