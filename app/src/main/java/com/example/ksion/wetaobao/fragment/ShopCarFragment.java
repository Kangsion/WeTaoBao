package com.example.ksion.wetaobao.fragment;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.contract.FragShopcarContract;

import com.example.ksion.wetaobao.presenter.FragShopCarPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import com.example.ksion.wetaobao.widget.XListView;

/**
 * Created by Ksion on 2017/9/4.
 */

public class ShopCarFragment extends BaseFragment implements FragShopcarContract.FragShopcarView,
        View.OnClickListener{

    CheckBox mFragShopCarCb;

    TextView mFragShopCarTvMoney;

    Button mFragShopCarBtnSubmit;

    RelativeLayout mFragShopcarLn;

    TextView mTvShopCarManager;
    TextView mTvShopCarOk;
    LinearLayout mShopCarlnManager;
    TextView mTvShopCarShouCang;
    TextView mTvShopCarDelete;

    private LinearLayout mGnull;

    ListView mShopCarlistView;

    private FragShopcarContract.FragShopcarPresenter presenter;


    protected void lazyLoad() {
        if(!isPrepared|| !isVisible) {
            return;
        }
        if (!isLogin()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_shopcar,null);
        mFragShopcarLn= (RelativeLayout) rootView.findViewById(R.id.frag_shopcar_ln);
        mGnull= (LinearLayout) rootView.findViewById(R.id.frag_shopcar_notGood);
        mTvShopCarDelete= (TextView) rootView.findViewById(R.id.frag_shopcar_delete);
        mTvShopCarManager= (TextView) rootView.findViewById(R.id.frag_shopcar_manager);
        mTvShopCarOk= (TextView) rootView.findViewById(R.id.frag_shopcar_ok);
        mTvShopCarShouCang= (TextView) rootView.findViewById(R.id.frag_shopcar_shoucang);
        mShopCarlnManager= (LinearLayout) rootView.findViewById(R.id.frag_shopcar_li_manager);
        mShopCarlistView= (ListView) rootView.findViewById(R.id.frag_shopcar_listview);
        mFragShopCarBtnSubmit= (Button) rootView.findViewById(R.id.frag_shop_car_btn_submit);
        mFragShopCarCb= (CheckBox) rootView.findViewById(R.id.frag_shop_car_cb);
        mFragShopCarTvMoney= (TextView) rootView.findViewById(R.id.frag_shop_car_tv_money);
        new  FragShopCarPresenterImpl(this);
        isPrepared=true;
        lazyLoad();
        return rootView;

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        presenter.initData();
        mFragShopCarBtnSubmit.setOnClickListener(this);
        mTvShopCarManager.setOnClickListener(this);
        mTvShopCarOk.setOnClickListener(this);
        mTvShopCarShouCang.setOnClickListener(this);
        mTvShopCarDelete.setOnClickListener(this);

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
    public ListView getmShopCarListView() {
        return mShopCarlistView;
    }

    @Override
    public Context getShopCarContext() {
        return getContext();
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
    public void onResume() {
        super.onResume();
        presenter.initData();
    }

    @Override
    public TextView getmTvShopCarManager() {
        return mTvShopCarManager;
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
            case R.id.frag_shopcar_delete:
                presenter.DeleteSelected();
                break;
            case R.id.frag_shopcar_manager:
                mShopCarlnManager.setVisibility(View.VISIBLE);
                mTvShopCarManager.setVisibility(View.GONE);
                mTvShopCarOk.setVisibility(View.VISIBLE);
                break;
            case R.id.frag_shopcar_ok:
                mShopCarlnManager.setVisibility(View.GONE);
                mTvShopCarOk.setVisibility(View.GONE);
                mTvShopCarManager.setVisibility(View.VISIBLE);
                break;
            case R.id.frag_shopcar_shoucang:
                presenter.ShouCangSelected();
                break;
        }
    }
}
