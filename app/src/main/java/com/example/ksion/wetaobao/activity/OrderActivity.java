package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.contract.OrderContract;
import com.example.ksion.wetaobao.presenter.ActOrderPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import com.squareup.picasso.Picasso;

/**
 * Created by Ksion on 2017/9/19.
 */

public class OrderActivity extends BaseActivity  implements OrderContract.OrderView ,View.OnClickListener{

    //获取控件
    ImageView mActOrdersIvBack;

    TextView mActOrderTvTop;

    TextView mActOrderTvShouhuoName;

    TextView mActOrderTvShouhuoPhone;

    TextView mActOrderTvShouhuoAddress;

    ImageView mActOrderIvGoodsImg;

    TextView mActOrderTvGoodsName;

    TextView mActOrderTvGoodsMoney;

    LinearLayout mActOrderLn;

    ImageView mActOrderIvLine;

    Button mActOrderBtnAdd;

    EditText mActOrderEtCount;

    Button mActOrderBtnJian;

    TextView mActOrderTvShouhou;

    RelativeLayout mActOrderRlShouhou;

    TextView mActOrderTvPeisong;

    RelativeLayout mActOrderRlPeisong;

    TextView mActOrderTvYunfei;

    RelativeLayout mActOrderRlYunfei;

    TextView mActOrderTvLiuyan;

    RelativeLayout mActOrderRlLiuyan;

    TextView mActOrdersTvNumMoney;

    Button mActOrdersBtnSubmit;
    private OrderContract.OrderPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_order);
        initView();
        new ActOrderPresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActOrderBtnAdd= (Button) findViewById(R.id.act_order_btn_add);
        mActOrderBtnJian= (Button) findViewById(R.id.act_order_btn_jian);
        mActOrderEtCount= (EditText) findViewById(R.id.act_order_et_count);
        mActOrderIvGoodsImg= (ImageView) findViewById(R.id.act_order_iv_goods_img);
        mActOrderIvLine= (ImageView) findViewById(R.id.act_order_iv_line);
        mActOrderLn= (LinearLayout) findViewById(R.id.act_order_ln);
        mActOrderRlLiuyan= (RelativeLayout) findViewById(R.id.act_order_rl_liuyan);
        mActOrderRlPeisong= (RelativeLayout) findViewById(R.id.act_order_rl_peisong);
        mActOrderRlShouhou= (RelativeLayout) findViewById(R.id.act_order_rl_shouhou);
        mActOrderRlYunfei= (RelativeLayout) findViewById(R.id.act_order_rl_yunfei);
        mActOrdersBtnSubmit= (Button) findViewById(R.id.act_orders_btn_submit);
        mActOrdersIvBack= (ImageView) findViewById(R.id.act_orders_iv_back);
        mActOrdersTvNumMoney= (TextView) findViewById(R.id.act_orders_tv_num_money);
        mActOrderTvShouhuoAddress= (TextView) findViewById(R.id.act_order_tv_shouhuo_address);
        mActOrderTvShouhuoName= (TextView) findViewById(R.id.act_order_tv_shouhuoName);
        mActOrderTvGoodsMoney= (TextView) findViewById(R.id.act_order_tv_goods_money);
        mActOrderTvGoodsName= (TextView) findViewById(R.id.act_order_tv_goods_name);
        mActOrderTvLiuyan= (TextView) findViewById(R.id.act_order_tv_liuyan);
        mActOrderTvPeisong= (TextView) findViewById(R.id.act_order_tv_peisong);
        mActOrderTvShouhou= (TextView) findViewById(R.id.act_order_tv_shouhou);
        mActOrderTvShouhuoPhone= (TextView) findViewById(R.id.act_order_tv_shouhuoPhone);
        mActOrderTvTop= (TextView) findViewById(R.id.act_order_tv_top);
        mActOrderTvYunfei= (TextView) findViewById(R.id.act_order_tv_yunfei);

        mActOrdersIvBack.setOnClickListener(this);
        mActOrderBtnAdd.setOnClickListener(this);
        mActOrderBtnJian.setOnClickListener(this);
        mActOrdersBtnSubmit.setOnClickListener(this);
        mActOrderRlShouhou.setOnClickListener(this);
        mActOrderRlLiuyan.setOnClickListener(this);
        mActOrderRlPeisong.setOnClickListener(this);
        mActOrderRlYunfei.setOnClickListener(this);


    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this, msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public void jumpActivity(String orderId, double sum) {
        //跳转到支付
        Intent intent = new Intent(this,HomeActivity.class);
        //将订单编号和订单金额出传递到下一个activity
       /* intent.putExtra("orderId",orderId);
        intent.putExtra("sumMoney",sum);*/
        startActivity(intent);
    }

    @Override
    public void setData(Goods goods) {
        //设置收货人
        mActOrderTvShouhuoName.setText(CustomApplcation.getInstance().getCurrentUser().getUserName());
        //设置默认收货地址
        mActOrderTvShouhuoAddress.setText("收货地址："+CustomApplcation.getInstance().getCurrentUser().getAddress());
        //设置电话号码(默认为注册时的电话号码)
        mActOrderTvShouhuoPhone.setText(CustomApplcation.getInstance().getCurrentUser().getPhone()+"");
        //设置商品图片(默认为第一张图片)
        Picasso.with(this).load(goods.getGoodsImgs().getUrl()).into(mActOrderIvGoodsImg);
        //设置商品标题
        mActOrderTvGoodsName.setText(goods.getGoodsName());
        //设置价格
        mActOrderTvGoodsMoney.setText("¥ "+goods.getGoodsPrice());
        //设置数量默认为1
        mActOrderEtCount.setText("1");
        //设置总结金额
        mActOrdersTvNumMoney.setText("¥ "+goods.getGoodsPrice());
    }

    @Override
    public EditText getmActOrderEtCount() {
        return mActOrderEtCount;
    }

    @Override
    public TextView getmActOrdersTvNumMoney() {
        return mActOrdersTvNumMoney;
    }

    @Override
    public TextView getmActOrderTvGoodsMoney() {
        return mActOrderTvGoodsMoney;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setPresenter(OrderContract.OrderPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        //数量
        int count = Integer.parseInt(mActOrderEtCount.getText().toString());
        //获取价格
        String strPrice = mActOrderTvGoodsMoney.getText().toString();
        switch (view.getId()) {
            case R.id.act_orders_iv_back:
                finish();
                break;
            case R.id.act_order_btn_add:
                count++;
                mActOrderEtCount.setText(count+"");
                presenter.changeSumPrice(count,strPrice);
                break;
            case R.id.act_order_btn_jian:
                if (count>1){
                    count--;
                    mActOrderEtCount.setText(count+"");
                    presenter.changeSumPrice(count,strPrice);
                }
                break;
            case R.id.act_order_rl_shouhou:
                showMsg("暂时无法修改");
                break;
            case R.id.act_order_rl_peisong:
                showMsg("暂时无法修改");
                break;
            case R.id.act_order_rl_yunfei:
                showMsg("暂时无法修改");
                break;
            case R.id.act_order_rl_liuyan:
                showMsg("暂时无法修改");
                break;
            case R.id.act_orders_btn_submit:
                //判断数量是否为空,为空则默认为1
                if (mActOrderEtCount.getText().toString().isEmpty()){
                    mActOrderEtCount.setText("1");
                }
                presenter.submit();
                break;
        }
    }
}
