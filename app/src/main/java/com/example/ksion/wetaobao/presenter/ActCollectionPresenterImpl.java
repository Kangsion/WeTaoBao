package com.example.ksion.wetaobao.presenter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.adapter.ActCollectionAdapter;
import com.example.ksion.wetaobao.bean.Collection;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.CollectionContract;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Ksion on 2017/11/6.
 */

public class ActCollectionPresenterImpl implements CollectionContract.CollectionPresenter {
    private CheckBox mCheckBoxAll;
    private GridView mGridCollection;
    public CollectionContract.CollectionView view;
    private ActCollectionAdapter mAdapter;
    private List<Collection> collectionList;

    public ActCollectionPresenterImpl(CollectionContract.CollectionView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        mCheckBoxAll=view.getmCheckBoxAll();
        mGridCollection=view.getmGridCollection();

        queryCollection();

    }

    private void queryCollection() {
        String phone= CustomApplcation.getInstance().getCurrentUser().getPhone();
        BmobQuery<Collection> query = new BmobQuery<>();
        query.addWhereContains("phone", phone);
        query.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> list, BmobException e) {
                if(list != null) {
                    collectionList = list;
                    HashMap<Integer, Boolean> isChecked = new HashMap<>();
                    for (int i = 0; i < collectionList.size(); i++) {
                        isChecked.put(i, false);
                    }

                    ActCollectionAdapter.setIsSelected(isChecked);
                    mAdapter=new ActCollectionAdapter(view.getContext()
                            ,collectionList);
                    mGridCollection.setAdapter(mAdapter);
                    mGridCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            CustomApplcation.putDatas("goods",collectionList.get(position));
                            ActCollectionPresenterImpl.this.view.jumpActivity(collectionList.get(position).getGoodId());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void setCheckVisibility() {
        if(ActCollectionAdapter.isShow) {
            ActCollectionAdapter.isShow=false;
            mAdapter.notifyDataSetChanged();
        } else {
            ActCollectionAdapter.isShow=true;
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setAllSelected() {
        if(!mCheckBoxAll.isChecked()) {
            for (int i = 0; i < collectionList.size(); i++) {
                ActCollectionAdapter.isSelected.put(i, true);
            }
        } else {
            for (int i = 0; i < collectionList.size(); i++) {
                ActCollectionAdapter.isSelected.put(i, false);
            }
        }

    }

    @Override
    public void deleteSelected() {
        for (int i = collectionList.size()-1; i>=0 ; i--) {
            if(ActCollectionAdapter.isSelected.get(i)) {
                Collection collection=new Collection();
                final int finalI = i;
                collection.setObjectId(collectionList.get(i).getObjectId());
                collection.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null) {
                            collectionList.remove(finalI);
                        }
                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        }

    }
}
