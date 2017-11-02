package com.example.ksion.wetaobao.bean;



import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Ksion on 2017/9/13.
 */

public class Order  extends BmobObject{
    //订单Id
    private String orderId;
    //商品id的集合
    private String goodId;
    //用户id
    private String phone;
    //订单状态 0为待付款 1为待发货 2为待收货3位取消订单
    private Integer ordersState;
    //订单的金额
    private Number ordersMoney;
    //订单商品的数量
    private Integer goodsCount;

    public String getGoodId() {
        return goodId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(int ordersState) {
        this.ordersState = ordersState;
    }

    public Number getOerdersMoney() {
        return ordersMoney;
    }

    public void setOerdersMoney(Number oerdersMoney) {
        this.ordersMoney = oerdersMoney;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
