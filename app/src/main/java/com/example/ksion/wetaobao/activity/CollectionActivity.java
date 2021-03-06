package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.CollectionContract;
import com.example.ksion.wetaobao.presenter.ActCollectionPresenterImpl;


/**
 * Created by Ksion on 2017/11/6.
 */

public class CollectionActivity extends BaseActivity implements CollectionContract.CollectionView
        ,View.OnClickListener{

    private TextView mCollectionManager;
    private TextView mCollectionOk;
    private CheckBox mCheckBoxAll;
    private Button   mBtnDelete;
    private GridView mGridCollection;
    private ImageView mIvBack;
    private LinearLayout mCollectionBottom;
    CollectionContract.CollectionPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_collection);
        initViews();
        new ActCollectionPresenterImpl(this);
        presenter.initData();
    }

    private void initViews() {
        mCollectionManager= (TextView) findViewById(R.id.act_collection_tv_manager);
        mCollectionOk= (TextView) findViewById(R.id.act_collection_tv_ok);
        mCheckBoxAll= (CheckBox) findViewById(R.id.act_collection_checkbox_all);
        mBtnDelete= (Button) findViewById(R.id.act_collection_delete);
        mGridCollection= (GridView) findViewById(R.id.act_collection_gridView);
        mIvBack= (ImageView) findViewById(R.id.act_collection_iv_back);
        mCollectionBottom= (LinearLayout) findViewById(R.id.act_collection_li_bottom);
        mCollectionManager.setOnClickListener(this);
        mCheckBoxAll.setOnClickListener(this);
        mCollectionOk.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void setPresenter(CollectionContract.CollectionPresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public TextView getmCollectionManager() {
        return mCollectionManager;
    }

    @Override
    public TextView getmCollectionOk() {
        return mCollectionOk;
    }

    @Override
    public CheckBox getmCheckBoxAll() {
        return mCheckBoxAll;
    }

    @Override
    public Button getmBtnDelete() {
        return mBtnDelete;
    }

    @Override
    public GridView getmGridCollection() {
        return mGridCollection;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void jumpActivity(String goodId) {
        Intent intent=new Intent(getContext(),GoodsDetailsActivity.class);
        intent.putExtra("goodId",goodId);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_collection_iv_back:
                finish();
                break;
            case R.id.act_collection_tv_manager:
                mCollectionManager.setVisibility(View.GONE);
                mCollectionBottom.setVisibility(View.VISIBLE);
                mCollectionOk.setVisibility(View.VISIBLE);
                presenter.setCheckVisibility();
                break;
            case R.id.act_collection_tv_ok:
                mCollectionOk.setVisibility(View.GONE);
                mCollectionManager.setVisibility(View.VISIBLE);
                mCollectionBottom.setVisibility(View.GONE);
                presenter.setCheckVisibility();
                break;
            case R.id.act_collection_delete:
                presenter.deleteSelected();
                break;
            case R.id.act_collection_checkbox_all:
                presenter.setAllSelected();
                break;
        }
    }
}
