package com.example.ksion.wetaobao.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.User;

/**
 * Created by Ksion on 2017/9/4.
 */

public class BaseActivity extends FragmentActivity {

    //声明一个构建着对象，用于创建警告对话框
    private AlertDialog.Builder builder;
    //用于创建一个进度条对话框
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //屏幕方向固定
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        //设置在Activity启动时输入法默认不启动
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
    /**
     *  功能：实现沉浸式通知栏，使通知栏和APP的标题颜色一样
     */
    protected void immersiveNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    protected boolean isLogin() {
        if (CustomApplcation.getInstance().getCurrentUser() == null) {
            return false;
        }
       // CustomApplcation.getInstance().setCurrentUser(BmobUser.getCurrentUser(User.class));
        return true;
    }

    /**
     * 功能：显示一个警告对话框,无按钮，需要自己设置
     * @param title  标题
     * @param msg 内容
     * @param flag 是否可以取消
     * @return AlertDialog.Builder 对象
     */
    protected AlertDialog.Builder showAlertDialog(String title, String msg, boolean flag){
        if (builder==null){
            //创建一个构建者对象
            builder = new AlertDialog.Builder(this);
            builder.setTitle(title).setMessage(msg).setCancelable(flag);
        }
        return builder;
    }

    /**
     * 功能:取消警告对话框
     */
    protected void dismissAlertDialog(AlertDialog alertDialog){
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
            dialog = new ProgressDialog(this);
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
