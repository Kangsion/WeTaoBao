package com.example.ksion.wetaobao.presenter;

import android.widget.EditText;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.AddressContract;
import com.example.ksion.wetaobao.contract.UserNameContract;


import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Administrator on 2017/10/9.
 */

public class ActUserNamePresenterImpl implements UserNameContract.UserNamePresenter {
    private UserNameContract.UserNameView view;

    EditText mActAdressEdit;

    public ActUserNamePresenterImpl(UserNameContract.UserNameView view)
    {

        this.view=view;
        view.setPresenter(this);
    }


     @Override
    public void updateUserName() {
         final String username=mActAdressEdit.getText().toString().trim();
         User user = CustomApplcation.getInstance().getCurrentUser();
         user.setUserName(username);
         user.update(new UpdateListener() {
             @Override
             public void done(BmobException e) {
                if(e == null) {
                    CustomApplcation.getInstance().getCurrentUser().setUserName(username);
                    view.showMsg("修改成功");
                    view.jumpActivity();
                } else {
                    view.showMsg("修改失敗" +e.toString());
                }
             }
         });
    }

    @Override
    public void initData() {
        mActAdressEdit=view.getmActUserNameEdit();
        User user = CustomApplcation.getInstance().getCurrentUser();
        mActAdressEdit.setText(user.getUserName());
    }

}
