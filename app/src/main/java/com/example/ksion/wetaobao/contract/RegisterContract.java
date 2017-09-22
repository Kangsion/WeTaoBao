package com.example.ksion.wetaobao.contract;

import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/13.
 */

public class RegisterContract {
       public  interface RegisterView extends BaseView<RegisterPresenter> {
         EditText getmActHomeEtPhone();

         EditText getmActHomeEtSmsCode();

         EditText getmActHomeEtNickName();

         EditText getmActRegEtPwd();

         EditText getmActRegEtPwd2();

         TextView getmFragRegisterTvGetcode();

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
     }



      public interface RegisterPresenter extends BasePresenter<RegisterView>
      {
          /**
           * 获取验证码
           */
          void getCode();

          /**
           * 注册账号
           */
          void register();
      }


}
