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
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.FragShopcarContract;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

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

    public FragShopCarAdapter(Context context,List<ShopCar>shopCars,FragShopcarContract.
            FragShopcarPresenter presenter)
    {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.shopCars=shopCars;
        this.presenter=presenter;

        initData();
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
           initDatas(finalViewHolder,shopCars.get(position).getGoodId(),position);
          //设置商品数量
           finalViewHolder.mTvCount.setText(shopCars.get(position).getCount()+"");
        return convertView;
    }

    private void initDatas(final ViewHolder holder, final String goodId, final int position) {
        if(holder!=null) {
            Picasso.with(context).load(R.drawable.bookbag).into(holder.mIvGoodsImg);
            holder.mTvPrice.setText("￥" + 500);
            holder.mTvGoodName.setText("超级无敌书包");
            holder.mTvCount.setText("1");
            holder.mcb.setChecked(isSelected.get(position));
            holder.mcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        saveGood(position);
                    }else{
                        removeGood(position);
                    }

                }
            });
        }
    }



    private void saveGood(int position)
    {
        presenter.saveGoodIds(shopCars.get(position).getGoodId());
        presenter.saveShopCarIds(shopCars.get(position).getUserId());
        presenter.changeMoney(1,500);
    }

    private void removeGood(int position)
    {
        presenter.removeGoodIds(shopCars.get(position).getGoodId());
        presenter.removShopCarIds(shopCars.get(position).getUserId());
        presenter.changeMoney(1,-500);
    }


    // 初始化isSelected的数据i<5暂时固定数据测试
    private void initData() {
        for (int i = 0; i < 5; i++) {
            getIsSelected().put(i, false);
        }
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
