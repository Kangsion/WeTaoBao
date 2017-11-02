package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;
import android.widget.TextView;


import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.FragForPwdContract;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Ksion on 2017/9/17.
 */

public class ActForPwdPresenterImpl implements FragForPwdContract.FragforpwdPresenter {

    private FragForPwdContract.FragforPwdView view;
    private EditText mEtPhone, mEtCode, mEtPwd, mEtPwd2;
    private TextView mTvGetCode;



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

    }

    @Override
    public void getCode() {

    }

    @Override
    public void reset() {
       String phone=mEtPhone.getText().toString().trim();
        final String pwd=mEtPwd.getText().toString().trim();
        final String pwd2=mEtPwd2.getText().toString().trim();
        if(phone.isEmpty()) {
            view.showMsg("手机号码不能为空");
        } else if (pwd.isEmpty()) {
            view.showMsg("初始密码不能为空");
        } else if(pwd2.isEmpty()) {
            view.showMsg("重置密码不能为空");
        } else {
            String sql="select * from User where pwd='"+phone+"'";
            new BmobQuery<User>().doSQLQuery(view.getContext(), sql, new SQLQueryListener<User>() {
                @Override
                public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
                    if(!bmobQueryResult.getResults().isEmpty()) {
                        User user = bmobQueryResult.getResults().get(0);
                        if(pwd.equals(user.getPwd()))
                        {
                            user.setPwd(pwd2);
                            user.update(view.getContext(), new UpdateListener() {
                                @Override
                                public void onSuccess() {
                                    view.showMsg("密码修改成功");
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                   view.showMsg("密码修改失败"+s);
                                } });
                        } }
                } });
        }
    }


}
