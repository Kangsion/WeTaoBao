package com.example.ksion.wetaobao.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.ActGoodsResultAdapter;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.example.ksion.wetaobao.contract.GoodResultContract;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
        gridView = view.getmActGoodsResultGv();
        BmobQuery<Goods> query = new BmobQuery<>();
        query.addWhereEqualTo("goodsTypeId", typeId);
        query.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                System.out.println("goodResult:" + list);
                if(e == null) {
                    goodsList = list;
                    gridView.setAdapter(new ActGoodsResultAdapter(list, CustomApplcation.getInstance().context));
                    initEvent();
                } else {
                    Toast.makeText(view.getContext(), "商品：" + e, Toast.LENGTH_SHORT).show();
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
