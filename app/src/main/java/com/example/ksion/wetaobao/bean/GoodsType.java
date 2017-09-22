package com.example.ksion.wetaobao.bean;

import android.support.annotation.IntDef;
import android.widget.ImageView;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ksion on 2017/9/11.
 */
@Entity
public class GoodsType {
    //商品类别的名称
    @Id
    private String typeName;
    //商品类别的图片
    private Integer typeImg;
    @Generated(hash = 699409933)
    public GoodsType(String typeName, Integer typeImg) {
        this.typeName = typeName;
        this.typeImg = typeImg;
    }
    @Generated(hash = 1568965165)
    public GoodsType() {
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getTypeImg() {
        return this.typeImg;
    }
    public void setTypeImg(Integer typeImg) {
        this.typeImg = typeImg;
    }


}
