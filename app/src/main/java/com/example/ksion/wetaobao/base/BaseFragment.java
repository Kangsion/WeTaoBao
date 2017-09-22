package com.example.ksion.wetaobao.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ksion.wetaobao.Application.CustomApplcation;

/**
 * Created by Ksion on 2017/9/4.
 */

public abstract class BaseFragment extends Fragment{
    //定义一个用于重复view 的回收池
    private View contentView = null;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(contentView==null)
        {
            contentView=initLayout(inflater,container,false);
        }
        if(contentView!=null)
        {
            return contentView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData(savedInstanceState);
    }
    /**
     * 初始化Fragment的布局,当要创建视图时调用
     *
     * @param inflater  布局填充器
     * @param container ViewGroup
     * @param b         标记
     * @return view 返回视图
     */
    public abstract View initLayout(LayoutInflater inflater, ViewGroup container, boolean b);

    /**
     * 初始化数据,当ViewCreate被创建是调用此方法
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    protected boolean isLogin() {
        if (CustomApplcation.getInstance().getCurrentUser() == null) {
            return false;
        }
        // CustomApplcation.getInstance().setCurrentUser(BmobUser.getCurrentUser(User.class));
        return true;
    }
    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    @Override
    public final void onDestroyView() {
        //移除当前视图，防止重复加载相同视图使得程序闪退
        ((ViewGroup) contentView.getParent()).removeView(contentView);
        super.onDestroyView();
    }
}
