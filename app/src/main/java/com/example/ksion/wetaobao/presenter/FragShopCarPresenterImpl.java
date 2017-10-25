package com.example.ksion.wetaobao.presenter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.FragShopCarAdapter;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.FragShopcarContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ksion on 2017/9/8.
 */

public class FragShopCarPresenterImpl implements FragShopcarContract.FragShopcarPresenter {

    private FragShopcarContract.FragShopcarView view;
    //声明控件对象
    private SwipeMenuListView mLv;
    private CheckBox mCb;
    private FragShopCarAdapter adapter;
    private TextView mTvMoney;
    private RelativeLayout mFragShopcarLn;

    //数据源
    private List<ShopCar> listShopCas;
    //存放购物车中商品的Id
    List<String> listGoodsIds  = new ArrayList<>();
    //存放选中的购物车id
    List<String> checkedShopCars = new ArrayList<>();

    private LinearLayout mGnull;


    public FragShopCarPresenterImpl(FragShopcarContract.FragShopcarView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {

         mGnull=view.getmGnull();
         mLv=view.getmFragShopcarLv();
         mCb=view.getmFragShopCarCb();
         mTvMoney=view.getmFragShopCarTvMoney();
         mFragShopcarLn=view.getmFragShopcarLn();
        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            if (listShopCas != null) {
                //设置适配器
                HashMap<Integer, Boolean> isChecked = new HashMap<>();
                for (int i = 0; i < listShopCas.size(); i++) {
                    isChecked.put(i, false);
                }
                listShopCas = new ArrayList<>();
                FragShopCarAdapter.setIsSelected(isChecked);
                if (listShopCas != null) {
                    adapter = new FragShopCarAdapter(CustomApplcation.getInstance().context, listShopCas, this);

                    mLv.setAdapter(adapter);
                }
            } else {
                mGnull.setVisibility(View.VISIBLE);
                mFragShopcarLn.setVisibility(View.INVISIBLE);

            }
            //添加侧滑按钮
            addItemButton();
            //初始化监听事件
            initEvent();
        }else{
            mGnull.setVisibility(View.VISIBLE);
            mFragShopcarLn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void queryDatasFromServer() {

    }

    /**
     * 数据改变
     */
    private void dataChanged() {
        // 通知listView刷新
        adapter.notifyDataSetChanged();
    }


    @Override
    public void changeMoney(int count, double price) {
        //显示为原来的加上选中的
        double sum = count * price;
        double oldSum = Double.parseDouble(mTvMoney.getText().toString().trim());
        double newSum = oldSum + sum;

        //保留小数点后两位
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

    /**
     * 为listview添加侧滑删除功能
     */
    private void addItemButton() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        CustomApplcation.getInstance().context);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("修改");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        CustomApplcation.getInstance().context);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mLv.setMenuCreator(creator);
    }


    /**
     * 初始化监听事件
     */
    private void initEvent() {
        //给check 设置选中事件
        if(mCb!=null)
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//全选
                    // 遍历list的长度，将MyAdapter中的map值全部设为true
                    for (int i = 0; i < 5; i++) {
                        FragShopCarAdapter.getIsSelected().put(i, true);
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                    mCb.setText("取消全选");
                } else {//取消全选
                    // 遍历list的长度，将已选的按钮设为未选
                    for (int i = 0; i < 5; i++) {
                        if (FragShopCarAdapter.getIsSelected().get(i)) {
                            FragShopCarAdapter.getIsSelected().put(i, false);
                        }
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                    mCb.setText("全选");
                }
            }
        });
        if(mLv!=null)



        mLv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //获取选中的购物车中的id
                //

              //  String itemObjectId = listShopCas.get(position).getObjectId();

                switch (index) {
                    case 0:

                        // 修改收货地址
                       // open(itemObjectId);
                        break;
                    case 1:

                        // 删除收货地址
                       // delete(itemObjectId);
                        listShopCas.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }
}
