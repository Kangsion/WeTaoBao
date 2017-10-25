package com.example.ksion.wetaobao.bean;

import com.example.ksion.wetaobao.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

import cn.bmob.v3.BmobObject;

/**
 * Created by Ksion on 2017/9/13.
 */

public class ShopCar extends BmobObject{
    //商品id
    private String goodId;
    //当前用户
    private String phone;
    //商品的数量
    private int count;

    public String getGoodId() {
        return goodId;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
