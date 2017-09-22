package com.example.ksion.wetaobao.presenter;

import android.widget.Button;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.contract.GoodDetailsContract;
import com.example.ksion.wetaobao.widget.XListView;
import com.jude.rollviewpager.RollPagerView;

/**
 * Created by Ksion on 2017/9/11.
 */

public class ActGoodsDetailsPresenterImpl implements GoodDetailsContract.GoodDetailsPresenter {
    //定义控件
    private RollPagerView mRollVpAd;
    private TextView mTvGoodsName;
    private TextView mTvGoodsMoney;
    private XListView mXlvPl;
    private Button mBtnShoucang;
    private GoodDetailsContract.GoodDetailsView view;
    //商品
    private Goods goods;

    public  ActGoodsDetailsPresenterImpl(GoodDetailsContract.GoodDetailsView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
       //获取控件
        mRollVpAd=view.getmActGoodsDetailsRollVpAd();
        mTvGoodsMoney=view.getmActGoodsDetailsTvMoney();
        mTvGoodsName=view.getmActGoodsDetailsTvGoodsName();
       // mXlvPl=view.getmActGoodsDetailsXlv();
        mBtnShoucang=view.getmActGoodsDetailsBtnShoucang();

        //获取数据
        goods= (Goods) CustomApplcation.getDatas("goods",false);
        if(goods!=null) {
            mTvGoodsName.setText(goods.getGoodsName());
            mTvGoodsMoney.setText("￥" + goods.getGoodsPrice());
        }
    }

    @Override
    public void queryUserSuccess() {

    }

    @Override
    public void joinShopCar() {

    }

    @Override
    public void shareWXAPP(int type) {

    }


}
