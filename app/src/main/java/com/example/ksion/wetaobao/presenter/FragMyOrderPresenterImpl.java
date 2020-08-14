package com.example.ksion.wetaobao.presenter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.FragMyOrderAdapter;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.contract.FragOrderContract;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Ksion on 2017/10/14.
 */

public class FragMyOrderPresenterImpl implements FragOrderContract.FragOrderPresenter {


    ListView myOrderListView;
    public static FragOrderContract.FragOrderView view;

    public FragMyOrderPresenterImpl(FragOrderContract.FragOrderView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        myOrderListView=view.getMyOrderListView();
        myOrderListView.setEnabled(false);
        String phone= CustomApplcation.getInstance().getCurrentUser().getPhone();
        String sql="select * from Order phone='"+phone+"'";
//        new BmobQuery<Order>().doSQLQuery(view.getOrderContext(), sql, new SQLQueryListener<Order>() {
//            @Override
//            public void done(BmobQueryResult<Order> bmobQueryResult, BmobException e) {
//                 if(bmobQueryResult!=null) {
//                     myOrderListView.setAdapter(new FragMyOrderAdapter(view.getOrderContext(),
//                             bmobQueryResult.getResults()));
//                 }
//            }
//        });
    }



}
