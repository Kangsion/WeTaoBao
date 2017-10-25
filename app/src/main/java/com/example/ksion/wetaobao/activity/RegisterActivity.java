package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.RegisterContract;
import com.example.ksion.wetaobao.presenter.ActRegisterPresentImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/9/13.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView,View.OnClickListener{

    //获取控件

    TextView mFragTvHelp;

    EditText mActHomeEtPhone;

    TextView mFragRegisterTvGetcode;

    EditText mActHomeEtSmsCode;

    EditText mActHomeEtNickName;

    EditText mActRegEtPwd;

    EditText mActRegEtPwd2;

    TextView mActRegTvReg;

    ImageView mFragRegisterTvBack;

    Context context;
    private RegisterContract.RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        context=this;
        initView();
        new ActRegisterPresentImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActHomeEtNickName = (EditText) findViewById(R.id.act_reg_et_nick_name);
        mActHomeEtPhone = (EditText) findViewById(R.id.act_home_et_phone);
        mActHomeEtSmsCode = (EditText) findViewById(R.id.act_home_et_sms_code);
        mActRegEtPwd = (EditText) findViewById(R.id.act_reg_et_pwd);
        mActRegEtPwd2 = (EditText) findViewById(R.id.act_reg_et_pwd2);
        mActRegTvReg = (TextView) findViewById(R.id.act_reg_tv_reg);
        mFragRegisterTvBack = (ImageView) findViewById(R.id.frag_register_tv_back);
        mFragTvHelp = (TextView) findViewById(R.id.frag_tv_help);
        mFragRegisterTvGetcode = (TextView) findViewById(R.id.frag_register_tv_getcode);

        mFragTvHelp.setOnClickListener(this);
        mFragRegisterTvBack.setOnClickListener(this);
        mActRegTvReg.setOnClickListener(this);
    }
    @Override
    public EditText getmActHomeEtPhone() {
        return mActHomeEtPhone;
    }

    @Override
    public EditText getmActHomeEtSmsCode() {
        return mActHomeEtSmsCode;
    }

    @Override
    public EditText getmActHomeEtNickName() {
        return mActHomeEtNickName;
    }

    @Override
    public EditText getmActRegEtPwd() {
        return mActRegEtPwd;
    }

    @Override
    public EditText getmActRegEtPwd2() {
        return mActRegEtPwd2;
    }

    @Override
    public TextView getmFragRegisterTvGetcode() {
        return mFragRegisterTvGetcode;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this,msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {
          super.showProcessDialog(title,msg,flag);
    }

    @Override
    public void canelLoadingDialog() {
          super.dismissProcessDialog();
    }

    @Override
    public void jumpActivity() {
        finish();
    }

    @Override
    public void setPresenter(RegisterContract.RegisterPresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.frag_register_tv_back://返回上一个Activity
                finish();
                break;
            case R.id.frag_tv_help:
                break;
            case R.id.frag_register_tv_getcode://获取验证码
                presenter.getCode();
                break;
            case R.id.act_reg_tv_reg://注册
                presenter.register();
                break;
        }
    }
}
