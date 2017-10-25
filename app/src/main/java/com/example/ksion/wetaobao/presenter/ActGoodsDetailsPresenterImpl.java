package com.example.ksion.wetaobao.presenter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.DiscussXlvAdapter;
import com.example.ksion.wetaobao.adapter.GoodRollPageViewAdapter;
import com.example.ksion.wetaobao.bean.Discuss;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.contract.GoodDetailsContract;
import com.example.ksion.wetaobao.widget.XListView;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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
    private static final int LUNBO_NUM=2;
    private List<Discuss> mListDiscuss;
    //商品
    private Goods goods;

    private boolean isGood=false;

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
        mXlvPl=view.getmActGoodsDetailsXlv();
        mBtnShoucang=view.getmActGoodsDetailsBtnShoucang();

        //获取数据
        goods= (Goods) CustomApplcation.getDatas("goods",false);
        //初始化商品
        initGood();

        queryDiscuss(goods.getGoodId());



    }

    private void initGood() {
        List<String> imgs=new ArrayList<>();
        for (int i = 0; i < LUNBO_NUM; i++) {

            imgs.add(goods.getGoodsImgs().getUrl());
        }

        mRollVpAd.setAdapter(new GoodRollPageViewAdapter(view.getContext(),imgs));

        if(goods!=null) {
            mTvGoodsName.setText(goods.getGoodsName());
            mTvGoodsMoney.setText("￥" + goods.getGoodsPrice());
        }
    }

    private void shouCang(String goodId) {
    }

    private void queryDiscuss(String goodId)
    {
        String sql="select * from  Discuss  where goodId='"+goodId+"'";
        new BmobQuery<Discuss>().doSQLQuery(view.getContext(),sql, new SQLQueryListener<Discuss>() {
            @Override
            public void done(BmobQueryResult<Discuss> bmobQueryResult, BmobException e) {
                   if(!bmobQueryResult.getResults().isEmpty())
                   {
                       mListDiscuss=bmobQueryResult.getResults();
                   }
            }
        });
        if(mListDiscuss!=null)
        {
            mXlvPl.setAdapter(new DiscussXlvAdapter(mListDiscuss,view.getContext(),this));
        }
    }


    @Override
    public void queryUserSuccess() {

    }

    @Override
    public void joinShopCar() {
         String phone= CustomApplcation.getInstance().getCurrentUser().getPhone();
         String goodId=goods.getGoodId();
         String sql="select * from ShopCar where phone='"+phone+"' and goodId='"+goodId+"'";
         new BmobQuery<ShopCar>().doSQLQuery(view.getContext(),sql,new SQLQueryListener<ShopCar>(){
             @Override
             public void done(BmobQueryResult bmobQueryResult, BmobException e) {
                 if(!bmobQueryResult.getResults().isEmpty()) {
                     isGood=true;
                     ShopCar shopCar= (ShopCar) bmobQueryResult.getResults().get(0);
                     shopCar.increment("count",1);
                     shopCar.update(view.getContext(), new UpdateListener() {
                         @Override
                         public void onSuccess() {
                             view.showMsg("已成功加入购物车");
                         }

                         @Override
                         public void onFailure(int i, String s) {

                         }
                     });
                 }
             }
         });

         if(!isGood) {
             ShopCar shopCar = new ShopCar();
             shopCar.setPhone(phone);
             shopCar.setGoodId(goodId);
             shopCar.setCount(1);
             shopCar.save(view.getContext(), new SaveListener() {
                 @Override
                 public void onSuccess() {
                     view.showMsg("已成功加入购物车");
                 }

                 @Override
                 public void onFailure(int i, String s) {

                 }
             });
         }

    }

    @Override
    public void shareWXAPP(int type) {

    }


}
