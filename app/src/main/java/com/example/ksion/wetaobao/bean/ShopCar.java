package com.example.ksion.wetaobao.bean;

import com.example.ksion.wetaobao.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ksion on 2017/9/13.
 */
@Entity
public class ShopCar {
    //商品id
    private String goodId;
    //当前用户
    @Id
    private String userId;
    //商品的数量
    private int count;
    @Generated(hash = 1164107597)
    public ShopCar(String goodId, String userId, int count) {
        this.goodId = goodId;
        this.userId = userId;
        this.count = count;
    }
    @Generated(hash = 1637372148)
    public ShopCar() {
    }
    public String getGoodId() {
        return this.goodId;
    }
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
