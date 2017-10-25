package com.example.ksion.wetaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.SettingContract;
import com.example.ksion.wetaobao.presenter.ActSettingPersenterImpl;

import zhangphil.iosdialog.widget.ActionSheetDialog;

/**
 * Created by Ksion on 2017/9/18.
 */

public class SettingActivity extends BaseActivity implements SettingContract.SettingView,View.OnClickListener{
    ImageView mActSettingIvBack;
    LinearLayout mActSettingPersonal;
    TextView mActSettingExit;


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
        mActSettingPersonal= (LinearLayout) findViewById(R.id.act_setting_ln_person);
        mActSettingExit= (TextView) findViewById(R.id.act_personal_setting_tv_exit);
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
            case R.id.act_personal_setting_tv_exit:
                new ActionSheetDialog(SettingActivity.this)
                        .builder()
                        .setTitle("确定退出登录吗？")
                        .setCancelable(true)
                        .addSheetItem("确定",ActionSheetDialog.SheetItemColor.Red,
                                new ActionSheetDialog.OnSheetItemClickListener() {

                                    @Override
                                    public void onClick(int which) {
                                        //退出登录
                                        persenter.logingOut();
                                    }
                                }).show();
                break;
            case R.id.act_setting_ln_person:
                startActivity(new Intent(SettingActivity.this,PersonalDetailActivity.class));
                break;
        }
    }
}
