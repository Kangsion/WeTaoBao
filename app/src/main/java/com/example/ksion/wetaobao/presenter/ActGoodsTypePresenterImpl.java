package com.example.ksion.wetaobao.presenter;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.ksion.wetaobao.Application.CustomApplcation;

import com.example.ksion.wetaobao.adapter.ActGoodsTypeAdapter;
import com.example.ksion.wetaobao.bean.GoodsType;
import com.example.ksion.wetaobao.contract.GoodTypeContract;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

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


    public ActGoodsTypePresenterImpl(GoodTypeContract.GoodTypeView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        goodsTypes=new ArrayList<>();
        gridView=view.getmActGoodsTypeGv();
        lnContent=view.getmActGoodsTypeLnContent();
        BmobQuery<GoodsType> query=new BmobQuery<>();
//        query.findObjects(view.getContext(), new FindListener<GoodsType>() {
//            @Override
//            public void onSuccess(List<GoodsType> list) {
//                if(!list.isEmpty()) {
//                    view.showLoadingDialog("","数据加载中",true);
//                    goodsTypes=list;
//                    gridView.setAdapter(new ActGoodsTypeAdapter(list, CustomApplcation.getInstance().context));
//                    view.canelLoadingDialog();
//                }
//            }
//            @Override
//            public void onError(int i, String s) {
//                 view.showMsg("数据加载失败。。");
//            }
//        });
        initEvent();
    }

    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                GoodsType goodsTypeItem=goodsTypes.get(position);
                ActGoodsTypePresenterImpl.this.view.jumpActivity(goodsTypeItem.getGoodsTypeId());
            }
        });
    }
}
