package com.example.ksion.wetaobao.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.adapter.ActHomePageAdapter;
import com.example.ksion.wetaobao.contract.FragAskContract;
import com.example.ksion.wetaobao.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/6.
 */

public class FragAskPresenterImpl implements FragAskContract.IFragPresenter {
    private  FragAskContract.IFragAskView view;

    private List<Fragment> fragments;

    public FragAskPresenterImpl(FragAskContract.IFragAskView view) {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        fragments=new ArrayList<>();

        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());

        ViewPager viewPager=view.getmFragAskVp();
        viewPager.setAdapter(new ActHomePageAdapter(view.getManager(),fragments));

    }
}
