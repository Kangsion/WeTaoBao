package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.RegisterContract;
import com.example.ksion.wetaobao.util.DataProcessingUtils;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Ksion on 2017/9/13.
 */

public class ActRegisterPresentImpl  implements RegisterContract.RegisterPresenter {
     private RegisterContract.RegisterView view;
     private EditText mEtPhone, mEtCode, mEtNickName, mEtPwd, mEtPwd2;
     private TextView mTvGetCode;


     public ActRegisterPresentImpl(RegisterContract.RegisterView view)
     {
         this.view=view;
         view.setPresenter(this);
     }
    @Override
    public void getCode() {

    }

    @Override
    public void register() {
        String phone= mEtPhone.getText().toString().trim();
        String NickName=mEtNickName.getText().toString().trim();
        String Pwd=mEtPwd.getText().toString().trim();
        String Pwd2=mEtPwd2.getText().toString().trim();
        if(phone.isEmpty()) {
            view.showMsg("手机号码不能为空");
        } else if (!DataProcessingUtils.isPhone(phone)) {
            view.showMsg("手机号码格式错误");
        } else if (NickName.isEmpty()) {
            view.showMsg("请输入昵称");
        } else if (Pwd.isEmpty()) {
            view.showMsg("请输入密码");
        } else if (Pwd2.isEmpty()) {
            view.showMsg("请输确认密码");
        } else if (!Pwd.equals(Pwd2)) {
            view.showMsg("两次输入密码不一致");
        } else {
            view.showLoadingDialog("","注册中...",false);
            User user=new User();
            user.setPhone(phone);
            user.setUserName(NickName);
            user.setPwd(Pwd);
            user.save(view.getContext(),new SaveListener() {

                @Override
                public void onSuccess() {
                    view.showMsg("注册成功");
                    view.jumpActivity();
                }

                @Override
                public void onFailure(int i, String s) {
                    view.showMsg("注册失败"+s);
                 }
            });

        }
    }

    @Override
    public void initData() {
         mEtPhone=view.getmActHomeEtPhone();
         mEtNickName=view.getmActHomeEtNickName();
         mEtPwd=view.getmActRegEtPwd();
         mEtPwd2=view.getmActRegEtPwd2();
         mEtCode=view.getmActHomeEtSmsCode();
         mTvGetCode=view.getmFragRegisterTvGetcode();
    }
}
