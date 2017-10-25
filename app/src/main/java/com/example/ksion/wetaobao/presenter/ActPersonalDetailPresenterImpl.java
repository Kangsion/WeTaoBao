package com.example.ksion.wetaobao.presenter;

import android.net.Uri;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.PersonalDetailContract;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ksion on 2017/9/17.
 */

public class ActPersonalDetailPresenterImpl implements PersonalDetailContract.PersonalPresenter {

    private PersonalDetailContract.PersonalDetailView view;
    private CircleImageView circleImageViewHead;
    private TextView mTvHuiyunName, mTvNickName, mTvSex;


    @Override
    public void initData() {
        circleImageViewHead=view.getmActPersonDetailsIvHead();
        mTvHuiyunName=view.getmActPersonDetailsTvHuiyanName();
        mTvNickName=view.getmActPersonDetailsTvNickName();
        mTvSex=view.getmActPersonDetailsTvSex();

        /*if(findUser!=null)
        {
            circleImageViewHead.setImageResource(R.drawable.app_ico);
            mTvHuiyunName.setText(findUser.getUserHead());
            mTvHuiyunName.setText(findUser.getNickName());
            mTvSex.setText(findUser.getSex());
        }*/

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
    public void updateUserInfo(String text, int type) {

    }


}
