package com.myappcompany.imran.webviews;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView); //got access to web view
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient()); //required to display in the view else it will go to default browser
        webView.loadUrl("http://www.google.com");
        //webView.loadData("<html><body><h1> Hellow World</h1><p> This is my cool website</p></body></html>", "text/html", "UTF-8");
    }
}