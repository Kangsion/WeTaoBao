package com.example.ksion.wetaobao.presenter;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.ActGoodsTypeAdapter;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.example.ksion.wetaobao.contract.GoodTypeContract;
import com.example.ksion.wetaobao.gen.GoodsTypeDao;
import com.example.ksion.wetaobao.manager.GreenDaoManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/11.
 */

public class ActGoodsTypePresenterImpl  implements GoodTypeContract.GoodTypePresenter {
    private GoodTypeContract.GoodTypeView view;
    //数据源
    private List<GoodsType> goodsTypes;
    //
    private GridView gridView;
    private LinearLayout lnContent;

    GoodsTypeDao goodsTypeDao;



    public ActGoodsTypePresenterImpl(GoodTypeContract.GoodTypeView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        goodsTypeDao= GreenDaoManager.getInstance().getNewSession().getGoodsTypeDao();

        gridView=view.getmActGoodsTypeGv();
        lnContent=view.getmActGoodsTypeLnContent();

        goodsTypes=goodsTypeDao.loadAll();

        gridView.setAdapter(new ActGoodsTypeAdapter(goodsTypes,CustomApplcation.getInstance().context));
        initEvent();
    }

    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                GoodsType goodsTypeItem=goodsTypes.get(position);
                ActGoodsTypePresenterImpl.this.view.jumpActivity(goodsTypeItem.getTypeName());
            }
        });
    }
}
