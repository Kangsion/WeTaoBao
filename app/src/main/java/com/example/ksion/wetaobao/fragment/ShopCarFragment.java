package com.example.ksion.wetaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.FragShopcarContract;
import com.example.ksion.wetaobao.gen.ShopCarDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;
import com.example.ksion.wetaobao.presenter.FragShopCarPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
/**
 * Created by Ksion on 2017/9/4.
 */

public class ShopCarFragment extends BaseFragment implements FragShopcarContract.FragShopcarView,
        View.OnClickListener{

    ImageView mFragShopCarMsg;

    SwipeMenuListView mFragShopcarLv;

    CheckBox mFragShopCarCb;

    TextView mFragShopCarTvMoney;

    Button mFragShopCarBtnSubmit;

    RelativeLayout mFragShopcarLn;

    private LinearLayout mGnull;

    private FragShopcarContract.FragShopcarPresenter presenter;

    private Boolean isPrepared;

    protected void lazyLoad() {
       if(!isPrepared||isVisible) {
            return;
       }
           if (!isLogin()) {
               startActivityForResult(new Intent(getContext(), LoginActivity.class), 500);
           } else {
               setUserData();
           }

    }

    private void setUserData() {
      presenter.initData();
    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_shopcar,null);
        isPrepared=true;
        lazyLoad();

        mFragShopCarMsg= (ImageView) rootView.findViewById(R.id.frag_shop_car_msg);
        mFragShopcarLv= (SwipeMenuListView) rootView.findViewById(R.id.frag_shopcar_lv);
        mFragShopCarCb= (CheckBox) rootView.findViewById(R.id.frag_shop_car_cb);
        mFragShopCarTvMoney= (TextView) rootView.findViewById(R.id.frag_shop_car_tv_money);
        mFragShopCarBtnSubmit= (Button) rootView.findViewById(R.id.frag_shop_car_btn_submit);
        mFragShopcarLn= (RelativeLayout) rootView.findViewById(R.id.frag_shopcar_ln);
        mGnull= (LinearLayout) rootView.findViewById(R.id.frag_shopcar_notGood);
        new  FragShopCarPresenterImpl(this);
        return rootView;

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        presenter.initData();

        mFragShopCarBtnSubmit.setOnClickListener(this);
    }



    @Override
    public LinearLayout getmGnull()
    {
        return mGnull;
    }

    @Override
    public RelativeLayout getmFragShopcarLn() {
        return mFragShopcarLn;
    }

    @Override
    public SwipeMenuListView getmFragShopcarLv() {
        return mFragShopcarLv;
    }

    @Override
    public CheckBox getmFragShopCarCb() {
        return mFragShopCarCb;
    }

    @Override
    public TextView getmFragShopCarTvMoney() {
        return mFragShopCarTvMoney;
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(getContext(), msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public void jumpActivity(String objectId, double sum) {

    }

    @Override
    public void setPresenter(FragShopcarContract.FragShopcarPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.frag_shop_car_btn_submit:
                presenter.submit();
                break;
        }
    }
}
