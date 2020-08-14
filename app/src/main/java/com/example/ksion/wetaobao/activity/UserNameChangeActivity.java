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
import com.example.ksion.wetaobao.contract.UserNameContract;
import com.example.ksion.wetaobao.presenter.ActUserNamePresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/11/20.
 */

public class UserNameChangeActivity extends BaseActivity implements UserNameContract.UserNameView,
        View.OnClickListener{
    ImageView mActUserNameIvBack;
    TextView mActUserNameTvSave;
    EditText mActUserNameEdit;
    private UserNameContract.UserNamePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_username);
        initView();
        new ActUserNamePresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActUserNameIvBack= (ImageView) findViewById(R.id.act_username_iv_back);
        mActUserNameTvSave= (TextView) findViewById(R.id.act_username_save);
        mActUserNameEdit= (EditText) findViewById(R.id.act_username_edit);

        mActUserNameIvBack.setOnClickListener(this);
        mActUserNameTvSave.setOnClickListener(this);
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
    public EditText getmActUserNameEdit() {
        return mActUserNameEdit;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_username_iv_back:
                finish();
                break;
            case R.id.act_username_save:
                presenter.updateUserName();
                break;
        }
    }

    @Override
    public void setPresenter(UserNameContract.UserNamePresenter presenter) {
        this.presenter=presenter;
    }

}
