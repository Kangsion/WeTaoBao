package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.presenter.FragMyOrderPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteBatchListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Ksion on 2017/11/6.
 */

public class FragMyOrderAdapter extends BaseAdapter{

    Context context;
    List<Order> orderList;

    public FragMyOrderAdapter(Context context, List<Order> orderList){
        this.context=context;
        this.orderList=orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
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
            view= LayoutInflater.from(context).inflate(R.layout.myorder_item,null);
            holder.mIvGoodImg= (ImageView) view.findViewById(R.id.myorder_item_goodImg);
            holder.mTvGoodName= (TextView) view.findViewById(R.id.myorder_item_goodName);
            holder.mTvGoodNum= (TextView) view.findViewById(R.id.myorder_item_goodNum);
            holder.mTvGoodSumNum= (TextView) view.findViewById(R.id.myorder_item_sumNumber);
            holder.mTvPrice= (TextView) view.findViewById(R.id.myorder_item_goodPrice);
            holder.mTvGoodSumPrice= (TextView) view.findViewById(R.id.myorder_item_sum);
            holder.mTvPingJia= (TextView) view.findViewById(R.id.myorder_item_pingJia);
            holder.mTvDelete= (TextView) view.findViewById(R.id.myorder_item_delete);
            view.setTag(holder);
        } else  {
            holder= (ViewHolder) view.getTag();
        }
        String sql="select * from Goods where goodId='"+orderList.get(position).getGoodId()+"'";
        final ViewHolder finalHolder = holder;
        BmobQuery<Goods> query = new BmobQuery<>();
        String goodId = orderList.get(position).getGoodId();
        query.addWhereEqualTo("goodId", goodId);
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e == null) {
                     final Goods goods = list.get(0);
                     double price= Double.parseDouble(goods.getGoodsPrice()+"");
                     final int count=orderList.get(position).getGoodsCount();
                     Picasso.with(context).load(goods.getGoodsImgs().getUrl()).into(finalHolder.mIvGoodImg);
                     finalHolder.mTvGoodSumPrice.setText(price*count+"");
                     finalHolder.mTvPrice.setText(price+"");
                     finalHolder.mTvGoodName.setText(goods.getGoodsName());
                     finalHolder.mTvGoodNum.setText(count+"");
                     finalHolder.mTvGoodSumNum.setText(count+"");
                     finalHolder.mTvPingJia.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             FragMyOrderPresenterImpl.view.jumpActivity(goods.getGoodId());
                         }
                     });
                    finalHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(final View view) {
                             Order order = new Order();
                             order.setObjectId(orderList.get(position).getObjectId());
                             order.delete(new UpdateListener() {
                                 @Override
                                 public void done(BmobException e) {
                                    if(e == null) {
                                        orderList.remove(position);
                                        notifyDataSetChanged();
                                        Toast.makeText(context,"删除订单成功",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context,"删除订单失败", Toast.LENGTH_SHORT).show();
                                    }
                                 }
                             });
                         }});
        }}});
        return view;
    }

    class ViewHolder {
         ImageView mIvGoodImg;
         TextView mTvGoodName;
         TextView  mTvPrice;
         TextView  mTvGoodNum;
         TextView  mTvGoodSumNum;
         TextView  mTvGoodSumPrice;
         TextView  mTvPingJia;
         TextView  mTvDelete;
    }
}
