package com.example.inshortsapp.app;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.inshortsapp.R;

public class WebBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.webview_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageButton back_button = (ImageButton) view.findViewById(R.id.action_bar_back);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.i("custom", getIntent().getStringExtra("url"));

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(getIntent().getStringExtra("url"));

        // Code for opening URLs in the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}
