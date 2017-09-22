package com.example.ksion.wetaobao.presenter;


import android.widget.GridView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.SortGridViewAdapter;
import com.example.ksion.wetaobao.contract.PersonalContract;


/**
 * Created by Ksion on 2017/9/8.
 */

public class PersonalPresenterImpl implements PersonalContract.PersonalPresenter {
    PersonalContract.PersonalView view;

    GridView mGridViewCenter;
    GridView mGridViewBottom;

    public PersonalPresenterImpl(PersonalContract.PersonalView view)
    {
       this.view=view;
        this.view.setPresenter(this);
    }
    @Override
    public void queryOrders(int type) {

    }


    @Override
    public void initData() {
        //获取中间的GridView
        mGridViewCenter = view.getmFragPersonalGvCenter();
        //获取下部的GridView控件
        mGridViewBottom = view.getmFragPersonalGvBottom();
        //初始化数据
        Integer imgCenterIds[] = {R.drawable.frag_my_shoucang_baobei, R.drawable.frag_shoucang_neirong,
                R.drawable.frag_my_guanzhu, R.drawable.frag_my_zuji, R.drawable.frag_my_card,
                R.drawable.frag_my_mayi, R.drawable.frag_my_huiyuan, R.drawable.frag_my_xiaomi, R.drawable.frag_my_qianbao,
                R.drawable.frag_my_phone, R.drawable.frag_my_kuaidi, R.drawable.frag_my_shangjia};
        String strsCenter[] = {"收藏宝贝", "收藏内容", "关注", "足迹", "卡券包", "蚂蚁花呗", "会员中心", "我的小蜜",
                "我要换钱", "我的通信", "我的快递", "我是商家"};
        Integer imgBottomIds[] = {R.drawable.frag_my_quanzi, R.drawable.frag_my_share,
                R.drawable.frag_my_ask, R.drawable.frag_my_bottom_pingjia};
        String strsBottom[] = {"我的圈子", "我的分享", "问大家", "我的评价"};
        //配置适配器
        mGridViewCenter.setAdapter(new SortGridViewAdapter(CustomApplcation.getInstance().context
        ,imgCenterIds,strsCenter,R.layout.frag_personal_gv_sort_item));

        mGridViewBottom.setAdapter(new SortGridViewAdapter(CustomApplcation.getInstance().context
                ,imgBottomIds,strsBottom,R.layout.frag_personal_gv_sort_item));
    }
}
