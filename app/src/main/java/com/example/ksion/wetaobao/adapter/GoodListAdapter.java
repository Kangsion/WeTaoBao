package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;

/**
 * Created by Ksion on 2017/10/5.
 */

public class GoodListAdapter extends BaseAdapter {

    private Context context;
    private Integer [] goodImgs;
    private String [] goodNames;
    private double [] prices;
    private Integer [] number;

    private LayoutInflater inflater;

    public GoodListAdapter(Context context,Integer [] goodImgs,String [] goodNames, double [] prices
                           ,Integer [] number)
    {
        this.context=context;
        this.goodImgs=goodImgs;
        this.goodNames=goodNames;
        this.prices=prices;
        this.number=number;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodImgs.length;
    }

    @Override
    public Object getItem(int position) {
        return goodImgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.act_good_list_item,null);
            holder.goodImg= (ImageView) view.findViewById(R.id.act_good_list_item_image);
            holder.goodName= (TextView) view.findViewById(R.id.act_good_list_item_goodName);
            holder.number= (TextView) view.findViewById(R.id.act_good_list_item_number);
            holder.price= (TextView) view.findViewById(R.id.act_good_list_item_cost);
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }

        holder.goodImg.setImageResource(goodImgs[position]);
        holder.goodName.setText(goodNames[position]);
        holder.price.setText(prices[position]+"");
        holder.number.setText(number[position]+"");
        return view;
    }

    class ViewHolder{
        ImageView goodImg;
        TextView goodName,price,number;
    }
}
