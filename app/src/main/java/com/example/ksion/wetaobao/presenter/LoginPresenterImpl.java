package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.LoginContract;
import com.example.ksion.wetaobao.gen.UserDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;

/**
 * Created by Ksion on 2017/9/8.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {
    private LoginActivity mView;
    private EditText Editname;
    private EditText Editpassword;
    UserDao userDao;

     public LoginPresenterImpl(LoginActivity view)
    {
        mView=view;
        mView.setPresenter(this);
    }
    @Override
    public void login() {
        String phone=Editname.getText().toString().trim();
        String password=Editpassword.getText().toString().trim();
        if(phone.isEmpty()&&password.isEmpty()) {
             mView.showMsg("账号或密码不能为空");
        }
        else {
            if (userDao!= null) {
                User findUser = userDao.queryBuilder().where(UserDao.Properties.Phone.eq(phone)).build().unique();
                if(findUser!=null) {
                    String Xphome = findUser.getPhone();
                    String Xpwd = findUser.getPassword();
                    if (phone.equals(Xphome) && password.equals(Xpwd)) {
                        mView.showMsg("登录成功");
                        CustomApplcation.getInstance().setCurrentUser(findUser);
                        mView.jumpActivity();
                    }
                    else{
                        mView.showMsg("密码错误！\r\n");
                    }
                }else {
                    mView.showMsg("登录失败！\r\n");
                }
            }
        }
    }

    @Override
    public void initData() {
         Editname=mView.getmActHomeEtPhone();
         Editpassword=mView.getmActLoginEtPwd();

         userDao = GreenDaoManager.getInstance().getNewSession().getUserDao();
    }
}
