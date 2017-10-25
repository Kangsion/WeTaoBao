package com.example.ksion.wetaobao.bean;



import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodsType extends BmobObject{
    //商品类别ID
    private String goodsTypeId;
    //商品类别的名称
    private String goodsTypeName;
    //商品类别的图片
    private BmobFile goodsTypeImg;

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public BmobFile getGoodsTypeImg() {
        return goodsTypeImg;
    }

    public void setGoodsTypeImg(BmobFile goodsTypeImg) {
        this.goodsTypeImg = goodsTypeImg;
    }
}
