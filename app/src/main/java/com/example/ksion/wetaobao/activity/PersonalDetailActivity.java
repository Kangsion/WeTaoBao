package com.example.ksion.wetaobao.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.PersonalDetailContract;
import com.example.ksion.wetaobao.presenter.ActPersonalDetailPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import de.hdodenhof.circleimageview.CircleImageView;
//import zhangphil.iosdialog.widget.ActionSheetDialog;

import com.kale.lib.photo.GetSimplePhotoHelper;
import com.kale.lib.photo.SimplePhoto;
/**
 * Created by Ksion on 2017/9/17.
 */

public class PersonalDetailActivity extends BaseActivity implements PersonalDetailContract.PersonalDetailView
        ,View.OnClickListener{

    ImageView mActPersonDetailsIvBack;

    TextView mActSettingTvMenu;

    LinearLayout mActSettingLnNav;

    CircleImageView mActPersonDetailsIvHead;

    LinearLayout mActPersonDetailsLnHead;

    TextView mActPersonDetailsTvHuiyanName;

    LinearLayout mActPersonDetailsLnHuiyuanName;

    TextView mActPersonDetailsTvNickName;

    LinearLayout mActPersonDetailsLnNickName;

    TextView mTextView;

    TextView mActPersonDetailsTvSex;

    TextView mActPersonDetailsTvAdress;

    LinearLayout mActPersonDetailsLnSex;

    LinearLayout mActPersonDetailsLnAddress;

    LinearLayout mActPersonDetailsLnErcode;

    private PersonalDetailContract.PersonalPresenter presenter;

    private GetSimplePhotoHelper mPhotoHelper;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_personal_detail);
        new ActPersonalDetailPresenterImpl(this);
        initView();
        presenter.initData();
    }
    private void initView() {
        mActPersonDetailsIvBack= (ImageView) findViewById(R.id.act_person_details_iv_back);
        mActPersonDetailsIvHead= (CircleImageView) findViewById(R.id.act_person_details_iv_head);
        mActPersonDetailsLnHead= (LinearLayout) findViewById(R.id.act_person_details_ln_head);
        mActPersonDetailsLnAddress= (LinearLayout) findViewById(R.id.act_person_details_ln_address);
        mActPersonDetailsLnErcode= (LinearLayout) findViewById(R.id.act_person_details_ln_ercode);
        mActPersonDetailsLnHuiyuanName= (LinearLayout) findViewById(R.id.act_person_details_ln_huiyuan_name);
        mActPersonDetailsLnSex= (LinearLayout) findViewById(R.id.act_person_details_ln_sex);
        mActPersonDetailsLnNickName= (LinearLayout) findViewById(R.id.act_person_details_ln_nick_name);
        mActPersonDetailsTvHuiyanName= (TextView) findViewById(R.id.act_person_details_tv_huiyan_name);
        mActPersonDetailsTvNickName= (TextView) findViewById(R.id.act_person_details_tv_nick_name);
        mActPersonDetailsTvSex= (TextView) findViewById(R.id.act_person_details_tv_sex);
        mActSettingLnNav= (LinearLayout) findViewById(R.id.act_setting_ln_nav);
        mActSettingTvMenu= (TextView) findViewById(R.id.act_setting_tv_menu);
        mActPersonDetailsTvAdress= (TextView) findViewById(R.id.act_person_details_tv_address);
        mTextView= (TextView) findViewById(R.id.textView);

        mActPersonDetailsIvBack.setOnClickListener(this);
        mActPersonDetailsLnHead.setOnClickListener(this);
        mActPersonDetailsLnSex.setOnClickListener(this);
        mActPersonDetailsLnErcode.setOnClickListener(this);
        mActPersonDetailsLnHuiyuanName.setOnClickListener(this);
        mActPersonDetailsLnNickName.setOnClickListener(this);
        mActPersonDetailsLnAddress.setOnClickListener(this);


    }

    @Override
    public CircleImageView getmActPersonDetailsIvHead() {
        return mActPersonDetailsIvHead;
    }

    @Override
    public TextView getmActPersonDetailsTvHuiyanName() {
        return mActPersonDetailsTvHuiyanName;
    }

    @Override
    public TextView getmActPersonDetailsTvNickName() {
        return mActPersonDetailsTvNickName;
    }

    @Override
    public TextView getmActPersonDetailsTvSex() {
        return mActPersonDetailsTvSex;
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this,msg);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void canelLoadingDialog() {

    }

    @Override
    public TextView getmActPersonDetailsTvAdress() {
        return mActPersonDetailsTvAdress;
    }

    @Override
    public void jumpActivity() {
        finish();
    }

    @Override
    public void setPresenter(PersonalDetailContract.PersonalPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.act_person_details_iv_back:
                finish();
            case R.id.act_person_details_ln_head:
//                new ActionSheetDialog(this)
//                        .builder()
//                        .setCancelable(true)
//                        .setCanceledOnTouchOutside(true)
//                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red
//                                , new ActionSheetDialog.OnSheetItemClickListener() {
//                                    @Override
//                                    public void onClick(int which) {
//                                        mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_CAMERA, null, new MyListener());
//                                    }
//                                })
//                        .addSheetItem("从相册中选择", ActionSheetDialog.SheetItemColor.Blue
//                                , new ActionSheetDialog.OnSheetItemClickListener() {
//                                    @Override
//                                    public void onClick(int which) {
//                                        //从图库中获取
//                                        mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_ALBUM, null, new MyListener());
//                                    }
//                                }).show();
                break;
            case R.id.act_person_details_ln_huiyuan_name:
                showMsg("会员名为当前的登陆名,不允许更改!");
                break;
            case R.id.act_person_details_ln_nick_name:
                startActivity(new Intent(this,UserNameChangeActivity.class));
                break;
            case R.id.act_person_details_ln_sex:
                showSexSelectDialog();
                break;
            case R.id.act_person_details_ln_ercode:
                showMsg("二维码不支持更改");
                break;
            case R.id.act_person_details_ln_address:
                startActivity(new Intent(this, AddressChangeActivity.class));
                break;
        }
    }



    void showSexSelectDialog() {
        builder=super.showAlertDialog(null,null,true);
        View v=LayoutInflater.from(this).inflate(R.layout.dialog_sex_select,null);
        builder.setView(v);
        alertDialog=builder.show();
        final TextView mTvMan= (TextView) v.findViewById(R.id.dialog_sex_man);
        final TextView mTvWoman= (TextView) v.findViewById(R.id.dialog_sex_woman);
        mTvMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalDetailActivity.this.dismissAlertDialog(alertDialog);
                mActPersonDetailsTvSex.setText(mTvMan.getText().toString());
                presenter.updateUserSex(mTvMan.getText().toString());
            }
        });
        mTvWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalDetailActivity.this.dismissAlertDialog(alertDialog);
                mActPersonDetailsTvSex.setText(mTvWoman.getText().toString());
                presenter.updateUserSex(mTvWoman.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initData();
    }

    class MyListener implements GetSimplePhotoHelper.OnSelectedPhotoListener {


        @Override
        public void onSelectedPhoto(int fromWay, SimplePhoto photo) {
            if (photo != null) {
                Log.d("myTag", "uri = " + photo.uri.toString());
                Log.d("myTag", "photo's degree = " + photo.degree);
                //上传头像
                presenter.upload(photo.uri);
            }
        }
    }
}
