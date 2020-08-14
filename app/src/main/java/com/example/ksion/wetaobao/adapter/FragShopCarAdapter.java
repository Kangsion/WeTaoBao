package com.example.ksion.wetaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Ksion on 2017/9/14.
 */

public class FragShopCarAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    //数据源
    private List<ShopCar> shopCars;
    // 用来控制CheckBox的选中状况
    public static HashMap<Integer, Boolean> isSelected;
    FragShopcarContract.FragShopcarPresenter presenter;

    private Context context;

    public FragShopCarAdapter(Context context, List<ShopCar>shopCars,FragShopcarContract.FragShopcarPresenter presenter)
    {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.shopCars=shopCars;
        this.presenter=presenter;
        // 初始化数据
        initData();
    }

    @Override
    public int getCount() {
        return shopCars.size();
    }

    @Override
    public Object getItem(int position) {
        return shopCars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        ViewHolder viewHolder = null;
//    }

//        if(convertView==null)
//        {
//            viewHolder=new ViewHolder();
//            convertView=inflater.inflate(R.layout.frag_shop_car_xlv_item,null);
//            viewHolder.mIvGoodsImg= (ImageView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_img);
//            viewHolder.mTvCount= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_count);
//            viewHolder.mcb= (CheckBox) convertView.findViewById(R.id.frag_shop_car_xlv_item_cb);
//            viewHolder.mTvPrice= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_price);
//            viewHolder.mTvGoodName= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_good_name);
//            viewHolder.mTvAdd= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_add);
//            viewHolder.mTvJian= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_jian);
//            viewHolder.mEdNum= (EditText) convertView.findViewById(R.id.frag_shop_car_xlv_item_num);
//            viewHolder.mTvXiu= (TextView) convertView.findViewById(R.id.frag_shop_car_xlv_item_xiugai);
//            viewHolder.mBtnOk= (Button) convertView.findViewById(R.id.frag_shop_car_xlv_item_ok);
//            viewHolder.mLnContent1= (LinearLayout) convertView.findViewById(R.id.frag_shop_car_xlv_item_li_content);
//            viewHolder.mLnContent2= (LinearLayout) convertView.findViewById(R.id.frag_shop_car_xlv_item_li_content2);
//            viewHolder.mLnXiushu= (LinearLayout) convertView.findViewById(R.id.frag_shop_car_xlv_item_xiu);
//            convertView.setTag(viewHolder);
//        }
//        else{
//            viewHolder= (ViewHolder) convertView.getTag();
//        }
//          final ViewHolder finalViewHolder=viewHolder;
//          //设置商品ID
//           initDatas(finalViewHolder,shopCars.get(position).getGoodId(),position,shopCars.get(position).getCount());
//          //设置商品数量
//           finalViewHolder.mTvCount.setText(shopCars.get(position).getCount()+"");
 //       return convertView;
  //  }

//    private void initDatas(final ViewHolder holder, final String goodId, final int position,final int count) {
//        if(holder!=null) {
//
//            String sql="select * from Goods where goodId='"+goodId+"'";
//            new BmobQuery<Goods>().doSQLQuery(context,sql, new SQLQueryListener<Goods>() {
//
//                @Override
//                public void done(BmobQueryResult<Goods> bmobQueryResult, BmobException e) {
//                    if(bmobQueryResult!=null) {
//                        final Goods good =bmobQueryResult.getResults().get(0);
//                        Picasso.with(context).load(good.getGoodsImgs().getUrl()).into(holder.mIvGoodsImg);
//                        holder.mTvPrice.setText("￥" + good.getGoodsPrice());
//                        holder.mTvGoodName.setText(good.getGoodsName());
//                        holder.mTvCount.setText(count+"");
//                        holder.mcb.setChecked(isSelected.get(position));
//                        holder.mcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                if(isChecked)
//                                {
//                                    saveGood(position,Double.parseDouble(good.getGoodsPrice()+""));
//                                }else{
//                                    removeGood(position,Double.parseDouble(good.getGoodsPrice()+""));
//                                }
//
//                            }
//                        });
//                        holder.mTvXiu.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                holder.mLnContent1.setVisibility(View.GONE);
//                                holder.mLnContent2.setVisibility(View.GONE);
//                                holder.mLnXiushu.setVisibility(View.VISIBLE);
//                                holder.mTvXiu.setVisibility(View.GONE);
//                            }
//                        });
//                        holder.mBtnOk.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                final int num = Integer.parseInt(holder.mEdNum.getText().toString().trim());
//                                ShopCar shopCar=new ShopCar();
//                                shopCar.setCount(num);
//                                shopCar.update(context,shopCars.get(position).getObjectId() ,new UpdateListener() {
//                                    @Override
//                                    public void onSuccess() {
//                                        shopCars.get(position).setCount(num);
//                                        notifyDataSetChanged();
//                                        holder.mLnContent1.setVisibility(View.VISIBLE);
//                                        holder.mLnContent2.setVisibility(View.VISIBLE);
//                                        holder.mLnXiushu.setVisibility(View.GONE);
//                                        holder.mTvXiu.setVisibility(View.VISIBLE);
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, String s) {
//
//                                    }
//                                });
//
//                            }
//                        });
//                        holder.mTvJian.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int num = Integer.parseInt(holder.mEdNum.getText().toString().trim());
//                                if(num>1)
//                                  num--;
//                                holder.mEdNum.setText(num+"");
//                            }
//                        });
//                        holder.mTvAdd.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int num = Integer.parseInt(holder.mEdNum.getText().toString().trim());
//                                 num++;
//                                holder.mEdNum.setText(num+"");
//                            }
//                        });
//                    }
//                }
//            });
//
    //}

    private void initData() {
        for (int i = 0; i < shopCars.size(); i++) {
            isSelected.put(i, false);
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



    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        FragShopCarAdapter.isSelected= isSelected;
    }



    public static class ViewHolder {
         ImageView mIvGoodsImg;
         TextView mTvGoodName, mTvPrice, mTvCount,mTvXiu,mTvJian,mTvAdd;
         CheckBox mcb;
         EditText mEdNum;
         Button mBtnOk;
         LinearLayout mLnContent1,mLnContent2,mLnXiushu;

    }
}
