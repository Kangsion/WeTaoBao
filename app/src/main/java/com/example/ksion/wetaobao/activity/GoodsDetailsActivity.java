package com.example.ksion.wetaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.GoodDetailsContract;
import com.example.ksion.wetaobao.presenter.ActGoodsDetailsPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import com.example.ksion.wetaobao.widget.XListView;
import com.jude.rollviewpager.RollPagerView;
import com.tencent.mm.sdk.openapi.SendMessageToWX;

/**
 * Created by Ksion on 2017/9/11.
 */

public class GoodsDetailsActivity extends BaseActivity implements GoodDetailsContract.GoodDetailsView,
        View.OnClickListener {
    //获取控件

    ImageView mActGoodsDetailsIvBack;

    TextView mActGoodsDetailsTvMenu;

    RollPagerView mActGoodsDetailsRollVpAd;

    TextView mActGoodsDetailsTvGoodsName;

    Button mActGoodsDetailsBtnShare;

    TextView mActGoodsDetailsTvMoney;

    XListView mActGoodsDetailsXlv;

    Button mActGoodsDetailsBtnKefu;

    Button mActGoodsDetailsBtnDianpu;

    Button mActGoodsDetailsBtnShoucang;

    Button mActGoodsDetailsBtnJiaru;

    Button mActGoodsDetailsBtnGoumai;
    private GoodDetailsContract.GoodDetailsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_good_details);
        initView();
        new ActGoodsDetailsPresenterImpl(this);
        presenter.initData();
    }

    private void initView() {
        mActGoodsDetailsBtnDianpu= (Button) findViewById(R.id.act_goods_details_btn_dianpu);
        mActGoodsDetailsBtnGoumai= (Button) findViewById(R.id.act_goods_details_btn_goumai);
        mActGoodsDetailsBtnJiaru= (Button) findViewById(R.id.act_goods_details_btn_jiaru);
        mActGoodsDetailsBtnKefu= (Button) findViewById(R.id.act_goods_details_btn_kefu);
        mActGoodsDetailsBtnShare= (Button) findViewById(R.id.act_goods_details_btn_share);
        mActGoodsDetailsBtnShoucang= (Button) findViewById(R.id.act_goods_details_btn_shoucang);
        mActGoodsDetailsIvBack= (ImageView) findViewById(R.id.act_good_details_iv_back);
        mActGoodsDetailsRollVpAd= (RollPagerView) findViewById(R.id.act_goods_details_roll_vp_ad);
        mActGoodsDetailsTvGoodsName= (TextView) findViewById(R.id.act_goods_details_tv_goods_name);
        mActGoodsDetailsTvMenu= (TextView) findViewById(R.id.act_good_details_tv_menu);
        mActGoodsDetailsTvMoney= (TextView) findViewById(R.id.act_goods_details_tv_money);
        mActGoodsDetailsXlv= (XListView) findViewById(R.id.act_goods_details_xlv);

        mActGoodsDetailsBtnGoumai.setOnClickListener(this);
        mActGoodsDetailsBtnJiaru.setOnClickListener(this);
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

    }

    @Override
    public void jumpLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public RollPagerView getmActGoodsDetailsRollVpAd() {
        return mActGoodsDetailsRollVpAd;
    }

    @Override
    public TextView getmActGoodsDetailsTvGoodsName() {
        return mActGoodsDetailsTvGoodsName;
    }

    @Override
    public XListView getmActGoodsDetailsXlv() {
        return getmActGoodsDetailsXlv();
    }

    @Override
    public TextView getmActGoodsDetailsTvMoney() {
        return mActGoodsDetailsTvMoney;
    }

    @Override
    public Button getmActGoodsDetailsBtnShoucang() {
        return mActGoodsDetailsBtnShoucang;
    }

    @Override
    public void setPresenter(GoodDetailsContract.GoodDetailsPresenter presenter) {
         this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_good_details_iv_back:
                finish();
                break;
            case R.id.act_good_details_tv_menu:
                break;
            case R.id.act_goods_details_btn_share:
                break;
            case R.id.act_goods_details_btn_kefu:
                break;
            case R.id.act_goods_details_btn_dianpu:
                break;
            case R.id.act_goods_details_btn_shoucang://收藏按钮被点击时,暂时不使用
                break;
            case R.id.act_goods_details_btn_jiaru://加入购物车

                    presenter.joinShopCar();

                break;
            case R.id.act_goods_details_btn_goumai://购买
                if (isLogin()) {
                    //跳转到下单页面
                   startActivity(new Intent(this, OrderActivity.class));
                } else {
                    jumpLogin();
                }
                break;
        }
        }

}
