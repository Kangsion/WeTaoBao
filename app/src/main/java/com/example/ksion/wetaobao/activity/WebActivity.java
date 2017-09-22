package com.example.ksion.wetaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;

/**
 * Created by Ksion on 2017/9/7.
 */

public class WebActivity extends BaseActivity {
    private ImageView mBtnBack;
    private ImageView mBtnGoTo;
    private TextView  mtitle;
    private WebView   mWebView;

    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        initView();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("URL");
        if(mWebView!=null) {
            mWebView.loadUrl(url);
            mWebView.getSettings().setJavaScriptEnabled(true);

            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    mWebView.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });

            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    mtitle.setText(title);
                }
            });
        }
    }

    private void initView() {
        mBtnBack= (ImageView) findViewById(R.id.act_webview_iv_back);
        mBtnGoTo= (ImageView) findViewById(R.id.act_webview_iv_goto);
        mtitle= (TextView) findViewById(R.id.act_webview_title);
        mWebView= (WebView) findViewById(R.id.id_act_webview);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBtnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.goForward();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mWebView!=null) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

                mWebView.goBack();
                return true;
            }
        }
             return false;

    }
}
