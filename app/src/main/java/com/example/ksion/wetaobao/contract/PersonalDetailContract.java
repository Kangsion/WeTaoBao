package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ksion on 2017/9/17.
 */

public class PersonalDetailContract {
    public interface PersonalDetailView extends BaseView<PersonalPresenter>{
        CircleImageView getmActPersonDetailsIvHead();

        TextView getmActPersonDetailsTvHuiyanName();

        TextView getmActPersonDetailsTvNickName();

        TextView getmActPersonDetailsTvSex();
        /**
         * Toast数据
         * @param msg
         */
        void showMsg(String msg);

        /**
         * 展示一个进度条对话框
         * @param title 标题
         * @param msg 显示的内容
         * @param flag 是否可以取消
         */
        void showLoadingDialog(String title, String msg, boolean flag);

        Context getContext();


        /**
         * 取消进度条
         */
        void canelLoadingDialog();

        TextView getmActPersonDetailsTvAdress();

        /**
         * activity的跳转
         */
        void jumpActivity();
    }
    public interface PersonalPresenter extends BasePresenter<PersonalDetailView>{
        /**
         * 上传头像
         * @param uri
         */
        void upload(Uri uri);


        void updateUserSex(String text);


    }
}
