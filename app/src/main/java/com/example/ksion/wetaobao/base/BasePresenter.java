package com.example.ksion.wetaobao.base;

/**
 * Created by Ksion on 2017/9/4.
 */

public interface BasePresenter <T extends BaseView> {
       String Tag="myTag";

        void initData();

}
