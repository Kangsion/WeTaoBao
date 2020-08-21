package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.Collection;
import com.example.ksion.wetaobao.bean.Goods;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Ksion on 2017/11/6.
 */

public class ActCollectionAdapter  extends BaseAdapter{
    private Context context;
    private List<Collection> collectionList;
    public static HashMap<Integer,Boolean> isSelected;
    public static boolean isShow=false;
    public ActCollectionAdapter(Context context,List<Collection> collectionList){
        this.context=context;
        this.collectionList=collectionList;
    }

    @Override
    public int getCount() {
        return collectionList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null) {
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.act_collection_item,null);
            holder.checkBox= (CheckBox) view.findViewById(R.id.act_collection_item_checkbox);
            holder.mGoodImg= (ImageView) view.findViewById(R.id.act_collection_item_img);
            holder.mGoodName= (TextView) view.findViewById(R.id.act_collection_item_name);
            holder.mGoodPrice= (TextView) view.findViewById(R.id.act_collection_item_price);
            view.setTag(holder);
        } else {
            holder= (ViewHolder) view.getTag();
        }
         // String sql="select * from Goods where goodId='"+collectionList.get(position).getGoodId()+"'";
        final ViewHolder finalHolder = holder;
        BmobQuery<Goods> query = new BmobQuery<>();
        query.addWhereEqualTo("goodId", collectionList.get(position).getGoodId());
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e == null) {
                    Goods good = list.get(0);
                    Picasso.with(context).load(good.getGoodsImgs().getUrl()).into(finalHolder.mGoodImg);
                    finalHolder.mGoodName.setText(good.getGoodsName());
                    finalHolder.mGoodPrice.setText(good.getGoodsPrice()+"");
                    if(isShow) {
                        finalHolder.checkBox.setVisibility(View.VISIBLE);
                    } else {
                        finalHolder.checkBox.setVisibility(View.INVISIBLE);
                    }
                    finalHolder.checkBox.setChecked(isSelected.get(position));
                }
            }
        });
        return view;
    }


    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
          ActCollectionAdapter.isSelected=isSelected;
    }


    class ViewHolder {
        CheckBox checkBox;
        TextView mGoodName;
        TextView mGoodPrice;
        ImageView mGoodImg;
    }
}
