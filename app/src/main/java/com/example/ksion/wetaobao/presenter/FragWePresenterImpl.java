package com.example.ksion.wetaobao.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ksion.wetaobao.adapter.ActHomePageAdapter;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.contract.FragAskContract;
import com.example.ksion.wetaobao.contract.FragWeContract;
import com.example.ksion.wetaobao.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/7.
 */

public class FragWePresenterImpl  implements FragWeContract.FragWePresenter {
    private FragWeContract.FragWeView view;
    private List<Fragment> fragments;

    public FragWePresenterImpl(FragWeContract.FragWeView view) {
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


        ViewPager viewPager=view.getmFragWeVp();
        viewPager.setAdapter(new ActHomePageAdapter(view.getManager(),fragments));
    }
}
