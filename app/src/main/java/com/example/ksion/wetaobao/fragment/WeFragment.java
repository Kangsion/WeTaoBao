package com.example.ksion.wetaobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.contract.FragWeContract;
import com.example.ksion.wetaobao.presenter.FragWePresenterImpl;


/**
 * Created by Ksion on 2017/9/4.
 */

public class WeFragment extends BaseFragment implements FragWeContract.FragWeView,View.OnClickListener {
    private FragWeContract.FragWePresenter presenter;

    ViewPager mWeVpContent;

    private Button mBtnNews;
    private Button mBtnUpNews;
    private Button mBtnVideo;
    private Button mBtnShopcar;
    private Button mBtnTalk;

    private Button[] btns;
    private int []   btnID;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_we,null);
        mBtnNews= (Button) rootView.findViewById(R.id.frag_we_btn_news);
        mBtnUpNews= (Button) rootView.findViewById(R.id.frag_we_btn_up_news);
        mBtnVideo= (Button) rootView.findViewById(R.id.frag_we_btn_video);
        mBtnShopcar= (Button) rootView.findViewById(R.id.frag_we_btn_shopcar);
        mBtnTalk= (Button) rootView.findViewById(R.id.frag_we_btn_talk);
        mWeVpContent= (ViewPager) rootView.findViewById(R.id.frag_we_vp);
        return rootView;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        new FragWePresenterImpl(this);
        btns = new Button[]{mBtnNews, mBtnUpNews, mBtnVideo,
                mBtnShopcar, mBtnTalk};
        btnID = new int[]{R.id.frag_we_btn_news, R.id.frag_we_btn_up_news,
                R.id.frag_we_btn_video, R.id.frag_we_btn_shopcar, R.id.frag_we_btn_talk};

        mBtnNews.setOnClickListener(this);
        mBtnUpNews.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnShopcar.setOnClickListener(this);
        mBtnTalk.setOnClickListener(this);

        presenter.initData();
    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 选中的按钮颜色改变
     */
    private void SelectChangeBtnColor(int position) {
        for (int i = 0; i < btns.length; i++) {
            if (i == position) {
                btns[i].setEnabled(false);
            } else {
                btns[i].setEnabled(true);
            }
        }
    }
    @Override
    public ViewPager getmFragWeVp() {
        return mWeVpContent;
    }

    @Override
    public FragmentManager getManager() {
        return getChildFragmentManager();
    }

    @Override
    public void setPresenter(FragWeContract.FragWePresenter presenter) {
           this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.frag_we_btn_news:
                mWeVpContent.setCurrentItem(0);
                SelectChangeBtnColor(0);
                break;
            case R.id.frag_we_btn_up_news:
                mWeVpContent.setCurrentItem(1);
                SelectChangeBtnColor(1);
                break;
            case R.id.frag_we_btn_video:
                mWeVpContent.setCurrentItem(2);
                SelectChangeBtnColor(2);
                break;
            case R.id.frag_we_btn_shopcar:
                mWeVpContent.setCurrentItem(3);
                SelectChangeBtnColor(3);
                break;
            case R.id.frag_we_btn_talk:
                mWeVpContent.setCurrentItem(4);
                SelectChangeBtnColor(4);
                break;
        }
    }

}
