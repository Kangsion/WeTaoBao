package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.AddressContract;
import com.example.ksion.wetaobao.presenter.ActAdressPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Administrator on 2017/10/9.
 */

public class AddressChangeActivity extends BaseActivity implements AddressContract.AddressView,View.OnClickListener {
    ImageView mActAddressIvBack;
    TextView mActAddressTvSave;
    EditText mActAdressEdit;
    private AddressContract.AddressPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_address);
        initView();
        new ActAdressPresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActAddressIvBack= (ImageView) findViewById(R.id.act_address_iv_back);
        mActAddressTvSave= (TextView) findViewById(R.id.act_address_save);
        mActAdressEdit= (EditText) findViewById(R.id.act_address_edit);

        mActAddressIvBack.setOnClickListener(this);
        mActAddressTvSave.setOnClickListener(this);
    }



    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this,msg).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void jumpActivity() {
         finish();
    }

    @Override
    public EditText getmActAddressEdit() {
        return mActAdressEdit;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_address_iv_back:
                finish();
                break;
            case R.id.act_address_save:
                presenter.updateAddress();
                break;
        }
    }

    @Override
    public void setPresenter(AddressContract.AddressPresenter presenter) {
        this.presenter=presenter;
    }


}
