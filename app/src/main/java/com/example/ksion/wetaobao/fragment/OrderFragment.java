package com.example.ksion.wetaobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.activity.DiscussActivity;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.contract.FragOrderContract;
import com.example.ksion.wetaobao.adapter.FragMyOrderAdapter;
import com.example.ksion.wetaobao.presenter.FragMyOrderPresenterImpl;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Administrator on 2017/10/13.
 */

public class OrderFragment extends BaseFragment implements FragOrderContract.FragOrderView {


    private ListView myOrderlistView;

    FragOrderContract.FragOrderPresenter presenter;

    private int state;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_myorder,null);
         myOrderlistView= (ListView) rootView.findViewById(R.id.frag_myorder_listView);
        return rootView;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
            Bundle bundle=getArguments();
         if(bundle!=null) {
              state=bundle.getInt("state");
              new FragMyOrderPresenterImpl(this);
              presenter.initData();
         }
    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public Context getOrderContext() {
        return getContext();
    }

    @Override
    public int getOrderState() {
        return state;
    }


    @Override
    public ListView getMyOrderListView() {
        return myOrderlistView;
    }

    @Override
    public void jumpActivity(String type) {
        Intent intent=new Intent(getContext(), DiscussActivity.class);
        intent.putExtra("goodId",type);
        startActivity(intent);
    }

    @Override
    public void setPresenter(FragOrderContract.FragOrderPresenter presenter) {
         this.presenter=presenter;
    }
}
