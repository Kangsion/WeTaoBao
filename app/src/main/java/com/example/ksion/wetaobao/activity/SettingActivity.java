package com.example.ksion.wetaobao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.SettingContract;
import com.example.ksion.wetaobao.presenter.ActSettingPersenterImpl;

/**
 * Created by Ksion on 2017/9/18.
 */

public class SettingActivity extends BaseActivity implements SettingContract.SettingView,View.OnClickListener{
    ImageView mActSettingIvBack;

    SettingContract.SettingPersenter persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_personal_setting);
        new ActSettingPersenterImpl(this);
        initViews();
        persenter.initData();
    }

    private void initViews() {
        mActSettingIvBack= (ImageView) findViewById(R.id.act_setting_iv_back);

        mActSettingIvBack.setOnClickListener(this);
    }

    @Override
    public void setPresenter(SettingContract.SettingPersenter presenter) {
         this.persenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_setting_iv_back:
                finish();
                break;
        }
    }
}
