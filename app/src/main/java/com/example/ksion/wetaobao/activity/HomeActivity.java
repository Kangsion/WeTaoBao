package com.example.ksion.wetaobao.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.ActHomeContract;
import com.example.ksion.wetaobao.contract.HomeContract;
import com.example.ksion.wetaobao.presenter.ActHomePresenterImpl
        ;
import com.example.ksion.wetaobao.widget.NotSlipViewPager;


/**
 * Created by Ksion on 2017/9/5.
 */

public class HomeActivity extends BaseActivity implements ActHomeContract.ActHomeView,OnClickListener {
    private ActHomeContract.ActHomePresenter presenter;
    private FragmentManager manager;
    private NotSlipViewPager mActHomeVpContent;

    //获取控件
    private Button mActHomeBtnHome;
    private Button mActHomeBtnWe;
    private Button mActHomeBtnShopcar;
    private Button mActHomeBtnPersonal;
    private Button mActHomeBtnAsk;

    private Button[] btns;
    private long mBackPressed;
    private final long BACKTIME=2000;

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

    @Override
    public void onBackPressed() {
        if(mBackPressed+BACKTIME> SystemClock.currentThreadTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this,"再次点击返回键退出",Toast.LENGTH_SHORT).show();
        }
        mBackPressed=SystemClock.currentThreadTimeMillis();
    }

    @Override
    public void setPresenter(ActHomeContract.ActHomePresenter presenter) {
        this.presenter=presenter;
    }
}
