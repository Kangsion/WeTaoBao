package com.example.ksion.wetaobao.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.IhomeContract;
import com.example.ksion.wetaobao.presenter.ActHomePresenterImpl;
import com.example.ksion.wetaobao.widget.NotSlipViewPager;
import com.jude.rollviewpager.RollPagerView;
import com.sunfusheng.marqueeview.MarqueeView;

/**
 * Created by Ksion on 2017/9/5.
 */

public class HomeActivity extends BaseActivity implements IhomeContract.IHomeView,OnClickListener {
    private IhomeContract.IHomePresenter presenter;
    private FragmentManager manager;
    private NotSlipViewPager mActHomeVpContent;

    //获取控件
    private Button mActHomeBtnHome;
    private Button mActHomeBtnWe;
    private Button mActHomeBtnShopcar;
    private Button mActHomeBtnPersonal;
    private Button mActHomeBtnAsk;

    private int [] btnID;
    private Button[] btns;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
        new ActHomePresenterImpl(this);
        btns[0].setEnabled(false);
        presenter.initData();

    }

    private void initView() {
        //初始化按钮ID
        btnID = new int[]{R.id.home_btn_home, R.id.home_btn_we,R.id.home_btn_ask ,
                R.id.home_btn_shopcar, R.id.home_btn_my};
        mActHomeVpContent= (NotSlipViewPager) findViewById(R.id.act_home_vp_content);
        mActHomeBtnHome= (Button) findViewById(R.id.home_btn_home);
        mActHomeBtnWe= (Button) findViewById(R.id.home_btn_we);
        mActHomeBtnShopcar= (Button) findViewById(R.id.home_btn_shopcar);
        mActHomeBtnPersonal= (Button) findViewById(R.id.home_btn_my);
        mActHomeBtnAsk= (Button) findViewById(R.id.home_btn_ask);

        mActHomeBtnHome.setOnClickListener(this);
        mActHomeBtnWe.setOnClickListener(this);
        mActHomeBtnShopcar.setOnClickListener(this);
        mActHomeBtnPersonal.setOnClickListener(this);
        mActHomeBtnAsk.setOnClickListener(this);

        btns  = new Button[]{mActHomeBtnHome,mActHomeBtnWe,mActHomeBtnAsk,mActHomeBtnShopcar,
                mActHomeBtnPersonal};
    }

    @Override
    public ViewPager getmActHomeVpContent() {
        return mActHomeVpContent;
    }

    @Override
    public FragmentManager getManager() {
        manager=getSupportFragmentManager();
        return manager;
    }

    @Override
    public GridView getGridViewSort() {
        return null;
    }

    @Override
    public GridView getGridViewContent() {
        return null;
    }

    @Override
    public MarqueeView getMarqueeViewTop() {
        return null;
    }

    @Override
    public RollPagerView getmActHomeVpAd() {
        return null;
    }

    @Override
    public void jumpActivity(int type, String datas) {

    }

    @Override
    public EditText getmFragHomeEtSearch() {
        return null;
    }


    @Override
    public void setPresenter(IhomeContract.IHomePresenter presenter) {
         this.presenter=presenter;
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


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.home_btn_home:
                SelectChangeBtnColor(0);
                mActHomeVpContent.setCurrentItem(0);
                break;
            case R.id.home_btn_we:
                SelectChangeBtnColor(1);
                mActHomeVpContent.setCurrentItem(1);
                break;
            case R.id.home_btn_ask:
                SelectChangeBtnColor(2);
                mActHomeVpContent.setCurrentItem(2);
                break;
            case R.id.home_btn_shopcar:
                SelectChangeBtnColor(3);
                mActHomeVpContent.setCurrentItem(3);
                break;
            case R.id.home_btn_my:
                SelectChangeBtnColor(4);
                mActHomeVpContent.setCurrentItem(4);
                break;
        }
    }
}
