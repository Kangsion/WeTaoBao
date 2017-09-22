package com.example.ksion.wetaobao.bean;

import android.widget.ImageView;

import com.example.ksion.wetaobao.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Ksion on 2017/9/12.
 */
@Entity
public class Goods {
    //商品名称
    @NotNull
    private String goodsName;
    //商品类别id
    @Property(nameInDb = "GOODS_TYPE_ID")
    private String goodsTypeId;
    //商品的图片
    private int  goodsImgs;
    //商品的价格
    private double goodsPrice;
    //商品的发货地址
    private String goodsAddress;
    //收藏商品的人
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> loveUserIds;
    @Generated(hash = 1463531467)
    public Goods(@NotNull String goodsName, String goodsTypeId, int goodsImgs,
            double goodsPrice, String goodsAddress, List<String> loveUserIds) {
        this.goodsName = goodsName;
        this.goodsTypeId = goodsTypeId;
        this.goodsImgs = goodsImgs;
        this.goodsPrice = goodsPrice;
        this.goodsAddress = goodsAddress;
        this.loveUserIds = loveUserIds;
    }
    @Generated(hash = 1770709345)
    public Goods() {
    }
    public String getGoodsName() {
        return this.goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsTypeId() {
        return this.goodsTypeId;
    }
    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
    public int getGoodsImgs() {
        return this.goodsImgs;
    }
    public void setGoodsImgs(int goodsImgs) {
        this.goodsImgs = goodsImgs;
    }
    public double getGoodsPrice() {
        return this.goodsPrice;
    }
    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public String getGoodsAddress() {
        return this.goodsAddress;
    }
    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }
    public List<String> getLoveUserIds() {
        return this.loveUserIds;
    }
    public void setLoveUserIds(List<String> loveUserIds) {
        this.loveUserIds = loveUserIds;
    }





}
