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
         String UserId=CustomApplcation.getInstance().getCurrentUser().getObjectId();
         User user=new User();
         user.setUserName(username);
//         user.update(view.getContext(), UserId, new UpdateListener() {
//             @Override
//             public void onSuccess() {
//                 CustomApplcation.getInstance().getCurrentUser().setUserName(username);
//                 view.showMsg("修改成功");
//                 view.jumpActivity();
//             }
//
//             @Override
//             public void onFailure(int i, String s) {
//                 view.showMsg(s);
//             }
//         });
    }

    @Override
    public void initData() {
        mActAdressEdit=view.getmActUserNameEdit();

        String phone=CustomApplcation.getInstance().getCurrentUser().getPhone();
        String sql="select * from User where phone='"+phone+"'";
//        new BmobQuery<User>().doSQLQuery(view.getContext(), sql, new SQLQueryListener<User>() {
//            @Override
//            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
//                if(bmobQueryResult!=null) {
//                    mActAdressEdit.setText(bmobQueryResult.getResults().get(0).getUserName());
//
//                }
//
//            }
//        });
    }

}
