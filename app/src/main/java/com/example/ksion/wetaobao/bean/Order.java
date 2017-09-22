package com.example.ksion.wetaobao.bean;

import com.example.ksion.wetaobao.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ksion on 2017/9/13.
 */
@Entity
public class Order {
    //订单编号
    @Id
    private String OrderId;
    //商品id的集合
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> goodIds;
    //用户id
    @NotNull
    private String userId;
    //订单状态 0为待付款 1为待发货 2为待收货3位取消订单
    private int ordersState;
    //订单的金额
    private double oerdersMoney;
    //订单商品的数量
    private int goodsCount;
    @Generated(hash = 582971988)
    public Order(String OrderId, List<String> goodIds, @NotNull String userId,
            int ordersState, double oerdersMoney, int goodsCount) {
        this.OrderId = OrderId;
        this.goodIds = goodIds;
        this.userId = userId;
        this.ordersState = ordersState;
        this.oerdersMoney = oerdersMoney;
        this.goodsCount = goodsCount;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public String getOrderId() {
        return this.OrderId;
    }
    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }
    public List<String> getGoodIds() {
        return this.goodIds;
    }
    public void setGoodIds(List<String> goodIds) {
        this.goodIds = goodIds;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getOrdersState() {
        return this.ordersState;
    }
    public void setOrdersState(int ordersState) {
        this.ordersState = ordersState;
    }
    public double getOerdersMoney() {
        return this.oerdersMoney;
    }
    public void setOerdersMoney(double oerdersMoney) {
        this.oerdersMoney = oerdersMoney;
    }
    public int getGoodsCount() {
        return this.goodsCount;
    }
    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
