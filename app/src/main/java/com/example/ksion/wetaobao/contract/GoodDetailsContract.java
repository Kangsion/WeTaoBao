package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;
import com.jude.rollviewpager.RollPagerView;
import com.example.ksion.wetaobao.widget.XListView;
/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodDetailsContract {
    public  interface GoodDetailsView extends BaseView<GoodDetailsContract.GoodDetailsPresenter> {
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
         */
        void jumpActivity();

        Context getContext();

        /**
         * 跳转到登陆页面
         */
        void jumpLogin();

        RollPagerView getmActGoodsDetailsRollVpAd();

        TextView getmActGoodsDetailsTvGoodsName();

        XListView getmActGoodsDetailsXlv();

        TextView getmActGoodsDetailsTvMoney();

        Button getmActGoodsDetailsBtnShoucang();
    }
    public interface GoodDetailsPresenter extends BasePresenter<GoodDetailsContract.GoodDetailsView> {
        /**
         * 查询信息成功的回调方法
         */
        void queryUserSuccess();

        /**
         * 查询信息失败的回调方法
         * @param e
         */
        //void queryUseError(BmobException e);

        /**
         * 加入购物车
         */
        void joinShopCar();

        /**
         * 分享到微信
         * @param type 1:分享到好友列表 2 分享到朋友圈
         */
        void shareWXAPP(int type);
    }
}
