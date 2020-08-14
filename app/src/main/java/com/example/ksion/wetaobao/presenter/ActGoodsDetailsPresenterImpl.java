package com.example.ksion.wetaobao.presenter;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.DiscussXlvAdapter;
import com.example.ksion.wetaobao.adapter.GoodRollPageViewAdapter;
import com.example.ksion.wetaobao.bean.Collection;
import com.example.ksion.wetaobao.bean.Discuss;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.ShopCar;
import com.example.ksion.wetaobao.bean.User;
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
    private ListView mXlvPl;
    private Button mBtnShoucang;
    private GoodDetailsContract.GoodDetailsView view;
    private static final int LUNBO_NUM=2;
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
        mXlvPl=view.getmActGoodsDetailsXlv();
        mBtnShoucang=view.getmActGoodsDetailsBtnShoucang();

        //获取数据
        goods= (Goods) CustomApplcation.getDatas("goods",false);
        //初始化商品
        initGood();
        //初始化评论
        queryDiscuss(goods.getGoodId());
        if(QueryIsCollection()) {
            mBtnShoucang.setEnabled(false);
        } else {
            mBtnShoucang.setEnabled(true);
        }
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

    private void queryDiscuss(String goodId)
    {
        String sql="select * from Discuss where goodId='"+goodId+"'";
//        new BmobQuery<Discuss>().doSQLQuery(view.getContext(),sql, new SQLQueryListener<Discuss>() {
//            @Override
//            public void done(BmobQueryResult<Discuss> bmobQueryResult, BmobException e) {
//                   if(bmobQueryResult!=null)
//                   {
//                       mXlvPl.setAdapter(new DiscussXlvAdapter(bmobQueryResult.getResults(),
//                               view.getContext()));
//                       Log.e("tag",bmobQueryResult.getResults().size()+"");
//                   }
//            }
//        });
    }


    @Override
    public void queryUserSuccess() {

    }

    @Override
    public void joinShopCar() {
//         final String phone= CustomApplcation.getInstance().getCurrentUser().getPhone();
//         final String goodId=goods.getGoodId();
//         String sql="select * from ShopCar where phone='"+phone+"' and goodId='"+goodId+"'";
//         new BmobQuery<ShopCar>().doSQLQuery(view.getContext(),sql,new SQLQueryListener<ShopCar>(){
//             @Override
//             public void done(BmobQueryResult bmobQueryResult, BmobException e) {
//                 if(!bmobQueryResult.getResults().isEmpty()) {
//                     ShopCar shopCar= (ShopCar) bmobQueryResult.getResults().get(0);
//                     shopCar.increment("count",1);
////                     shopCar.update(view.getContext(), new UpdateListener() {
////                         @Override
////                         public void onSuccess() {
////                             view.showMsg("已成功加入购物车");
////                         }
////
////                         @Override
////                         public void onFailure(int i, String s) {
////                             view.showMsg("加入购物车失败"+s);
////                         }
////                     });
//                 } else {
//                     ShopCar shopCar = new ShopCar();
//                     shopCar.setPhone(phone);
//                     shopCar.setGoodId(goodId);
//                     shopCar.setCount(1);
//                     shopCar.save(view.getContext(), new SaveListener() {
//                         @Override
//                         public void onSuccess() {
//                             view.showMsg("已成功加入购物车");
//                         }
//
//                         @Override
//                         public void onFailure(int i, String s) {
//                             view.showMsg("加入购物车失败" + s);
//                         }
//                     });
//                 }
//             }
//         });

    }

    @Override
    public void shareWXAPP(int type) {

    }

    @Override
    public void GoodCollection() {
        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            String phone = CustomApplcation.getInstance().getCurrentUser().getPhone();
            Collection collection = new Collection();
            collection.setPhone(phone);
            collection.setGoodId(goods.getGoodId());
//            collection.save(view.getContext(), new SaveListener() {
//                @Override
//                public void onSuccess() {
//                    view.showMsg("收藏成功");
//                    mBtnShoucang.setEnabled(false);
//                }
//
//                @Override
//                public void onFailure(int i, String s) {
//                    view.showMsg("收藏失败" + s);
//                }
//            });
        } else {
            view.jumpActivity();
        }
    }
      public  boolean isCollection=false;
    public boolean QueryIsCollection() {
        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            String phone = CustomApplcation.getInstance().getCurrentUser().getPhone();
            final String goodId = goods.getGoodId();
            String sql = "select * from Collection where phone='"+phone+"'";
//            new BmobQuery<Collection>().doSQLQuery(view.getContext(), sql, new SQLQueryListener<Collection>() {
//                @Override
//                public void done(BmobQueryResult<Collection> bmobQueryResult, BmobException e) {
//                    if (bmobQueryResult != null) {
//                        for (int i = 0; i < bmobQueryResult.getResults().size(); i++) {
//                          if(bmobQueryResult.getResults().get(i).getGoodId().contains(goodId)) {
//                              isCollection=true;
//                          }
//                        }
//                    }
//                }
//            });
        }
        return isCollection;
    }
}
