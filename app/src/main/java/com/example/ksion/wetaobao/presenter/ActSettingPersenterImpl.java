package com.example.ksion.wetaobao.presenter;

import com.example.ksion.wetaobao.contract.SettingContract;

/**
 * Created by Ksion on 2017/9/18.
 */

public class ActSettingPersenterImpl implements SettingContract.SettingPersenter {

    private SettingContract.SettingView view;

    public ActSettingPersenterImpl(SettingContract.SettingView view)
    {
        this.view=view;
        view.setPresenter(this);
    }


    @Override
    public void initData() {

    }
}
