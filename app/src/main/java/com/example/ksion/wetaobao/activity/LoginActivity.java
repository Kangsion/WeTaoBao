package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.LoginContract;
import com.example.ksion.wetaobao.presenter.LoginPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/9/8.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView,View.OnClickListener {
    private ImageView mBtnBack;
    private TextView mBtnLogin;
    private TextView mBtnRigster;
    private TextView mBtnForget;
    private TextView mBtnHelp;
    private EditText mTextName;
    private EditText mTextPassword;

    private LoginContract.LoginPresenter presenter;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        context=this;
        initView();
        new LoginPresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mBtnBack= (ImageView) findViewById(R.id.frag_login_iv_back);
        mBtnLogin= (TextView) findViewById(R.id.frag_login_tv_login);
        mBtnRigster= (TextView) findViewById(R.id.frag_login_tv_rigster);
        mBtnForget= (TextView) findViewById(R.id.frag_login_tv_upassword);
        mBtnHelp= (TextView) findViewById(R.id.frag_login_tv_help);
        mTextName= (EditText) findViewById(R.id.frag_login_tv_name);
        mTextPassword= (EditText) findViewById(R.id.frag_login_tv_password);

        mBtnBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnRigster.setOnClickListener(this);
        mBtnForget.setOnClickListener(this);
        mBtnHelp.setOnClickListener(this);
    }

    @Override
    public EditText getmActLoginEtPwd() {
        return mTextPassword;
    }

    @Override
    public EditText getmActHomeEtPhone() {
        return mTextName;
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
       setResult(200);
        finish();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.frag_login_tv_login:
                presenter.login();
                break;
            case R.id.frag_login_tv_help:
                break;
            case R.id.frag_login_tv_rigster:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.frag_login_tv_upassword:
                startActivity(new Intent(this,ForgetPwdActivity.class));
                break;
            case R.id.frag_login_iv_back:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }
    }
}
