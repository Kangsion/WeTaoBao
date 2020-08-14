package com.example.ksion.wetaobao.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.MyOrderContract;
import com.example.ksion.wetaobao.presenter.ActMyOrderPresenterImpl;
import com.kale.lib.utils.BitmapUtil;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyOrderActivity extends BaseActivity implements MyOrderContract.myOrderView,
        View.OnClickListener{
    private ImageView mMyOrderBack;
    private ViewPager viewPager;
    private MyOrderContract.myOrderPresenter presenter;

    private Button mBtnAll;
    private Button mBtnFuKan;
    private Button mBtnFaHuo;
    private Button mBtnShoHuo;
    private Button mBtnPinJia;

    private Button [] btns;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myorder);
        initView();
        new ActMyOrderPresenterImpl(this);
        presenter.initData();
        btns[0].setEnabled(false);
    }

    private void initView() {
        mMyOrderBack= (ImageView) findViewById(R.id.act_myorder_iv_back);
        viewPager= (ViewPager) findViewById(R.id.act_myorder_viewpager);
        mBtnAll= (Button) findViewById(R.id.act_myOrder_all);
        mBtnFuKan= (Button) findViewById(R.id.act_myOrder_fuKan);
        mBtnFaHuo= (Button) findViewById(R.id.act_myOrder_faHuo);
        mBtnShoHuo= (Button) findViewById(R.id.act_myOrder_shoHuo);
        mBtnPinJia= (Button) findViewById(R.id.act_myOrder_pinJia);

        btns=new Button[] {mBtnAll,mBtnFuKan,mBtnFaHuo,mBtnShoHuo,mBtnPinJia};

        mMyOrderBack.setOnClickListener(this);
        mBtnAll.setOnClickListener(this);
        mBtnFuKan.setOnClickListener(this);
        mBtnShoHuo.setOnClickListener(this);
        mBtnFaHuo.setOnClickListener(this);
        mBtnPinJia.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                  SelectChangeBtnColor(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setPresenter(MyOrderContract.myOrderPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public FragmentManager getManager() {
        manager=getSupportFragmentManager();
        return manager;
    }

    @Override
    public Context getMyOrderContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.act_myorder_iv_back:
                finish();
            break;
            case R.id.act_myOrder_all:
                viewPager.setCurrentItem(0);
                SelectChangeBtnColor(0);
                break;
            case R.id.act_myOrder_fuKan:
                viewPager.setCurrentItem(1);
                SelectChangeBtnColor(1);
                break;
            case R.id.act_myOrder_faHuo:
                viewPager.setCurrentItem(2);
                SelectChangeBtnColor(2);
                break;
            case R.id.act_myOrder_shoHuo:
                viewPager.setCurrentItem(3);
                SelectChangeBtnColor(3);
                break;
            case R.id.act_myOrder_pinJia:
                viewPager.setCurrentItem(4);
                SelectChangeBtnColor(4);
                break;
        }
    }


    /**
     * 选中的按钮颜色改变
     */
    private void SelectChangeBtnColor(int position)
    {
        for(int i=0;i<5;i++)
        {
            if(i==position)
            {
                btns[i].setEnabled(false);
            }
            else
            {
                btns[i].setEnabled(true);
            }
        }

    }
}
