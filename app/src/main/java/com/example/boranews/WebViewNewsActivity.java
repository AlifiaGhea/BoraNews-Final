package com.example.boranews;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boranews.R;

public class WebViewNewsActivity extends AppCompatActivity {

    WebView newsWeb;
    ProgressBar progressBar;
    String url = "https://www.google.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        url =getIntent().getStringExtra("url");

        newsWeb = (WebView) findViewById(R.id.read_news);
        newsWeb.getSettings().setJavaScriptEnabled(true);
        newsWeb.loadUrl(url);
        progressBar.setMax(0);

        newsWeb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                progressBar.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                //mProgressBar.setProgress(0);
                url = urlStart;
                invalidateOptionsMenu();
            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                progressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }
        });

    }


}
