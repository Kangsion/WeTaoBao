package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.GoodTypeContract;
import com.example.ksion.wetaobao.presenter.ActGoodsTypePresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodsTypeActivity extends BaseActivity implements GoodTypeContract.GoodTypeView ,
        View.OnClickListener{

    ImageView mActGoodsTypeIvBack;
    TextView mActGoodsTypeTvMenu;
    GridView mActGoodsTypeGv;
    LinearLayout mActGoodsTypeLnContent;
    private GoodTypeContract.GoodTypePresenter presenter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_good_type);
        initView();
        context=this;
        new ActGoodsTypePresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActGoodsTypeIvBack= (ImageView) findViewById(R.id.act_good_type_iv_back);
        mActGoodsTypeTvMenu= (TextView) findViewById(R.id.act_good_type_tv_menu);
        mActGoodsTypeGv= (GridView) findViewById(R.id.act_goods_type_gv);
        mActGoodsTypeLnContent= (LinearLayout) findViewById(R.id.act_goods_type_ln_content);

        mActGoodsTypeIvBack.setOnClickListener(this);
        mActGoodsTypeTvMenu.setOnClickListener(this);
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(context,msg);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {
        super.showProcessDialog(title,msg,flag);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void canelLoadingDialog() {
       super.dismissProcessDialog();
    }

    @Override
    public void jumpActivity(String datas) {
        //跳转到商品展示的页面
        Intent intent = new Intent(this,GoodsResultActivity.class);
        intent.putExtra("datas",datas);
        startActivity(intent);
    }

    @Override
    public GridView getmActGoodsTypeGv() {
        return mActGoodsTypeGv;
    }

    @Override
    public LinearLayout getmActGoodsTypeLnContent() {
        return mActGoodsTypeLnContent;
    }

    @Override
    public void setPresenter(GoodTypeContract.GoodTypePresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_good_type_iv_back:
                finish();
                break;
            case R.id.act_good_type_tv_menu:
                showMsg("开发中。。。");
                break;
        }
    }
}
