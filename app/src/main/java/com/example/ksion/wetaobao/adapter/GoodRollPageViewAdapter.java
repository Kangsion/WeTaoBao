package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Ksion on 2017/9/12.
 */

public class GoodRollPageViewAdapter extends StaticPagerAdapter {
    //数据源
    List<ImageView> imgs;
    //上下文对象
    Context context;

     public GoodRollPageViewAdapter(List<ImageView> imgs)
     {
         this.imgs=imgs;
     }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view=new ImageView(container.getContext());
        Picasso.with(context).load(imgs.get(position).getId()).into(view);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }
}
