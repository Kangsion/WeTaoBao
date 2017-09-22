package com.example.ksion.wetaobao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ksion on 2017/9/4.
 */

public class ActHomePageAdapter extends FragmentPagerAdapter{
     private List<Fragment> fragments;

     public ActHomePageAdapter(FragmentManager manager, List<Fragment> fragments)
     {
         super(manager);
         this.fragments=fragments;
     }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
