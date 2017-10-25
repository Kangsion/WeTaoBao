package com.example.ksion.wetaobao.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.ActGoodsResultAdapter;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.example.ksion.wetaobao.contract.GoodResultContract;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Ksion on 2017/9/11.
 */

public class ActGoodsResultPresenterImpl implements GoodResultContract.GoodResultPresenter {
    private GoodResultContract.GoodResultView view;
    private GridView gridView;

    List<Goods> goodsList;

    //商品类别ID
    private String typeId;

    public ActGoodsResultPresenterImpl(GoodResultContract.GoodResultView view,String typeId)
    {
        this.view=view;
        this.typeId=typeId;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        gridView=view.getmActGoodsResultGv();
        String sql="select * from Goods where goodsTypeId='"+typeId+"'";
        new BmobQuery<Goods>().doSQLQuery(view.getContext(),sql, new SQLQueryListener<Goods>() {
            @Override
            public void done(BmobQueryResult<Goods> bmobQueryResult, BmobException e) {
                if(!bmobQueryResult.getResults().isEmpty()) {
                    goodsList = bmobQueryResult.getResults();
                    gridView.setAdapter(new ActGoodsResultAdapter(goodsList, CustomApplcation.getInstance().context));
                    initEvent();
                }
            }
        });

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
