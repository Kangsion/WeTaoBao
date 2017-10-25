package com.example.ksion.wetaobao.activity;

import android.app.AlertDialog;
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

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.contract.PersonalDetailContract;
import com.example.ksion.wetaobao.presenter.ActPersonalDetailPresenterImpl;
import com.example.ksion.wetaobao.util.ToastFactory;
import de.hdodenhof.circleimageview.CircleImageView;
import zhangphil.iosdialog.widget.ActionSheetDialog;

import com.kale.lib.photo.GetSimplePhotoHelper;
import com.kale.lib.photo.SimplePhoto;
/**
 * Created by Ksion on 2017/9/17.
 */

public class PersonalDetailActivity extends BaseActivity implements PersonalDetailContract.PersonalDetailView
        ,View.OnClickListener{

    //获取控件

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

    LinearLayout mActPersonDetailsLnSex;

    LinearLayout mActPersonDetailsLnAddress;

    LinearLayout mActPersonDetailsLnErcode;

    private PersonalDetailContract.PersonalPresenter presenter;
    //获取图片的帮助类
    private GetSimplePhotoHelper mPhotoHelper;
    //获取弹出对话框构造者对象
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_personal_detail);
        new ActPersonalDetailPresenterImpl(this);
        initView();
        presenter.initData();
        mPhotoHelper = GetSimplePhotoHelper.getInstance(this);
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
    public void canelLoadingDialog() {
        alertDialog.dismiss();
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
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //进行拍照
                                        mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_CAMERA, null, new MyListener());
                                    }
                                })
                        .addSheetItem("从相册中选择", ActionSheetDialog.SheetItemColor.Blue
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //从图库中获取
                                        mPhotoHelper.choicePhoto(GetSimplePhotoHelper.FROM_ALBUM, null, new MyListener());
                                    }
                                }).show();
                break;
            case R.id.act_person_details_ln_huiyuan_name:
                showMsg("会员名为当前的登陆名,不允许更改!");
                break;
            case R.id.act_person_details_ln_nick_name:
                showMyDialog(1, "更改淘宝昵称");
                break;
            case R.id.act_person_details_ln_sex:
                showMyDialog(2, "更改性别");
                break;
            case R.id.act_person_details_ln_ercode:
                //暂时显示提示，以后可以改为显示一张二维码
                showMsg("二维码不支持更改");
                break;
            case R.id.act_person_details_ln_address://修改收货地址
                startActivity(new Intent(this, AddressChangeActivity.class));
                break;
        }
    }

    /**
     * 弹出对话框
     *
     * @param type
     * @param msg
     */
    void showMyDialog(final int type, String msg) {
        //获取一个警告对话框的builder对象
        builder = super.showAlertDialog(null, null, true);
        View v = LayoutInflater.from(this).inflate(R.layout.act_address_change_text, null);
        //设置弹出的布局
        builder.setView(v);
        alertDialog = builder.show();
        //获取控件
        final EditText mDiscussEr = (EditText) v.findViewById(R.id.write_et_content);
        final TextView mTvTile = (TextView) v.findViewById(R.id.writer_tv_title);
        //设置按钮控件文字
        mTvTile.setText(msg);
        mDiscussEr.setHint("输入信息信息");
        Button mSendBtn = (Button) v.findViewById(R.id.write_btn_send);
        //给按钮设置点击事件
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏评论框
                PersonalDetailActivity.this.dismissAlertDialog(alertDialog);
                String text = mDiscussEr.getText().toString();
                presenter.updateUserInfo(text, type);
            }
        });
    }


    /**
     * 获取图片后的监听事件
     */
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
