package com.example.ksion.wetaobao.bean;

import com.example.ksion.wetaobao.gen.UserDao;
import com.example.ksion.wetaobao.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ksion on 2017/9/5.
 */
@Entity
public class User {
    //用户ID

    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private Long UserId;
    //昵称
    @Unique
    private String nickName;
    //头像
    private String userHead;
    //性别
    private String sex;
    //收货地址的集合
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> addressLists;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    @Generated(hash = 1391994525)
    public User(Long UserId, String nickName, String userHead, String sex,
            List<String> addressLists, @NotNull String password,
            @NotNull String phone) {
        this.UserId = UserId;
        this.nickName = nickName;
        this.userHead = userHead;
        this.sex = sex;
        this.addressLists = addressLists;
        this.password = password;
        this.phone = phone;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUserId() {
        return this.UserId;
    }
    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getUserHead() {
        return this.userHead;
    }
    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public List<String> getAddressLists() {
        return this.addressLists;
    }
    public void setAddressLists(List<String> addressLists) {
        this.addressLists = addressLists;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}
