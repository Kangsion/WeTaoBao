package com.example.ksion.wetaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.adapter.ActGoodsResultAdapter;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.bean.Goods;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by yeespec on 2017/10/30.
 */

public class SearchGoodActivity extends BaseActivity  implements View.OnClickListener{
     private EditText mSearchContent;
     private ImageView mSearchBack;
     private TextView mSearch;
     private GridView mSearchGridView;
     private TextView mTvSearchRsult;

     List<Goods> searchlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        initViews();
    }

    private void initViews() {
        mSearchContent= (EditText) findViewById(R.id.act_search_context);
        mSearchBack= (ImageView) findViewById(R.id.act_search_iv_back);
        mSearch= (TextView) findViewById(R.id.act_search_tv_search);
        mSearchGridView= (GridView) findViewById(R.id.act_search_result_gv);
        mTvSearchRsult= (TextView) findViewById(R.id.act_search_tv_result);
        mSearchBack.setOnClickListener(this);
        mSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_search_iv_back:
                finish();
                break;
            case R.id.act_search_tv_search:
                searchGood();
        }
    }

    private void searchGood() {
        final String goodName=mSearchContent.getText().toString().trim();
        if(!goodName.isEmpty()) {
            //String sql="select * from Goods";
            BmobQuery<Goods> query = new BmobQuery<>();
            query.findObjects(new FindListener<Goods>() {
                @Override
                public void done(List<Goods> list, BmobException e) {
                    if(e == null) {
                        List<Goods> goodsList = list;
                        for (int i = 0; i < goodsList.size(); i++) {
                            if (goodsList.get(i).getGoodsName().contains(goodName)) {
                                searchlist = new ArrayList<Goods>();
                                searchlist.add(goodsList.get(i));
                            }
                        }
                        if (searchlist != null) {
                            mSearchGridView.setAdapter(new ActGoodsResultAdapter(searchlist,
                                    SearchGoodActivity.this));

                            mSearchGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    CustomApplcation.putDatas("goods", searchlist.get(position));
                                    startActivity(new Intent(SearchGoodActivity.this, GoodsDetailsActivity.class));
                                }
                            });
                        } else {
                            mTvSearchRsult.setVisibility(View.VISIBLE);
                            mSearchGridView.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
    }
}
