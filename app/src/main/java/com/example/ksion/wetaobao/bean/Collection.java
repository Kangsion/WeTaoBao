package com.example.ksion.wetaobao.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Ksion on 2017/11/6.
 */

public class Collection extends BmobObject{
    private String phone;
    private String goodId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
}
