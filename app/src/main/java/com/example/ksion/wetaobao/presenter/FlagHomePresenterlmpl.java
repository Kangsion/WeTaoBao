package com.example.ksion.wetaobao.presenter;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.AdRollPageAdapter;
import com.example.ksion.wetaobao.adapter.ContentGridViewAdapter;
import com.example.ksion.wetaobao.adapter.SortGridViewAdapter;
import com.example.ksion.wetaobao.contract.IhomeContract;
import com.jude.rollviewpager.RollPagerView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksion on 2017/9/1.
 */

public class FlagHomePresenterlmpl  implements IhomeContract.IHomePresenter {
    //存放轮播图片ID
    private ArrayList<Integer> imgs;
    //存放分类图标Id
    private Integer [] sortImgs;
    //分类文字
    private String [] sortStrs;

    //存放下部分小图标
    private Integer contentIcoImgs[];
    //存放商品图片
    private Integer contentImgs[];
    //存放小图标对应文字
    private String contentText[];
    //存放下面文字
    private String contentText2[];
    private GridView mGridViewSort;
    private GridView mGridViewContent;
    private IhomeContract.IHomeView mView;
    private MarqueeView marqueeViewTop;
    private AdRollPageAdapter mAdadapter;
    private RollPagerView rollPagerViewAd;
    private EditText editText;

    public FlagHomePresenterlmpl(IhomeContract.IHomeView view)
    {
         mView=view;
        mView.setPresenter(this);
    }

    public void initData()
    {
        //轮播
        rollPagerViewAd=mView.getmActHomeVpAd();
        mGridViewSort=mView.getGridViewSort();
        mGridViewContent=mView.getGridViewContent();
        marqueeViewTop=mView.getMarqueeViewTop();
        editText=mView.getmFragHomeEtSearch();

        imgs=new ArrayList<>();
        //加入轮播图片
        imgs.add(R.drawable.pager1);
        imgs.add(R.drawable.pager2);
        imgs.add(R.drawable.pager3);
        imgs.add(R.drawable.pager4);
        imgs.add(R.drawable.pager5);
        sortImgs = new Integer[]{R.drawable.frag_home_sort_tianmao, R.drawable.frag_home_sort_juhuasuan,
                R.drawable.frag_home_sort_jinkou, R.drawable.frag_home_sort_waimai, R.drawable.frag_home_sort_market,
                R.drawable.frag_home_sort_chongzhi, R.drawable.frag_home_sort_travel, R.drawable.frag_home_sort_tao,
                R.drawable.frag_home_sort_daojia, R.drawable.frag_home_sort_type};
        sortStrs = new String[]{"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "阿里旅行",
                "领金币", "到家", "分类"};

        contentIcoImgs = new Integer[]{R.drawable.frag_home_qianggou, R.drawable.frag_home_haohuo,
                R.drawable.frag_home_guangjie, R.drawable.frag_home_qingdan};
        contentImgs = new Integer[]{R.drawable.xiangj, R.drawable.bookbag, R.drawable.xiangj, R.drawable.bookbag};
        contentText = new String[]{"淘抢购", "有好货", "爱逛街", "必买清单"};
        contentText2 = new String[]{"极速抢购", "高颜值美物", "时髦流行家", "整理好帮手"};

        mAdadapter=new AdRollPageAdapter(imgs);
        rollPagerViewAd.setAdapter(mAdadapter);

        mGridViewSort.setAdapter(new SortGridViewAdapter(CustomApplcation.getInstance().context,
                sortImgs, sortStrs, R.layout.flag_home_gv_content_item));

        //此处数据，应该为从网络上获取
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是C陈志广。");
        info.add("2. 这是我的二期项目！");
        info.add("3. GitHub帐号：mmengchen");
        info.add("4. 淘宝双11.11");
        info.add("5. 个人博客");
        info.add("6. 消息进行测试");
        //启动滚动
        marqueeViewTop.startWithList(info);

        mGridViewContent.setAdapter(new ContentGridViewAdapter(CustomApplcation.getInstance().context,
            contentIcoImgs,contentImgs,contentText,contentText2,R.layout.frag_home_gv_goods_item));

        initEvent();
    }

    private void initEvent() {
       mGridViewSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(0,"");
                        break;
                    case 1:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(1,"");
                        break;
                    case 2:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(2,"");
                        break;
                    case 3:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(3,"");
                        break;
                    case 4:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(4,"");
                        break;
                    case 5:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(5,"");
                        break;
                    case 6:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(6,"");
                        break;
                    case 7:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(7,"");
                        break;
                    case 8:
                        FlagHomePresenterlmpl.this.mView.jumpActivity(8,"");
                        break;
                    case 9://分类
                        FlagHomePresenterlmpl.this.mView.jumpActivity(9, "");
                        break;
                }
           }
       });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                FlagHomePresenterlmpl.this.mView.jumpActivity(10,"");
                return false;
            }
        });
    }
}
