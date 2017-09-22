package com.example.ksion.wetaobao.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ksion on 2017/9/5.
 */

public class AdRollPageAdapter extends StaticPagerAdapter {
    private ArrayList<Integer>  imgs;


     public AdRollPageAdapter( ArrayList<Integer> imgs)
     {
         this.imgs=imgs;
     }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view=new ImageView(container.getContext());
        view.setBackgroundResource(imgs.get(position));
        view.setScaleType(view.getScaleType());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}
