package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.FragShopcarContract;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Ksion on 2017/9/14.
 */

public class FragShopCarAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    //数据源
    private List<ShopCar> shopCars;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    FragShopcarContract.FragShopcarPresenter presenter;

    private Context context;

    public FragShopCarAdapter(Context context, List<ShopCar>shopCars, FragShopcarContract.FragShopcarPresenter presenter)
    {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.shopCars=shopCars;
        this.presenter=presenter;


    }

    @Override
    public int getCount() {
        return shopCars.size();
    }

    @Override
    public Object getItem(int i) {
        return shopCars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.frag_shop_car_xlv_item,null);
            viewHolder.mIvGoodsImg= (ImageView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_img);
            viewHolder.mTvCount= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_count);
            viewHolder.mcb= (CheckBox) convertView.findViewById(R.id.frag_shop_car_xlv_item_cb);
            viewHolder.mTvPrice= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_price);
            viewHolder.mTvGoodName= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_name);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }


          final ViewHolder finalViewHolder=viewHolder;
          //设置商品ID
           initDatas(finalViewHolder,shopCars.get(position).getGoodId(),position,shopCars.get(position).getCount());
          //设置商品数量
           finalViewHolder.mTvCount.setText(shopCars.get(position).getCount()+"");
        return convertView;
    }

    private void initDatas(final ViewHolder holder, final String goodId, final int position,final int count) {
        if(holder!=null) {

            String sql="select * from Goods where goodId='"+goodId+"'";
            new BmobQuery<Goods>().doSQLQuery(context,sql, new SQLQueryListener<Goods>() {

                @Override
                public void done(BmobQueryResult<Goods> bmobQueryResult, BmobException e) {
                    if(!bmobQueryResult.getResults().isEmpty()) {
                        final Goods  good =bmobQueryResult.getResults().get(0);
                        Picasso.with(context).load(good.getGoodsImgs().getUrl()).into(holder.mIvGoodsImg);
                        holder.mTvPrice.setText("￥" + good.getGoodsPrice());
                        holder.mTvGoodName.setText(good.getGoodsName());
                        holder.mTvCount.setText(count+"");
                        holder.mcb.setChecked(isSelected.get(position));
                        initData(Double.parseDouble(good.getGoodsPrice()+""),position);
                        holder.mcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked)
                                {
                                    saveGood(position,Double.parseDouble(good.getGoodsPrice()+""));
                                }else{
                                    removeGood(position,Double.parseDouble(good.getGoodsPrice()+""));
                                }

                            }
                        });
                    }
                }
            });

        }
    }



    private void saveGood(int position,double price)
    {
        presenter.saveGoodIds(shopCars.get(position).getGoodId());
        presenter.saveShopCarIds(shopCars.get(position).getGoodId());
        presenter.changeMoney(shopCars.get(position).getCount(),price);
    }

    private void removeGood(int position,double price)
    {
        presenter.removeGoodIds(shopCars.get(position).getGoodId());
        presenter.removShopCarIds(shopCars.get(position).getObjectId());
        presenter.changeMoney(shopCars.get(position).getCount(),-price);
    }


    // 初始化isSelected的数据i<5暂时固定数据测试
    private void initData(double price,int position) {
            getIsSelected().put(position, true);
            saveGood(position,price);

    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        FragShopCarAdapter.isSelected = isSelected;
    }



    public static class ViewHolder {
        public ImageView mIvGoodsImg;
        public TextView mTvGoodName, mTvPrice, mTvCount;
        public CheckBox mcb;
    }
}
