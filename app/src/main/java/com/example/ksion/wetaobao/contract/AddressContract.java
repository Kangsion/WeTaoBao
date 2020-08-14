package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Administrator on 2017/10/9.
 */

public class AddressContract  {
    public interface AddressView extends BaseView <AddressPresenter>
    {
        /**
         * Toast数据
         * @param msg
         */
        void showMsg(String msg);

        Context getContext();

        /**
         * activity的跳转
         */
        void jumpActivity();

        EditText getmActAddressEdit();

    }

    public interface AddressPresenter extends BasePresenter<AddressView> {

        void updateAddress();
    }
}
