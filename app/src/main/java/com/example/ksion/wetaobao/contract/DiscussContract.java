package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;
import com.example.ksion.wetaobao.bean.Discuss;

/**
 * Created by Ksion on 2017/11/6.
 */

public class DiscussContract {
    public interface DiscussView extends BaseView<DiscussPresenter> {

        EditText getmEdDiscussContent();


        Context getContext();

        String getGoodId();

    }
    public interface DiscussPresenter extends BasePresenter<DiscussView> {
        void addDiscuss();
    }
}
