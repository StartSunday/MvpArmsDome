package com.sun.component.commonres.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.sun.component.commonres.R;


/**
 * Created by ChenYuYun on 2018/8/21.
 * 含有加载进度的WebView
 */
public class LoadingWebView extends SwipeRefreshLayout {
    private BridgeWebView mWebView;

    public LoadingWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_webview, this, true);
//        BaseHelper.initSwipeRefreshLayoutColor(this);
        mWebView = findViewById(R.id.bridge_webView);
        init(context);
        addLister();
    }

    public void loadUrl(final String url) {
        post(new Runnable() {
                 @Override
                 public void run() {
                     mWebView.loadUrl(url);
                     setRefreshing(true);
                 }
             });

    }

    public void loadBodyHtml(final String bodyHtml) {
        post(new Runnable() {
                 @Override
                 public void run() {

                mWebView.loadDataWithBaseURL(null, getHtmlData(bodyHtml), "text/html", "utf-8", null);
            setRefreshing(false);
                 }
             });
    }

    private void addLister() {
        setOnRefreshListener(new OnRefreshListener() {
                                 @Override
                                 public void onRefresh() {
                                     mWebView.reload();
                                 }
                             }

        );
    }

    private void init(Context context) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCacheDir = this.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
                Log.e("超链接", url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setRefreshing(false);
                        }
                    });

//                    post(() -> setRefreshing(false));
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.setBackgroundColor(0);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //滚动条样式
        mWebView.setVerticalScrollBarEnabled(true); //显示滚动条
    }

    /**
     * 加载html标签
     *
     * @param bodyHTML
     * @return
     */
    private String getHtmlData(String bodyHTML) {
//        String content = "<style>\n" +
//                "\t\tp,span,b{\n" +
//                "\t\t\tfont: normal 18px \"Microsoft YaHei\" !important;\n" +
//                "\t\t\tline-height: 30px !important;\n" +
////                "\t\t\tfont-size:30px !important;\n" +
////                "\t\t\tcolor: #f00 !important;\n" +
//                "\t\t}\n" +
//                "\t\th1,h2,h3,h4,h5,h6{\n" +
//                "\t\t\tfont:normal 30px \"Microsoft YaHei\" !important;\n" +
//                "\t\t\tline-height: 46px !important;\n" +
//                "\t\t}\n" +
//                "\n" +
//                "\t</style>" + bodyHTML;
        return bodyHTML;
        //return "<html>" + content + "<body style=\"font-size:30px;color: #fff;line-height: 48px;font-family: Microsoft YaHei\">" + bodyHTML + "</body></html>";

//        String head = "<head>" +
//                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> " +
//                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
//                "<script type=\"text/javascript\" src=\"file:///android_asset/jquery.min.js\"></script> " +
//                "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/mathquill/mathquill.css\">" +
//                "<script type=\"text/javascript\" src=\"file:///android_asset/mathquill/mathquill.min.js\"></script>" +
////                "<style>\n" +
////                "\t\tp,span,b{\n" +
////                "\t\t\tfont: normal 30px \"Microsoft YaHei\" !important;\n" +
////                "\t\t\tline-height: 30px !important;\n" +
////                "\t\t\tcolor: #fff !important;\n" +
////                "\t\t}\n" +
////                "\t\th1,h2,h3,h4,h5,h6{\n" +
////                "\t\t\tfont:normal 30px \"Microsoft YaHei\" !important;\n" +
////                "\t\t\tline-height: 46px !important;\n" +
////                "\t\t}\n" + "\n" + "\t</style>"+
//                "</head>";
//
//
//        return "<html>" + head + "<body style=\"font-size:30px;color: #fff;line-height: 48px;font-family: Microsoft YaHei\">" + bodyHTML + "</body></html>";
    }
}
