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
         final String address=mActAdressEdit.getText().toString().trim();
         String UserId=CustomApplcation.getInstance().getCurrentUser().getObjectId();
         User user=new User();
         user.setAddress(address);
//         user.update(view.getContext(), UserId, new UpdateListener() {
//             @Override
//             public void onSuccess() {
//                 CustomApplcation.getInstance().getCurrentUser().setAddress(address);
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
        mActAdressEdit=view.getmActAddressEdit();

        String phone=CustomApplcation.getInstance().getCurrentUser().getPhone();
        String sql="select * from User where phone='"+phone+"'";
//        new BmobQuery<User>().doSQLQuery(view.getContext(), sql, new SQLQueryListener<User>() {
//            @Override
//            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
//                if(bmobQueryResult!=null) {
//                    mActAdressEdit.setText(bmobQueryResult.getResults().get(0).getAddress());
//
//                }
//
//            }
//        });
    }

}
