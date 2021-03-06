package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.DiscussContract;
import com.example.ksion.wetaobao.presenter.ActDiscussPresenterImpl;


/**
 * Created by Ksion on 2017/11/6.
 */

public class DiscussActivity extends BaseActivity implements DiscussContract.DiscussView,
        View.OnClickListener{

    ImageView mIvDiscussBack;
    EditText  mEdDiscussContent;
    TextView  mTvDiscussAdd;
    DiscussContract.DiscussPresenter presenter;

    String goodId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_discuss);
        initViews();
        goodId=getIntent().getStringExtra("goodId");
        new ActDiscussPresenterImpl(this);
        presenter.initData();
    }

    private void initViews() {
        mIvDiscussBack= (ImageView) findViewById(R.id.act_discuss_iv_back);
        mEdDiscussContent= (EditText) findViewById(R.id.act_discuss_content);
        mTvDiscussAdd= (TextView) findViewById(R.id.act_discuss_tv_add);

        mIvDiscussBack.setOnClickListener(this);
        mTvDiscussAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_discuss_iv_back:
                finish();
                break;
            case R.id.act_discuss_tv_add:
                presenter.addDiscuss();
                finish();
                break;
        }
    }

    @Override
    public EditText getmEdDiscussContent() {
        return mEdDiscussContent;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getGoodId() {
        return goodId;
    }

    @Override
    public void setPresenter(DiscussContract.DiscussPresenter presenter) {
          this.presenter=presenter;
    }
}
