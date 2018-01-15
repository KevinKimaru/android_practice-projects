package com.example.android.androidyoutube;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class OurViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(String.valueOf(request));
//        return super.shouldOverrideUrlLoading(view, request);
        return true;
    }
}
