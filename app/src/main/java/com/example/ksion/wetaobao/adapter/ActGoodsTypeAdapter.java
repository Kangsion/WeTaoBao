package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ksion on 2017/9/11.
 */

public class ActGoodsTypeAdapter extends BaseAdapter {
    private List<GoodsType> goodsTypeList;
    private LayoutInflater inflater;

    public ActGoodsTypeAdapter(List<GoodsType> goodsTypeList, Context context) {
        this.goodsTypeList = goodsTypeList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  vh = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.act_good_gv_type_item,null);
            vh = new ViewHolder();
            vh.imageView = (ImageView) convertView.findViewById(R.id.act_goods_type_gv_item_iv);
            vh.textView = (TextView) convertView.findViewById(R.id.act_goods_type_gv_item_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //设置数据
        GoodsType goodsType = goodsTypeList.get(position);
        vh.textView.setText(goodsType.getGoodsTypeName());
        Picasso.with(CustomApplcation.getInstance().context)
                .load(goodsType.getGoodsTypeImg().getUrl())
                .into(vh.imageView);
        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
