package com.example.ksion.wetaobao.contract;

import com.example.ksion.wetaobao.base.BasePresenter;
import com.example.ksion.wetaobao.base.BaseView;

/**
 * Created by Ksion on 2017/9/18.
 */

public class SettingContract  {
     public  interface SettingView extends BaseView<SettingPersenter>{

     }
     public interface SettingPersenter extends BasePresenter<SettingView> {

     }
}
