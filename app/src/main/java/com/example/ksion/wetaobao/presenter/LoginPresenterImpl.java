package com.example.ksion.wetaobao.presenter;

import android.support.design.widget.Snackbar;
import android.widget.EditText;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.LoginContract;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
//import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Ksion on 2017/9/8.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {
    private LoginContract.LoginView mView;
    private EditText Editname;
    private EditText Editpassword;
     //User user;

     public LoginPresenterImpl(LoginActivity view)
    {
        mView=view;
        mView.setPresenter(this);
    }

    @Override
    public void login() {
        final String phone = Editname.getText().toString().trim();
        final String password = Editpassword.getText().toString().trim();
        if (phone.isEmpty() || password.isEmpty()) {
            mView.showMsg("账号或密码不能为空");
        } else {
            mView.showLoadingDialog("", "登陆中...", true);
            final User user = new User();
            user.setUsername(phone);
            user.setPassword(password);
            user.login(new SaveListener<User>() {
                @Override
                public void done(User bmobUser, BmobException e) {
                    if (e == null) {
                        User user = BmobUser.getCurrentUser(User.class);
                        CustomApplcation.getInstance().setCurrentUser(user);
                        mView.jumpActivity();
                        mView.showMsg("登录成功" + bmobUser);
                    } else {
                        mView.showMsg("登录失败！\r\n" + e);
                    }
                }
            });
       }
    }

    @Override
    public void initData() {
         Editname=mView.getmActHomeEtPhone();
         Editpassword=mView.getmActLoginEtPwd();
    }
}
