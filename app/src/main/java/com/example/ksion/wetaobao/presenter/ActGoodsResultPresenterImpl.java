package com.example.ksion.wetaobao.presenter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.ActGoodsResultAdapter;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.example.ksion.wetaobao.contract.GoodDetailsContract;
import com.example.ksion.wetaobao.contract.GoodResultContract;
import com.example.ksion.wetaobao.gen.GoodsDao;
import com.example.ksion.wetaobao.gen.GoodsTypeDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/11.
 */

public class ActGoodsResultPresenterImpl implements GoodResultContract.GoodResultPresenter {
    private GoodResultContract.GoodResultView view;
    private GridView gridView;

    List<Goods> goodsList;

    GoodsDao goodsDao;

    //商品类别ID
    private String typename;

    public ActGoodsResultPresenterImpl(GoodResultContract.GoodResultView view,String typename)
    {
        this.view=view;
        this.typename=typename;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        gridView=view.getmActGoodsResultGv();

        goodsDao= GreenDaoManager.getInstance().getNewSession().getGoodsDao();

        goodsList=goodsDao.queryBuilder().where(GoodsDao.Properties.GoodsTypeId.eq(typename)).list();
        gridView.setAdapter(new ActGoodsResultAdapter(goodsList,CustomApplcation.getInstance().context));

        initEvent();
    }

    /**
     * 初始化点击事件
     */
    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将选中的活动保存到内存中
                CustomApplcation.putDatas("goods",goodsList.get(position));
                //跳转到商品详情页面
                ActGoodsResultPresenterImpl.this.view.jumpActivity();
            }
        });
    }
}
