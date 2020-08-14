package com.example.ksion.wetaobao.contract;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/11/9.
 */

public class ActHomeContract  {
    public interface ActHomeView extends BaseView<ActHomePresenter> {
         ViewPager getmActHomeVpContent();

         FragmentManager getManager();
    }
    public interface ActHomePresenter extends BasePresenter<ActHomeView> {

    }
}
