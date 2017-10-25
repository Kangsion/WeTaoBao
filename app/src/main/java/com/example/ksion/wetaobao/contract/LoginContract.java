package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/8.
 */

public class LoginContract {
     public interface LoginView extends BaseView<LoginPresenter>{
         EditText getmActLoginEtPwd();

         EditText getmActHomeEtPhone();

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
     }
    public  interface LoginPresenter extends BasePresenter<LoginView> {
        /**
         * 登陆操作
         */
        void login();
    }
}
