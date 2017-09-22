package com.example.ksion.wetaobao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ksion on 2017/9/13.
 */
@Entity
public class Discuss {
    //商品id
    private String goodsId;
    //评论内容
    private String discussText;
    //评论的人
    private String discussUserId;
    //评论时间
    private String discussTime;
    @Generated(hash = 1227945685)
    public Discuss(String goodsId, String discussText, String discussUserId,
            String discussTime) {
        this.goodsId = goodsId;
        this.discussText = discussText;
        this.discussUserId = discussUserId;
        this.discussTime = discussTime;
    }
    @Generated(hash = 1577192031)
    public Discuss() {
    }
    public String getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getDiscussText() {
        return this.discussText;
    }
    public void setDiscussText(String discussText) {
        this.discussText = discussText;
    }
    public String getDiscussUserId() {
        return this.discussUserId;
    }
    public void setDiscussUserId(String discussUserId) {
        this.discussUserId = discussUserId;
    }
    public String getDiscussTime() {
        return this.discussTime;
    }
    public void setDiscussTime(String discussTime) {
        this.discussTime = discussTime;
    }
}
