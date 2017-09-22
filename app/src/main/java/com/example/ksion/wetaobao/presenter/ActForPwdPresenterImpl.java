package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.FragForPwdContract;
import com.example.ksion.wetaobao.gen.UserDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;

/**
 * Created by Ksion on 2017/9/17.
 */

public class ActForPwdPresenterImpl implements FragForPwdContract.FragforpwdPresenter {

    private FragForPwdContract.FragforPwdView view;
    private EditText mEtPhone, mEtCode, mEtPwd, mEtPwd2;
    private TextView mTvGetCode;

    UserDao userDao;

    public ActForPwdPresenterImpl(FragForPwdContract.FragforPwdView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        mEtPhone=view.getmActForgetEtPhone();
        mEtPwd=view.getmActForgetEtPwd();
        mEtPwd2=view.getmActForgetEtPwd2();
        mEtCode=view.getmActForgetEtSmsCode();
        mTvGetCode=view.getmActForgetTvGetcode();

        userDao = GreenDaoManager.getInstance().getNewSession().getUserDao();
    }

    @Override
    public void getCode() {

    }

    @Override
    public void reset() {
        String phone=mEtPhone.getText().toString().trim();
        String pwd=mEtPwd.getText().toString().trim();
        String pwd2=mEtPwd2.getText().toString().trim();
        if(phone.isEmpty()) {
            view.showMsg("手机号码不能为空");
        } else if (pwd.isEmpty()) {
            view.showMsg("初始密码不能为空");
        } else if(pwd2.isEmpty()) {
            view.showMsg("重置密码不能为空");
        } else {
            User findUser = userDao.queryBuilder().where(UserDao.Properties.Phone.eq(phone)).build().unique();
            if (findUser != null) {
                if (findUser.getPassword().equals(pwd)) {
                    findUser.setPassword(pwd2);
                    userDao.update(findUser);
                    view.showMsg("密码修改成功");
                    view.jumpActivity();
                } else {
                    view.showMsg("密码错误");
                }
            } else {
                view.showMsg("用户不存在");
            }
        }
    }


}
