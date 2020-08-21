package com.example.ksion.wetaobao.presenter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.FragMyOrderAdapter;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.contract.FragOrderContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
        BmobQuery<Order> query = new BmobQuery<>();
        query.addWhereEqualTo("phone", phone);
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if(e == null) {
                    myOrderListView.setAdapter(new FragMyOrderAdapter(view.getOrderContext(), list));
                }
            }
        });
    }
}
