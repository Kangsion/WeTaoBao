package com.example.ksion.wetaobao.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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
    public void onActivityCreated(Bundle savedInstanceState) {
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
    protected abstract void initData(Bundle savedInstanceState);

    protected boolean isLogin() {
        if (CustomApplcation.getInstance().getCurrentUser() == null) {
            return false;
        }
        return true;
    }
    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;

        } else {
            isVisible = false;
            onInvisible();
        }
    }




    /**
     * 不可见
     */
    protected void onInvisible() {

    }




    @Override
    public final void onDestroyView() {
        //移除当前视图，防止重复加载相同视图使得程序闪退
        ((ViewGroup) contentView.getParent()).removeView(contentView);
        super.onDestroyView();
    }

    /**
     *  功能 ：显示一个警告对话框
     */
    protected void showAlertDialog(String title,String text){
        if (builder==null){
            //创建一个构建者对象
            builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title).setMessage(text).setCancelable(false);
            builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //跳转到系统网络设置
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //退出虚拟机
                    System.exit(0);
                }
            });
        }
        alertDialog = builder.show();
    }

    /**
     * 功能:取消警告对话框
     */
    protected void dismissAlertDialog(){
        if (alertDialog!=null){
            //取消警告对话框
            alertDialog.dismiss();
        }
    }
    /**
     * 功能 ：显示一个进度条对话框
     */
    protected void showProcessDialog(String title,String msg,boolean falg){
        if(dialog==null){
            dialog = new ProgressDialog(getContext());
        }
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(falg);
        dialog.show();
    }

    /**
     * 功能 ：取消一个进度条对话框
     */
    protected void dismissProcessDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
}
