package com.example.ksion.wetaobao.presenter;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.Goods;
import com.example.ksion.wetaobao.bean.Order;
import com.example.ksion.wetaobao.contract.OrderContract;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Ksion on 2017/9/19.
 */

public class ActOrderPresenterImpl implements OrderContract.OrderPresenter {
    private OrderContract.OrderView view;
    //声明一个控件对象
    EditText mEtCount;
    TextView mTvPrice,mTvMoney;
    private Goods goods;

    List<String> goodsList;

    public ActOrderPresenterImpl(OrderContract.OrderView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        goods= (Goods) CustomApplcation.getDatas("goods",false);
        mEtCount=view.getmActOrderEtCount();
        mTvPrice=view.getmActOrderTvGoodsMoney();
        mTvMoney=view.getmActOrdersTvNumMoney();
        goodsList=new ArrayList<>();
        view.setData(goods);
        initEvent();
    }

    @Override
    public void changeSumPrice(int count, String strPrice) {
        //截取文本内容
        String price  = strPrice.substring(2);
        //计算总价格
        double dprice = Double.parseDouble(price);
        double sum = dprice*count;
        //设置需要小数点两位数
        double sum1 = (double)Math.round(sum*100)/100;
        mTvMoney.setText("¥ "+sum1);
    }

    @Override
    public void submit() {
        //提交订单
        //获取数据
        String countStr = mEtCount.getText().toString();
        String sumMoneyStr = mTvMoney.getText().toString();
        String sumMoney = sumMoneyStr.substring(2);
        double dSumMoney = Double.parseDouble(sumMoney);
        int count = Integer.parseInt(countStr);
        //设置需要小数点两位数
        final double sum = (double) Math.round(dSumMoney * 100) / 100;
        //设置数据
        Order orders = new Order();
        orders.setOrderId(goods.getGoodsName() + sum + count);
        orders.setGoodId(goods.getGoodId());
        orders.setPhone(CustomApplcation.getInstance().getCurrentUser().getPhone());
        orders.setOrdersState(0);//设置订单状态为代付款0
        orders.setOerdersMoney(sum);
        orders.setGoodsCount(count);
        orders.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    view.showMsg("生产订单成功");
                    view.jumpActivity(null, 0);
                } else {
                    view.showMsg("生成订单失败");
                }
            }
        });
    }

    /**
     * 为按钮设置点击事件
     */
    private void initEvent() {
        mEtCount.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    changeSumPrice(Integer.parseInt(mEtCount.getText().toString()), mTvPrice.getText().toString());
                }
                return false;
            }
        });
    }
}
