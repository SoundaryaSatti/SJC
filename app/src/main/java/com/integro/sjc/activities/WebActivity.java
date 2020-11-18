package com.integro.sjc.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.integro.sjc.R;
import com.victor.loading.newton.NewtonCradleLoading;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    NewtonCradleLoading newtonCradleLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide();

        String url = (String) getIntent().getSerializableExtra("TAG");
        newtonCradleLoading = findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.setLoadingColor(Color.parseColor("#378dbe"));
        newtonCradleLoading.start();
        //getSupportActionBar().hide();
       /* if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        webView = findViewById(R.id.webview);
        webView.loadUrl(url);
        openWebView();
    }

    public void openWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                newtonCradleLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                newtonCradleLoading.setVisibility(View.GONE);
            }
        });
    }

    //goto previous page when pressing back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown( keyCode, event );
    }


}
