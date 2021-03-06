package com.example.ksion.wetaobao.contract;

import android.content.Context;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/11/6.
 */

public class CollectionContract {
    public interface CollectionView extends BaseView<CollectionPresenter> {
         TextView getmCollectionManager();

         TextView getmCollectionOk();

         CheckBox getmCheckBoxAll();

         Button getmBtnDelete();

         GridView getmGridCollection();

         Context getContext();

         void jumpActivity(String goodId);
    }
    public  interface CollectionPresenter extends BasePresenter<CollectionView> {
        void setCheckVisibility();

        void setAllSelected();

        void deleteSelected();
    }
}
