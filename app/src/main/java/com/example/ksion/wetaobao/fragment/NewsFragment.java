package com.example.ksion.wetaobao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.config.Contracts;


/**
 * Created by Ksion on 2017/9/4.
 */

public class NewsFragment extends BaseFragment {

    private WebView webView;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_webview,null);
         webView= (WebView) rootView.findViewById(R.id.frag_webview);
        return rootView;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        webView.loadUrl(Contracts.TIANMAO_GUOJI_URL);
    }


}
