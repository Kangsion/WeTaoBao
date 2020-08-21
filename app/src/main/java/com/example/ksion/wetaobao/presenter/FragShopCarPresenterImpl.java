package com.example.ksion.wetaobao.presenter;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.FragShopCarAdapter;
import com.example.ksion.wetaobao.bean.Collection;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.FragShopcarContract;
import com.example.ksion.wetaobao.widget.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteBatchListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Ksion on 2017/9/8.
 */

public class FragShopCarPresenterImpl implements FragShopcarContract.FragShopcarPresenter {

    private FragShopcarContract.FragShopcarView view;
    private CheckBox mCb;
    private FragShopCarAdapter adapter;
    private TextView mTvMoney;
    private RelativeLayout mFragShopcarLn;
    ListView mShopCarlistView;
    private List<ShopCar> listShopCas;

    List<String> listGoodsIds  = new ArrayList<>();

    List<String> checkedShopCars = new ArrayList<>();

    private LinearLayout mGnull;
    private TextView mTvShopCarManager;


    public FragShopCarPresenterImpl(FragShopcarContract.FragShopcarView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        mGnull=view.getmGnull();
        mCb=view.getmFragShopCarCb();
        mTvMoney=view.getmFragShopCarTvMoney();
        mFragShopcarLn=view.getmFragShopcarLn();
        mShopCarlistView=view.getmShopCarListView();
        mTvShopCarManager=view.getmTvShopCarManager();

        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            mTvMoney.setText("0.00");
            String phone=CustomApplcation.getInstance().getCurrentUser().getPhone();
            BmobQuery<ShopCar> query = new BmobQuery<>();
            query.addWhereEqualTo("phone", phone);
            query.findObjects(new FindListener<ShopCar>() {
                @Override
                public void done(List<ShopCar> list, BmobException e) {
                    if(e == null) {
                        if(list.size() == 0) {
                            mGnull.setVisibility(View.GONE);
                            mFragShopcarLn.setVisibility(View.VISIBLE);
                            mTvShopCarManager.setVisibility(View.VISIBLE);
                            listShopCas = list;

                           HashMap<Integer, Boolean> isChecked = new HashMap<>();
                           for (int i = 0; i < listShopCas.size(); i++) {
                               isChecked.put(i, false);
                           }
                          FragShopCarAdapter.setIsSelected(isChecked);
                          adapter= new FragShopCarAdapter(view.getShopCarContext(), list, FragShopCarPresenterImpl.this);
                          mShopCarlistView.setAdapter(adapter);
                        } else {
                            mGnull.setVisibility(View.VISIBLE);
                            mFragShopcarLn.setVisibility(View.GONE);
                            mTvShopCarManager.setVisibility(View.GONE);
                        }
                    }
                }
            });
           initEvent();
        }else{
            mGnull.setVisibility(View.VISIBLE);
            mFragShopcarLn.setVisibility(View.GONE);
            mTvShopCarManager.setVisibility(View.GONE);
        }
    }

    @Override
    public void queryDatasFromServer() {

    }


    private void dataChanged() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void changeMoney(int count, double price) {
        double sum = count * price;
        double oldSum = Double.parseDouble(mTvMoney.getText().toString().trim());
        double newSum = oldSum + sum;

       /* NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String strMoney = numberFormat.format(newSum);*/
        String strMoney=newSum+"";
        if (newSum>0) {
            mTvMoney.setText(strMoney);
        } else {
            mTvMoney.setText("0.00");
        }
    }

    @Override
    public void submit() {
        for (int i=listShopCas.size()-1;i>=0;i--){
            if (FragShopCarAdapter.isSelected.get(i)) {
                Order order = new Order();
                order.setPhone(listShopCas.get(i).getPhone());
                order.setGoodId(listShopCas.get(i).getGoodId());
                order.setGoodsCount(listShopCas.get(i).getCount());
                order.setOrdersState(0);
                double Sum = Double.parseDouble(mTvMoney.getText().toString().trim());
                order.setOerdersMoney(Sum);
                final int finalI = i;
                order.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e == null) {
                            listShopCas.remove(finalI);
                            adapter.notifyDataSetChanged();
                        } else {
                            view.showMsg(s);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void saveGoodIds(String goodId) {
        listGoodsIds.add(goodId);
    }

    @Override
    public void removeGoodIds(String goodId) {
        listGoodsIds.add(goodId);
    }

    @Override
    public void saveShopCarIds(String objectId) {
        checkedShopCars.add(objectId);
    }

    @Override
    public void removShopCarIds(String objectId) {
        checkedShopCars.add(objectId);
    }

    @Override
    public void DeleteSelected() {
       for (int i=listShopCas.size()-1;i>=0;i--){
            if (FragShopCarAdapter.isSelected.get(i)) {
                 ShopCar shopCar=new ShopCar();
                final int finalI = i;
                shopCar.setObjectId(listShopCas.get(i).getObjectId());
                shopCar.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null) {
                            listShopCas.remove(finalI);
                            adapter.notifyDataSetChanged();
                        } else {
                            view.showMsg("删除失败" + e);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void ShouCangSelected() {
        for (int i=listShopCas.size()-1;i>=0;i--){
            if (FragShopCarAdapter.isSelected.get(i)) {
                if(!QueryIsCollection(i)) {
                Collection collection=new Collection();
                collection.setGoodId(listShopCas.get(i).getGoodId());
                collection.setPhone(listShopCas.get(i).getPhone());
                final int finalI = i;
                collection.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e == null) {
                            listShopCas.remove(finalI);
                            adapter.notifyDataSetChanged();
                            view.showMsg("已移入收藏夹");
                        } else {
                            view.showMsg("收藏失败" + e);
                        }
                    }
                });
            } }
        }
    }

    public  boolean isCollection=false;
    public boolean QueryIsCollection(int position) {
        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            String phone = CustomApplcation.getInstance().getCurrentUser().getPhone();
            final String goodId = listShopCas.get(position).getGoodId();
            BmobQuery<Collection> query = new BmobQuery<>();
            query.addWhereEqualTo("phone", phone);
            query.addWhereEqualTo("goodId", goodId);
            query.findObjects(new FindListener<Collection>() {
                @Override
                public void done(List<Collection> list, BmobException e) {
                    if (list.size() != 0) {
                        isCollection = true;
                    }
                }
            });
        }
        return isCollection;
    }

    private void initEvent() {
        if(mCb!=null)
            mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked) {
                        for (int i = 0; i < listShopCas.size(); i++) {
                            FragShopCarAdapter.isSelected.put(i, true);
                        }
                        dataChanged();
                        mCb.setText("全选");
                    } else {
                        for (int i = 0; i <  listShopCas.size(); i++) {
                            if (FragShopCarAdapter.isSelected.get(i)) {
                                FragShopCarAdapter.isSelected.put(i, false);
                            }
                        }
                        dataChanged();
                        mCb.setText("全选");
                    }
                }
            });

    }
}
