package com.example.ksion.wetaobao.presenter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.AddressContract;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ActAdressPresenterImpl implements AddressContract.AddressPresenter {
    private AddressContract.AddressView view;

    EditText mActAdressEdit;

    public ActAdressPresenterImpl(AddressContract.AddressView view)
    {

        this.view=view;
        view.setPresenter(this);
    }


     @Override
    public void updateAddress() {
         final String address = mActAdressEdit.getText().toString().trim();
         String UserId = CustomApplcation.getInstance().getCurrentUser().getObjectId();
         User user = new User();
         user.setAddress(address);
         user.update(UserId, new UpdateListener() {
             @Override
             public void done(BmobException e) {
                 if(e == null) {
                     CustomApplcation.getInstance().getCurrentUser().setAddress(address);
                     view.showMsg("修改成功");
                     view.jumpActivity();
                 } else {
                     view.showMsg("修改失败");
                 }
             }
         });
    }

    @Override
    public void initData() {
        mActAdressEdit=view.getmActAddressEdit();
        BmobQuery<User> query = new BmobQuery<User>();
        String phone=CustomApplcation.getInstance().getCurrentUser().getPhone();
        query.getObject(phone, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user != null) {
                  mActAdressEdit.setText(user.getAddress());
                }
            }
        });
    }

}
