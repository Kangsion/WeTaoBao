package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/11/20.
 */

public class UserNameContract {
    public interface UserNameView extends BaseView<UserNameContract.UserNamePresenter>
    {
        /**
         * Toast数据
         * @param msg
         */
        void showMsg(String msg);

        Context getContext();

        /**
         * activity的跳转
         */
        void jumpActivity();

        EditText getmActUserNameEdit();

    }

    public interface UserNamePresenter extends BasePresenter<UserNameContract.UserNameView> {

        void updateUserName();
    }
}
