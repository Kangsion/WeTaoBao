package com.example.ksion.wetaobao.presenter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.Discuss;
import com.example.ksion.wetaobao.contract.DiscussContract;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Ksion on 2017/11/6.
 */

public class ActDiscussPresenterImpl implements DiscussContract.DiscussPresenter{

    EditText mEdDiscussContent;
    DiscussContract.DiscussView view;

    public ActDiscussPresenterImpl(DiscussContract.DiscussView view) {
         this.view=view;
         view.setPresenter(this);
    }

    @Override
    public void addDiscuss() {
        String Content=mEdDiscussContent.getText().toString().trim();
        String goodId=view.getGoodId();
        String phone= CustomApplcation.getInstance().getCurrentUser().getPhone();
        if(!Content.isEmpty()) {
            Discuss discuss = new Discuss();
            discuss.setPhone(phone);
            discuss.setDiscussText(Content);
            discuss.setGoodId(goodId);
//            discuss.save(view.getContext(), new SaveListener() {
//                @Override
//                public void onSuccess() {
//                    Toast.makeText(view.getContext(), "发表评论成功", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(int i, String s) {
//                    Toast.makeText(view.getContext(), "发表评论失败" + s, Toast.LENGTH_SHORT).show();
//                }
//            });
        } else {
            Toast.makeText(view.getContext(), "内容不能为空", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void initData() {
        mEdDiscussContent=view.getmEdDiscussContent();
    }
}
