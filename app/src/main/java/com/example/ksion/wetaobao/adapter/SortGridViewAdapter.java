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
 * Created by Ksion on 2017/9/1.
 */

public class SortGridViewAdapter extends BaseAdapter {
    //图标资源
    private Integer [] sortImgs;
    //文字资源
    private String [] sortStr;
    //布局文件
    private int resource;
    //布局填充器
    private LayoutInflater inflater;
    public   SortGridViewAdapter(Context context,Integer [] sortImgs,String[] sortStr,int resource)
    {
        this.sortImgs=sortImgs;
        this.sortStr=sortStr;
        this.resource=resource;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return sortImgs.length;
    }

    @Override
    public Object getItem(int position) {
        return sortImgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView=inflater.inflate(resource,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.grid_view_iv);
            holder.textView= (TextView) convertView.findViewById(R.id.grid_view_tv);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(sortImgs[position]);
        holder.textView.setText(sortStr[position]);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
