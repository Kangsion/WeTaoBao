package com.example.ksion.wetaobao.bean;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by Ksion on 2017/9/5.
 */

public class User  extends BmobUser{
    //手机号
    private String phone;
    //密码
    private String pwd;
    //昵称
    private String UserName;
    //头像
    private BmobFile userHead;
    //性别
    private String sex;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    //收货地址
    private String address;
    /**
     * 收藏的商品的Id
     */
    private List<String> loveGoodsIds;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public BmobFile getUserHead() {
        if (userHead == null) {
            userHead = new BmobFile();
        }
        return userHead;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserHead(BmobFile userHead) {
        this.userHead = userHead;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public List<String> getLoveGoodsIds() {
        if (loveGoodsIds ==null){
            loveGoodsIds = new ArrayList<>();
        }
        return loveGoodsIds;
    }

    public void setLoveGoodsIds(List<String> loveGoodsIds) {
        this.loveGoodsIds = loveGoodsIds;
    }
}
