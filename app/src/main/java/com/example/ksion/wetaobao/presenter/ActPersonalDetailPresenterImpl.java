package com.example.ksion.wetaobao.presenter;

import android.net.Uri;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.PersonalDetailContract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ksion on 2017/9/17.
 */

public class ActPersonalDetailPresenterImpl implements PersonalDetailContract.PersonalPresenter {

    private PersonalDetailContract.PersonalDetailView view;
    private CircleImageView circleImageViewHead;
    private TextView mTvHuiyunName, mTvNickName, mTvSex;
    private TextView mActPersonDetailsTvAdress;

    @Override
    public void initData() {

        circleImageViewHead=view.getmActPersonDetailsIvHead();
        mTvHuiyunName=view.getmActPersonDetailsTvHuiyanName();
        mTvNickName=view.getmActPersonDetailsTvNickName();
        mTvSex=view.getmActPersonDetailsTvSex();
        mActPersonDetailsTvAdress=view.getmActPersonDetailsTvAdress();

        if(CustomApplcation.getInstance().getCurrentUser()!=null) {
            User user=CustomApplcation.getInstance().getCurrentUser();

            Picasso.with(view.getContext()).load(user.getUserHead().getUrl()).into(circleImageViewHead);
            mTvHuiyunName.setText(user.getPhone());
            mTvNickName.setText(user.getUserName());
            mTvSex.setText(user.getSex());
            mActPersonDetailsTvAdress.setText(user.getAddress());
        }
    }

    public ActPersonalDetailPresenterImpl(PersonalDetailContract.PersonalDetailView view)
    {
          this.view=view;
          view.setPresenter(this);
    }

    @Override
    public void upload(Uri uri) {

    }

    @Override
    public void updateUserSex(final String text) {
        String UserId=CustomApplcation.getInstance().getCurrentUser().getObjectId();
        User user=new User();
        user.setSex(text);
//        user.update(view.getContext(), UserId, new UpdateListener() {
//            @Override
//            public void onSuccess() {
//                CustomApplcation.getInstance().getCurrentUser().setSex(text);
//                view.showMsg("修改成功");
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                view.showMsg(s);
//            }
//        });
    }




}
