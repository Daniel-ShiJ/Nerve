package com.kingnet.nerve;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

import com.kingnet.nerve.databinding.TeMainBinding;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/10 17:19
 * Description:
 */
public class TestActivity extends AppCompatActivity {

    private TeMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = TeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .build();

        binding.webView.setWebViewClient(new WebViewClientCompat() {
            @Override
            @RequiresApi(21)
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }

            @Override
            @SuppressWarnings("deprecation") // for API < 21
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return assetLoader.shouldInterceptRequest(Uri.parse(url));
            }
        });

        binding.webView.setWebChromeClient(new WebChromeClient(){
            @Override public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });



        WebSettings webViewSettings = binding.webView.getSettings();
        // Setting this off for security. Off by default for SDK versions >= 16.
        webViewSettings.setAllowFileAccessFromFileURLs(false);
        // Off by default, deprecated for SDK versions >= 30.
        webViewSettings.setAllowUniversalAccessFromFileURLs(false);
        // Keeping these off is less critical but still a good idea, especially if your app is not
        // using file:// or content:// URLs.
        webViewSettings.setAllowFileAccess(false);
        webViewSettings.setAllowContentAccess(false);

        webViewSettings.setSupportZoom(false);//不支持缩放
        webViewSettings.setJavaScriptEnabled(true);//设置WebView允许执行JavaScript脚本
        // 自适应屏幕大小
        webViewSettings.setUseWideViewPort(true);//当前页面包含viewport属性标签，在标签中指定宽度值生效
        webViewSettings.setLoadWithOverviewMode(true);//设置WebView使用预览模式加载界面
        String cacheDirPath = getFilesDir().getAbsolutePath() + "cache/";
        webViewSettings.setAppCachePath(cacheDirPath);
        webViewSettings.setAppCacheEnabled(true);//设置Application缓存API开启
        webViewSettings.setDomStorageEnabled(true);//设置开启DOM存储API权限,WebView能够使用DOM storage API
        webViewSettings.setAllowFileAccess(true);//设置在WebView内部允许访问文件
        webViewSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        webViewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置缓存加载模式
        webViewSettings.setTextZoom(100);//设置WebView中加载页面字体变焦百分比，默认100.
        webViewSettings.setSupportMultipleWindows(true);//设置webview支持多屏窗口
        //webView支持https和http混合加载模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webViewSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        binding.webView.loadUrl("http://172.20.6.89:8000/#/?channel_id=2&bulletin_category_id=3");



    }
}
