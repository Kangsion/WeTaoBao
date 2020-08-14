package com.example.ksion.wetaobao.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.ActHomePageAdapter;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.contract.MyOrderContract;
import com.example.ksion.wetaobao.fragment.NewsFragment;
import com.example.ksion.wetaobao.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ActMyOrderPresenterImpl implements MyOrderContract.myOrderPresenter {

    private MyOrderContract.myOrderView view;

    private ViewPager viewPager;

    private  final int ORDER_STATE=5;

    public ActMyOrderPresenterImpl(MyOrderContract.myOrderView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        viewPager=view.getViewPager();
        List<Fragment> fragments=new ArrayList<>();

        for (int i = 0; i < ORDER_STATE; i++) {
           OrderFragment  orderfragment=new OrderFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("state",i);
            orderfragment.setArguments(bundle);
            fragments.add(orderfragment);
        }

        viewPager.setAdapter(new ActHomePageAdapter(view.getManager(),fragments));

    }
}
