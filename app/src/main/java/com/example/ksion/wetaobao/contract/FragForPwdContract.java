package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/17.
 */

public class FragForPwdContract  {
    public interface FragforPwdView extends BaseView<FragforpwdPresenter> {
        /**
         * Toast数据
         *
         * @param msg
         */
        void showMsg(String msg);

        /**
         * 展示一个进度条对话框
         *
         * @param title 标题
         * @param msg   显示的内容
         * @param flag  是否可以取消
         */
        void showLoadingDialog(String title, String msg, boolean flag);

         Context getContext();

        /**
         * 取消进度条
         */
        void canelLoadingDialog();

        /**
         * activity的跳转
         */
        void jumpActivity();

        EditText getmActForgetEtPhone();

        EditText getmActForgetEtPwd();

        EditText getmActForgetEtPwd2();

        EditText getmActForgetEtSmsCode();

        TextView getmActForgetTvGetcode();
    }

    public interface FragforpwdPresenter extends BasePresenter<FragforPwdView>{
        /**
         * 获取验证码
         */
        void getCode();

        /**
         * 重置密码
         */
        void reset();
    }
}
