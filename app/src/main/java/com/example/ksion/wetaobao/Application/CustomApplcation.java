package com.example.ksion.wetaobao.Application;

import android.app.Application;
import android.content.Context;

import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.config.Contracts;


import java.util.HashMap;
import java.util.Map;

import c.b.BP;
import cn.bmob.v3.Bmob;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.update.BmobUpdateAgent;


/**
 * 自定义的全部的Applcation类
 * Created by 11655 on 2016/10/18.
 */

public class CustomApplcation extends Application {

    //一个标记
    public static String TAG;
    //当前的用户
    private static User currentUser;

    /**
     * 维护一个全局的context对象
     */
     public static Context context;
    //用于存放数据
    private static Map<String,Object> datas = new HashMap<String, Object>();
    private static CustomApplcation customApplcation = null;

    public static CustomApplcation getInstance() {
        return customApplcation;
    }
    public static Context getContext()
    {
        return context;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    /**
     * 获取当前的用户对象
     *
     * @return
     */
    public  User getCurrentUser() {
        User user = currentUser;
        if (user != null) {
            return user;
        }
        return null;
    }

    public static Object getDatas(String key, boolean delFlag) {
        if (delFlag) {
            return datas.remove(key);
        }
        return datas.get(key);
    }

    public static Object putDatas(String key, Object value) {
        return datas.put(key, value);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        //由于Application类本身已经单例，所以直接按以下处理即可。
        customApplcation = this;
        context = getApplicationContext();
         //初始化BMob 短信服务SDK
        //BmobSMS.querySmsState(context, Contracts.BMOB_APP_KEY);
        //初始化BMob 数据SDK
        Bmob.initialize(this,Contracts.BMOB_APP_KEY);
        //初始化Bmob 支付SDK
        BP.init(context,Contracts.BMOB_APP_KEY);
        //Bmob自动更新
        BmobUpdateAgent.initAppVersion();
    }
}
