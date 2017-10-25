package com.example.ksion.wetaobao.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Ksion on 2017/9/13.
 */

public class Discuss extends BmobObject{
    //商品id
    private String goodId;
    //评论内容
    private String discussText;
    //评论的人
    private String phone;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getDiscussText() {
        return discussText;
    }

    public void setDiscussText(String discussText) {
        this.discussText = discussText;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
