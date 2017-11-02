package com.example.ksion.wetaobao.contract;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/6.
 */

public class FragAskContract  {
    public interface IFragAskView extends BaseView<IFragAskPresenter>{
        /**
         * 获取当前Fragment中的viewpager
         * @return
         */
        ViewPager getmFragAskVp();

        /**
         * 获取当前Fragment中的FragmetManager
         * @return
         */
        FragmentManager getManager();
    }
    public  interface IFragAskPresenter extends BasePresenter<IFragAskView>{}
}
