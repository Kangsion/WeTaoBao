package com.example.ksion.wetaobao.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.FragForPwdContract;
import com.example.ksion.wetaobao.presenter.ActForPwdPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/9/17.
 */

public class ForgetPwdActivity extends BaseActivity implements FragForPwdContract.FragforPwdView,
        View.OnClickListener{

    //返回上一界面1231231
    ImageView mFragForgetTvBack;
    //帮助
    TextView mActForgetTvHelp;
    //手机号码
    EditText mActForgetEtPhone;
    //验证码
    TextView mActForgetTvGetcode;
    //短信验证码
    EditText mActForgetEtSmsCode;
    //初始密码
    EditText mActForgetEtPwd;
    //重密码
    EditText mActForgetEtPwd2;
    //重置密码
    TextView mActForgetTvReset;
    private FragForPwdContract.FragforpwdPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_forget);
        initView();
        new ActForPwdPresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mFragForgetTvBack= (ImageView) findViewById(R.id.frag_forget_tv_back);
        mActForgetTvHelp= (TextView) findViewById(R.id.frag_forget_tv_help);
        mActForgetEtPhone= (EditText) findViewById(R.id.frag_forget_tv_tel);
        mActForgetEtSmsCode= (EditText) findViewById(R.id.act_forget_et_sms_code);
        mActForgetEtPwd= (EditText) findViewById(R.id.act_forget_tv_pwd);
        mActForgetEtPwd2= (EditText) findViewById(R.id.act_forget_tv_pwd2);
        mActForgetTvReset= (TextView) findViewById(R.id.act_forget_tv_reset);
        mActForgetTvGetcode= (TextView) findViewById(R.id.act_forget_tv_getcode);

        mActForgetTvReset.setOnClickListener(this);
        mFragForgetTvBack.setOnClickListener(this);
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this,msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public void jumpActivity() {
        finish();
    }

    @Override
    public EditText getmActForgetEtPhone() {
        return mActForgetEtPhone;
    }

    @Override
    public EditText getmActForgetEtPwd() {
        return mActForgetEtPwd;
    }

    @Override
    public EditText getmActForgetEtPwd2() {
        return mActForgetEtPwd2;
    }

    @Override
    public EditText getmActForgetEtSmsCode() {
        return mActForgetEtSmsCode;
    }

    @Override
    public TextView getmActForgetTvGetcode() {
        return mActForgetTvGetcode;
    }

    @Override
    public void setPresenter(FragForPwdContract.FragforpwdPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_forget_tv_reset:
                presenter.reset();
                break;
            case R.id.act_good_details_iv_back:
                finish();
                break;
        }
    }
}
