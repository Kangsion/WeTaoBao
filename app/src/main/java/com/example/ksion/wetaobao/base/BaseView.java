package com.example.ksion.wetaobao.base;

/**
 * Created by Ksion on 2017/9/4.
 */

public interface BaseView <T extends BasePresenter>{
    /**
     * 为视图设置控制层
     * @param presenter
     */

     void setPresenter(T presenter);

}
