package com.example.ksion.wetaobao.contract;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.GridView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;
import com.jude.rollviewpager.RollPagerView;
import com.sunfusheng.marqueeview.MarqueeView;

/**
 * Created by Ksion on 2017/9/4.
 */

public  class IhomeContract {
    public interface IHomeView extends BaseView<IHomePresenter>{

        /**
         * 获取显示内容的Viewpager
         * @return viewpager 对象
         */
        ViewPager getmActHomeVpContent();

        /**
         * 获取当前activity的Fragment管理器
         * @return  Fragment管理器
         */
        FragmentManager getManager();

        GridView getGridViewSort();

        GridView getGridViewContent();

        MarqueeView getMarqueeViewTop();

        GridView getGridViewGoodList();

        /**
         * 获取显示广告的ViewPager
         *
         * @return viewpager 对象
         */
        RollPagerView getmActHomeVpAd();

        /**
         * 跳转Activity
         *
         * @param type 类型 1-9
         */
        void jumpActivity(int type, String datas);

        EditText getmFragHomeEtSearch();
    }
    public interface IHomePresenter extends BasePresenter<IHomeView> {

    }
}
