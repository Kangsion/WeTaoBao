package com.example.ksion.wetaobao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseFragment;


/**
 * Created by Ksion on 2017/9/4.
 */

public class NewsFragment extends BaseFragment {

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_home,null);
        return rootView;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }
}
