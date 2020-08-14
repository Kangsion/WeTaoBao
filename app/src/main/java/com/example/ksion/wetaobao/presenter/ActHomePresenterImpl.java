package com.example.ksion.wetaobao.presenter;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.ksion.wetaobao.activity.HomeActivity;
import com.example.ksion.wetaobao.adapter.ActHomePageAdapter;
import com.example.ksion.wetaobao.contract.ActHomeContract;
import com.example.ksion.wetaobao.contract.HomeContract;
import com.example.ksion.wetaobao.fragment.AskFragment;
import com.example.ksion.wetaobao.fragment.HomeFragment;
import com.example.ksion.wetaobao.fragment.PersonalFragment;
import com.example.ksion.wetaobao.fragment.ShopCarFragment;
import com.example.ksion.wetaobao.fragment.WeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/4.
 */

public class ActHomePresenterImpl  implements ActHomeContract.ActHomePresenter {
     private ActHomeContract.ActHomeView view;
     private ActHomePageAdapter adapter;
     private List<Fragment> fragments;

    public ActHomePresenterImpl(ActHomeContract.ActHomeView view) {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        ViewPager viewPager=view.getmActHomeVpContent();
        fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new WeFragment());
        fragments.add(new AskFragment());
        fragments.add(new ShopCarFragment());
        fragments.add(new PersonalFragment());
        adapter=new ActHomePageAdapter(view.getManager(),fragments);
        viewPager.setAdapter(adapter);

        //设置viewPager为不可滑动状态,存在主Fragment无法切换左右切换
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;  //修改为true
            }

        });
    }
}
