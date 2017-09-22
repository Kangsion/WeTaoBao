package com.example.ksion.wetaobao.contract;

import android.widget.GridView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodResultContract {
    public  interface GoodResultView extends BaseView<GoodResultPresenter> {
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

        String getDatas();

        GridView getmActGoodsResultGv();
    }
    public interface GoodResultPresenter extends BasePresenter<GoodResultView> {}
}
