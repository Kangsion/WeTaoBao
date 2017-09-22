package com.example.ksion.wetaobao.contract;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/7.
 */

public class FragWeContract  {
    public interface FragWeView extends BaseView<FragWePresenter> {
        /**
         * 获取当前Fragment中的viewpager
         * @return
         */
        ViewPager getmFragWeVp();

        /**
         * 获取当前Fragment中的FragmetManager
         * @return
         */
        FragmentManager getManager();

    };

    public interface FragWePresenter extends BasePresenter<FragWeView> {};
}
