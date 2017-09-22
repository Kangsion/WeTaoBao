package com.example.ksion.wetaobao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.contract.FragAskContract;
import com.example.ksion.wetaobao.presenter.FragAskPresenterImpl;

/**
 * Created by Ksion on 2017/9/4.
 */

public class AskFragment extends BaseFragment implements FragAskContract.IFragAskView,OnClickListener {
    private FragAskContract.IFragPresenter presenter;
    private ViewPager mActAskVpcontent;
    //控件
    private  Button mFragAskBtnHot;
    private  Button mFragAskBtnClothes;
    private  Button mFragAskBtnBuy;
    private  Button mFragAskBtnChild;
    private  Button mFragAskBtnFix;
    private  Button mFragAskBtnBeautiful;
    private  Button mFragAskBtnMedia;
    private  Button mFragAskBtnHeathy;

    private  Button[] btns;
    private   int [] btnID;
    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_ask,null);

        mFragAskBtnHot= (Button) rootView.findViewById(R.id.frag_ask_btn_hot);
        mFragAskBtnClothes= (Button) rootView.findViewById(R.id.frag_ask_btn_clothes);
        mFragAskBtnBuy= (Button) rootView.findViewById(R.id.frag_ask_btn_buy);
        mFragAskBtnChild= (Button) rootView.findViewById(R.id.frag_ask_btn_child);
        mFragAskBtnFix= (Button) rootView.findViewById(R.id.frag_ask_btn_fix);
        mFragAskBtnBeautiful= (Button) rootView.findViewById(R.id.frag_ask_btn_beautiful);
        mFragAskBtnMedia= (Button) rootView.findViewById(R.id.frag_ask_btn_media);
        mFragAskBtnHeathy= (Button) rootView.findViewById(R.id.frag_ask_btn_heahty);
        mActAskVpcontent= (ViewPager) rootView.findViewById(R.id.frag_ask_vp);
        return rootView;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        new FragAskPresenterImpl(this);
        //将控件添加到数组中，用于改变其颜色
        btns = new Button[]{mFragAskBtnHot, mFragAskBtnClothes, mFragAskBtnBuy, mFragAskBtnChild,
                mFragAskBtnFix, mFragAskBtnBeautiful, mFragAskBtnMedia, mFragAskBtnHeathy};
        btnID = new int[]{R.id.frag_ask_btn_hot, R.id.frag_ask_btn_clothes, R.id.frag_ask_btn_buy,
                R.id.frag_ask_btn_child, R.id.frag_ask_btn_fix, R.id.frag_ask_btn_beautiful,
                R.id.frag_ask_btn_media, R.id.frag_ask_btn_heahty};

        mFragAskBtnHot.setOnClickListener(this);
        mFragAskBtnClothes.setOnClickListener(this);
        mFragAskBtnBeautiful.setOnClickListener(this);
        mFragAskBtnChild.setOnClickListener(this);
        mFragAskBtnFix.setOnClickListener(this);
        mFragAskBtnMedia.setOnClickListener(this);
        mFragAskBtnHeathy.setOnClickListener(this);
        mFragAskBtnBuy.setOnClickListener(this);

        presenter.initData();
    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 选中的按钮颜色改变
     */
    private void SelectChangeBtnColor(int position) {
        for (int i = 0; i < btns.length; i++) {
            if (i == position) {
                btns[i].setEnabled(false);
            } else {
                btns[i].setEnabled(true);
            }
        }
    }

        @Override
        public ViewPager getmFragAskVp () {
            return mActAskVpcontent;
        }

        @Override
        public FragmentManager getManager () {
            return getChildFragmentManager();
        }

        @Override
        public void setPresenter (FragAskContract.IFragPresenter presenter){
            this.presenter = presenter;
        }


        @Override
        public void onClick (View view){
            switch (view.getId())
            {
                case R.id.frag_ask_btn_hot:
                    SelectChangeBtnColor(0);
                    mActAskVpcontent.setCurrentItem(0);
                    break;
                case R.id.frag_ask_btn_clothes:
                    SelectChangeBtnColor(1);
                    mActAskVpcontent.setCurrentItem(1);
                    break;
                case R.id.frag_ask_btn_buy:
                    SelectChangeBtnColor(2);
                    mActAskVpcontent.setCurrentItem(2);
                    break;
                case R.id.frag_ask_btn_child:
                    SelectChangeBtnColor(3);
                    mActAskVpcontent.setCurrentItem(3);
                    break;
                case R.id.frag_ask_btn_fix:
                    SelectChangeBtnColor(4);
                    mActAskVpcontent.setCurrentItem(4);
                    break;
                case R.id.frag_ask_btn_beautiful:
                    SelectChangeBtnColor(5);
                    mActAskVpcontent.setCurrentItem(5);
                    break;
                case R.id.frag_ask_btn_media:
                    SelectChangeBtnColor(6);
                    mActAskVpcontent.setCurrentItem(6);
                    break;
                case R.id.frag_ask_btn_heahty:
                    SelectChangeBtnColor(7);
                    mActAskVpcontent.setCurrentItem(7);
                    break;
            }
        }

}
