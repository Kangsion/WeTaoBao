package com.example.ksion.wetaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.GoodResultContract;
import com.example.ksion.wetaobao.presenter.ActGoodsResultPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;

/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodsResultActivity extends BaseActivity implements GoodResultContract.GoodResultView,
        View.OnClickListener {
    //获取控件
    ImageView mActGoodsResultIvBack;

    TextView mActGoodsResultTvMenu;

    GridView mActGoodsResultGv;
    private GoodResultContract.GoodResultPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String typename=intent.getStringExtra("datas");
        setContentView(R.layout.act_good_result);
        initView();
        new ActGoodsResultPresenterImpl(this,typename);
        presenter.initData();
    }

    private void initView() {
        mActGoodsResultIvBack= (ImageView) findViewById(R.id.act_good_result_iv_back);
        mActGoodsResultTvMenu= (TextView) findViewById(R.id.act_good_result_tv_menu);
        mActGoodsResultGv= (GridView) findViewById(R.id.act_goods_result_gv);

    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this,msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public void jumpActivity() {
        startActivity(new Intent(this,GoodsDetailsActivity.class));
    }

    @Override
    public String getDatas() {
        //获取intent对象
        Intent intent = getIntent();
        //获取传递过来的数据
        return intent.getStringExtra("datas");
    }

    @Override
    public GridView getmActGoodsResultGv() {
        return mActGoodsResultGv;
    }

    @Override
    public void setPresenter(GoodResultContract.GoodResultPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
         switch (view.getId())
         {
             case R.id.act_good_result_iv_back:
                 finish();
                 break;
             case R.id.act_good_result_tv_menu:
                 break;
         }
    }
}
