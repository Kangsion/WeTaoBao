package com.example.ksion.wetaobao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.activity.GoodsResultActivity;
import com.example.ksion.wetaobao.activity.GoodsTypeActivity;
import com.example.ksion.wetaobao.activity.WebActivity;
import com.example.ksion.wetaobao.base.BaseFragment;
import com.example.ksion.wetaobao.config.Contracts;
import com.example.ksion.wetaobao.contract.IhomeContract;
import com.example.ksion.wetaobao.presenter.FlagHomePresenterlmpl;
import com.jude.rollviewpager.RollPagerView;
import com.sunfusheng.marqueeview.MarqueeView;

/**
 * Created by Ksion on 2017/9/1.
 */

public class HomeFragment extends BaseFragment implements IhomeContract.IHomeView{
    RollPagerView mFragHomeRollVpAd;

    GridView gridViewSort;

    GridView gridViewContent;

    MarqueeView marqueeViewTop;

    private IhomeContract.IHomePresenter presenter;

    EditText editText;

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        View rootView=inflater.inflate(R.layout.frag_home,null);
        gridViewSort= (GridView) rootView.findViewById(R.id.frag_home_gv_sort);
        mFragHomeRollVpAd= (RollPagerView) rootView.findViewById(R.id.frag_home_roll_vp_ad);
        gridViewContent= (GridView) rootView.findViewById(R.id.frag_home_gv_content);
        marqueeViewTop= (MarqueeView) rootView.findViewById(R.id.frag_home_gv_marquee);
        editText= (EditText) rootView.findViewById(R.id.id_search);
        return rootView;
    }

    @Override
    protected void initData( Bundle savedInstanceState) {
        new FlagHomePresenterlmpl(this);
        presenter.initData();
    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public ViewPager getmActHomeVpContent() {
        return null;
    }

    @Override
    public FragmentManager getManager() {
        return null;
    }

    @Override
    public GridView getGridViewSort() {
        return gridViewSort;
    }

    @Override
    public GridView getGridViewContent() {
        return gridViewContent;
    }

    @Override
    public MarqueeView getMarqueeViewTop() {
        return marqueeViewTop;
    }

    @Override
    public RollPagerView getmActHomeVpAd() {
        return mFragHomeRollVpAd;
    }

    @Override
    public void jumpActivity(int type, String datas) {
        Intent intent=null;
        switch (type)
        {
            case 0:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.TIANMAO_URL);
                 break;
            case 1:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.JUHUASUAN_URL);
                break;
            case 2:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.TIANMAO_GUOJI_URL);
                break;
            case 3:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.WAIMAI);
                break;
            case 4:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.TIANMAO_SUPERMARKET);
                break;
            case 5:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.ALI_TRAVEL);
                break;
            case 6:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.LING_MONEY);
                break;
            case 7:
                intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.TIANMAO_URL);
                break;
            case 8:
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", Contracts.DAO_JIA);
                break;
            case 9:
                intent = new Intent(getContext(), GoodsTypeActivity.class);
                break;
            case 10://搜索框
                intent = new Intent(getContext(), GoodsResultActivity.class);

        }
        startActivity(intent);
    }

    @Override
    public EditText getmFragHomeEtSearch() {
        return editText;
    }

    @Override
    public void setPresenter(IhomeContract.IHomePresenter presenter) {
           this.presenter=presenter;
    }
}
