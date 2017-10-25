package com.example.ksion.wetaobao.bean;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by Ksion on 2017/9/12.
 */

public class Goods extends BmobObject {
    //商品id
    private String goodId;
    //商品名称
    private String goodsName;
    //商品类别id
    private String goodsTypeId;
    //商品的图片
    private BmobFile goodsImgs;
    //商品的价格
    private Number goodsPrice;
    //商品的发货地址
    private String goodsAddress;
    //商品简介
    private String goodsBrief;
    //月销量
    private Integer xiaoliang;

    public BmobFile getGoodsImgs() {
        return goodsImgs;
    }

    public void setGoodsImgs(BmobFile goodsImgs) {
        this.goodsImgs = goodsImgs;
    }

    public void setGoodsPrice(Number goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public Integer getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(Integer xiaoliang) {
        this.xiaoliang = xiaoliang;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }


    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }


    public Number getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsTypeId='" + goodsTypeId + '\'' +
                ", goodsImgs=" + goodsImgs +
                ", goodsPrice=" + goodsPrice +
                ", goodsAddress='" + goodsAddress + '\'' +
                '}';
    }
}
