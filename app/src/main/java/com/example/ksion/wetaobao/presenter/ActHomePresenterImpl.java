package com.example.ksion.wetaobao.presenter;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.ksion.wetaobao.adapter.ActHomePageAdapter;
import com.example.ksion.wetaobao.contract.IhomeContract;
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

public class ActHomePresenterImpl  implements IhomeContract.IHomePresenter {
     private IhomeContract.IHomeView iHomeView;
     private ActHomePageAdapter adapter;
     private List<Fragment> fragments;

    public ActHomePresenterImpl(IhomeContract.IHomeView iHomeView) {
        this.iHomeView=iHomeView;
        iHomeView.setPresenter(this);
    }

    @Override
    public void initData() {
        ViewPager viewPager=iHomeView.getmActHomeVpContent();
        fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new WeFragment());
        fragments.add(new AskFragment());
        fragments.add(new ShopCarFragment());
        fragments.add(new PersonalFragment());
        adapter=new ActHomePageAdapter(iHomeView.getManager(),fragments);
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
