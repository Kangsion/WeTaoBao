package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.ksion.wetaobao.R;



/**
 * Created by Ksion on 2017/9/7.
 */
public class ContentGridViewAdapter extends BaseAdapter {
    private Context context;
    //存放下部分小图标
    private Integer contentIcoImgs[];
    //存放商品图片
    private Integer contentImgs[];
    //存放小图标对应文字
    private String contentText[];
    //存放下面文字
    private String contentText2[];

    private int resource;

    private LayoutInflater inflater;

    public ContentGridViewAdapter(Context context, Integer[] contentIcoImgs, Integer[] contentImgs,
             String[] contentText, String[] contentText2, int resource) {
        this.context=context;
        this.contentIcoImgs=contentIcoImgs;
        this.contentImgs=contentImgs;
        this.contentText=contentText;
        this.contentText2=contentText2;
        this.resource=resource;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contentIcoImgs.length;
    }

    @Override
    public Object getItem(int position) {
        return contentIcoImgs[position];
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
            view=inflater.inflate(resource,null);
            holder=new ViewHolder();
            holder.ivIco= (ImageView) view.findViewById(R.id.frag_home_gv_content_item_iv_ico);
            holder.ivGood1= (ImageView) view.findViewById(R.id.frag_home_gv_content_item_iv_good1);
            holder.ivGood2= (ImageView) view.findViewById(R.id.frag_home_gv_content_item_iv_good2);
            holder.tv1= (TextView) view.findViewById(R.id.frag_home_gv_content_item_tv1);
            holder.tv2= (TextView) view.findViewById(R.id.frag_home_gv_content_item_tv2);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        holder.ivIco.setImageResource(contentIcoImgs[position]);
        holder.ivGood1.setImageResource(contentImgs[position]);
        holder.ivGood2.setImageResource(contentImgs[position]);
        holder.tv1.setText(contentText[position]);
        holder.tv2.setText(contentText2[position]);
        return view;
    }
    class ViewHolder{
        ImageView ivIco,ivGood1,ivGood2;
        TextView tv1,tv2;
    }
}
