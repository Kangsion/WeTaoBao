package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.LoginContract;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Ksion on 2017/9/8.
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {
    private LoginContract.LoginView mView;
    private EditText Editname;
    private EditText Editpassword;
     User user;

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
            String sql="select * from User where phone='"+phone+"'";
            new BmobQuery<User>().doSQLQuery(mView.getContext(), sql, new SQLQueryListener<User>() {
                @Override
                public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
                    if (!bmobQueryResult.getResults().isEmpty()) {
                         user = bmobQueryResult.getResults().get(0);
                    } else {
                        mView.showMsg("用户不存在");
                    }
                        if (user != null) {
                            if (phone.equals(user.getPhone()) && password.equals(user.getPwd())) {
                                mView.showMsg("登录成功");
                                CustomApplcation.getInstance().setCurrentUser(user);
                                mView.jumpActivity();
                            } else {
                                mView.showMsg("密码错误！\r\n");
                            }
                        } else {
                            mView.showMsg("用户不存在");
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
