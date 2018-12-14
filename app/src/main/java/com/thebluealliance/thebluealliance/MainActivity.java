package com.thebluealliance.thebluealliance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);

        webView.setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = webView.getSettings();
        CookieManager.getInstance().setAcceptCookie(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webView.loadUrl("https://pwa.thebluealliance.com");

    }
}
