package com.example.ksion.wetaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.activity.LoginActivity;
import com.example.ksion.wetaobao.activity.MyOrderActivity;
import com.example.ksion.wetaobao.activity.PersonalDetailActivity;
import com.example.ksion.wetaobao.activity.SettingActivity;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.config.Contracts;
import com.example.ksion.wetaobao.contract.PersonalContract;
import com.example.ksion.wetaobao.presenter.PersonalPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import com.squareup.picasso.Picasso;


/**
 * Created by Ksion on 2017/9/4.
 */

public class PersonalFragment extends BaseFragment implements PersonalContract.PersonalView,View.OnClickListener{
    private PersonalContract.PersonalPresenter presenter;

    private TextView mSettings;
    //用户名字
    private TextView mMyname;
    private Button mBtnPay;
    private Button mBtnFahuo;
    private Button mBtnShouhuo;
    private Button mBtnPingjia;
    private Button mBtnFukuan;
    //用户头像
    private ImageView mMyHead;

    GridView mFragPersonalGvCenter;
    GridView mFragPersonalGvBottom;

    RelativeLayout mFragPersonalLvmyOrder;

    private boolean isPrepared=false;

    public void lazyLoad()
    {
        if(!isPrepared) {
            return;
        }
        if(!isLogin()) {
            startActivityForResult(new Intent(getContext(), LoginActivity.class),100);
        }
        else{
           setUserData();
        }
    }



    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_personal,null);
        mSettings= (TextView) rootView.findViewById(R.id.frag_personal_tv_setting);
        mMyname= (TextView) rootView.findViewById(R.id.frag_personal_tv_nick_name);
        mBtnPay= (Button) rootView.findViewById(R.id.frag_personal_btn_pay);
        mBtnFahuo= (Button) rootView.findViewById(R.id.frag_personal_btn_daifahuo);
        mBtnShouhuo= (Button) rootView.findViewById(R.id.frag_personal_btn_daishouhuo);
        mBtnPingjia= (Button) rootView.findViewById(R.id.frag_personal_btn_daipingjia);
        mBtnFukuan= (Button) rootView.findViewById(R.id.frag_personal_btn_tuikuan);
        mFragPersonalGvCenter= (GridView) rootView.findViewById(R.id.frag_personal_gv_center);
        mFragPersonalGvBottom= (GridView) rootView.findViewById(R.id.frag_personal_gv_bottom);
        mMyHead= (ImageView) rootView.findViewById(R.id.frag_personal_iv_head);
        mFragPersonalLvmyOrder= (RelativeLayout) rootView.findViewById(R.id.frag_personal_rl_show_dingdan);
        isPrepared=true;
        lazyLoad();
        return rootView;

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
         new PersonalPresenterImpl(this);
         presenter.initData();

        mMyHead.setOnClickListener(this);
        mSettings.setOnClickListener(this);
        mFragPersonalLvmyOrder.setOnClickListener(this);
    }


    @Override
    public GridView getmFragPersonalGvBottom() {
        return mFragPersonalGvBottom;
    }

    @Override
    public GridView getmFragPersonalGvCenter() {
        return mFragPersonalGvCenter;
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(getContext(),msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public void jumpActivity(int type) {

    }

    @Override
    public void setPresenter(PersonalContract.PersonalPresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.frag_personal_tv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.frag_personal_iv_msg:
                break;
            case R.id.frag_personal_iv_head:
                //跳转到个人详情页面
                startActivity(new Intent(getContext(), PersonalDetailActivity.class));
                break;
            case R.id.frag_personal_rl_show_dingdan:
                startActivity(new Intent(getContext(), MyOrderActivity.class));
                break;
            case R.id.frag_personal_btn_pay:
                presenter.queryOrders(0);
                break;
            case R.id.frag_personal_btn_daifahuo:
                presenter.queryOrders(1);
                break;
            case R.id.frag_personal_btn_daishouhuo:
                presenter.queryOrders(2);
                break;
            case R.id.frag_personal_btn_daipingjia:
                presenter.queryOrders(3);
                break;
            case R.id.frag_personal_btn_tuikuan:
                presenter.queryOrders(4);
                break;
            case R.id.frag_personal_rl_show_jianzhi:
                break;

        }
    }
    private void setUserData() {
        User user=CustomApplcation.getInstance().getCurrentUser();
        if(user!=null) {
            mMyname.setText(user.getUserName());
            String url = "";
            if (user.getUserHead()== null) {
                url = Contracts.DEFALT_HEAD_URL;
            }
            //设置头像
           // Picasso.with(getContext()).load(url).into(mMyHead);
        }
    }
}
