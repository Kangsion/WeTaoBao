package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.RegisterContract;
import com.example.ksion.wetaobao.gen.UserDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;
import com.example.ksion.wetaobao.util.DataProcessingUtils;

/**
 * Created by Ksion on 2017/9/13.
 */

public class ActRegisterPresentImpl  implements RegisterContract.RegisterPresenter {
     private RegisterContract.RegisterView view;
     private EditText mEtPhone, mEtCode, mEtNickName, mEtPwd, mEtPwd2;
     private TextView mTvGetCode;
     UserDao userDao;

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
             User findUser=userDao.queryBuilder().where(UserDao.Properties.Phone.eq(phone)).unique();
            if(findUser==null) {
                User user = new User(null, NickName, null, "男", null, Pwd, phone);
                userDao.insert(user);
                view.showMsg("注册成功");
                view.jumpActivity();
            } else {
                view.showMsg("用户名已存在");
            }
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

         userDao = GreenDaoManager.getInstance().getNewSession().getUserDao();
    }
}
