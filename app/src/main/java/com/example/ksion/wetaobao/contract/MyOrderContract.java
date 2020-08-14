package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyOrderContract {
    public interface myOrderView extends BaseView<myOrderPresenter>{
        public ViewPager getViewPager();


        public FragmentManager getManager() ;

        public Context getMyOrderContext();


    }

    public interface myOrderPresenter extends BasePresenter<myOrderView>{

    }
}
