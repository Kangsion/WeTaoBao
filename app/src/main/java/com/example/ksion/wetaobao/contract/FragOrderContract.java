package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.ListView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/11/8.
 */

public class FragOrderContract {
    public interface FragOrderView extends BaseView<FragOrderPresenter> {

        public Context getOrderContext();


        public int getOrderState();

        public ListView getMyOrderListView();

        void jumpActivity(String type);

    }

    public interface FragOrderPresenter extends BasePresenter<FragOrderView> {

    }
}
