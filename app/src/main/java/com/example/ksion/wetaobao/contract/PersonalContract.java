package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/8.
 */

public class PersonalContract {
    public  interface PersonalView extends BaseView<PersonalPresenter>{
        GridView getmFragPersonalGvBottom();

        GridView getmFragPersonalGvCenter();


        ImageView getmIvUserImg();
        TextView getmTvUserName();

        /**
         * Toast数据
         * @param msg
         */
        void showMsg(String msg);

        /**
         * 展示一个进度条对话框
         * @param title 标题
         * @param msg 显示的内容
         * @param flag 是否可以取消
         */
        void showLoadingDialog(String title, String msg, boolean flag);

        /**
         * 取消进度条
         */
        void canelLoadingDialog();

        Context getPersonalContext();

        /**
         * activity的跳转  1 为跳转到订单页面  2 为跳转到收藏界面
         */
        void jumpActivity(int type);
    }
    public interface PersonalPresenter extends BasePresenter<PersonalView>
    {

        /**
         * @param type -1为查询全部订单 0 待付款 1 已付款,待发货 2 已发货，待收货 3已收货, 待评价 4退款 5.取消的订单
         */
        void queryOrders(int type);
    }
}
