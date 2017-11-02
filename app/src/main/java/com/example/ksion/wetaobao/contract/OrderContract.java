package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;
import com.example.ksion.wetaobao.bean.Goods;

/**
 * Created by Ksion on 2017/9/19.
 */

public class OrderContract {
    public interface OrderView extends BaseView<OrderPresenter> {
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

            /**
             * activity的跳转
             * @param objectId  订单号
             * @param sum  订单金额
             */
            void jumpActivity(String objectId, double sum);
            /**
             * 将数据显示到控件上
             * @param goods
             */
            void setData(Goods goods);

            EditText getmActOrderEtCount();

            TextView getmActOrdersTvNumMoney();

            TextView getmActOrderTvGoodsMoney();

            Context getContext();
    }
    public interface OrderPresenter extends BasePresenter<OrderView> {

        /**
         * 计算总价格
         * @param count 商品数量
         * @param strPrice 商品价格
         */
        void changeSumPrice(int count, String strPrice);

        /**
         * 提交订单
         */
        void submit();
    }
}
