package com.example.ksion.wetaobao.presenter;

import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.RegisterContract;
import com.example.ksion.wetaobao.util.DataProcessingUtils;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
//import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by Ksion on 2017/9/13.
 */

public class ActRegisterPresentImpl  implements RegisterContract.RegisterPresenter {
     private RegisterContract.RegisterView view;
     private EditText mEtPhone, mEtCode, mEtNickName, mEtPwd, mEtPwd2;
     private TextView mTvGetCode;


     public ActRegisterPresentImpl(RegisterContract.RegisterView view)
     {
         this.view=view;
         view.setPresenter(this);
     }
    @Override
    public void getCode() {
        //获取文本框中的数据
        String strPhone = mEtPhone.getText().toString().trim();
        //判断数据的正确性
        if (strPhone.isEmpty()) {//判断文本框内容是否为空
            view.showMsg("手机号码不能为空!");
        } else if (DataProcessingUtils.isPhone(strPhone)) {//判断是否为手机号码
            //进行倒计时,并获取验证码
            startCountdown();
            //向服务器发送请求短信验证码
//            BmobSMS.requestSMSCode(CustomApplcation.getInstance().context, strPhone, "注册模板", new RequestSMSCodeListener() {
//                @Override
//                public void done(Integer smsId, BmobException ex) {
//                    if (ex == null) {//验证码发送成功
//                        view.showMsg("发送成功");
//                    } else {
//                        view.showMsg("发送失败,请检查网络");
//                    }
//                }
//            });
        } else {
            view.showMsg("请输入11位正确的手机号码!");
            return;
        }
    }

    @Override
    public void register() {
        final String phone = mEtPhone.getText().toString().trim();
        final String NickName = mEtNickName.getText().toString().trim();
        final String Pwd = mEtPwd.getText().toString().trim();
        String Code = mEtCode.getText().toString().trim();
        String Pwd2 = mEtPwd2.getText().toString().trim();
        if (phone.isEmpty()) {
            view.showMsg("手机号码不能为空");
        } else if (!DataProcessingUtils.isPhone(phone)) {
            view.showMsg("手机号码格式错误");
        } else if (Code.isEmpty()) {
            view.showMsg("请输入验证码");
        } else if (NickName.isEmpty()) {
            view.showMsg("请输入昵称");
        } else if (Pwd.isEmpty()) {
            view.showMsg("请输入密码");
        } else if (Pwd2.isEmpty()) {
            view.showMsg("请输确认密码");
        } else if (!Pwd.equals(Pwd2)) {
            view.showMsg("两次输入密码不一致");
        } else {
            view.showLoadingDialog("", "注册中...", false);
            //短信验证码验证
//            BmobSMS.verifySmsCode(CustomApplcation.getInstance().context, phone, Code, new VerifySMSCodeListener() {
//                @Override
//                public void done(BmobException e) {
//                    if(e==null) {
//                        User user = new User();
//                        user.setPhone(phone);
//                        user.setUserName(NickName);
//                        user.setPwd(Pwd);
//                        user.save(view.getContext(), new SaveListener() {
//
//                            @Override
//                            public void onSuccess() {
//                                view.showMsg("注册成功");
//                                view.jumpActivity();
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//                                view.showMsg("注册失败" + s);
//                            }
//                        });
//                    }
//                }
//            });
        }
    }

    @Override
    public void initData() {
         mEtPhone=view.getmActHomeEtPhone();
         mEtNickName=view.getmActHomeEtNickName();
         mEtPwd=view.getmActRegEtPwd();
         mEtPwd2=view.getmActRegEtPwd2();
         mEtCode=view.getmActHomeEtSmsCode();
         mTvGetCode=view.getmFragRegisterTvGetcode();
    }


            /**
             * 功能：开启一个倒计时
             */
            private void startCountdown() {
                //开启一个倒计时
                final CountDownTimer timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //设置倒计时
                        mTvGetCode.setText(millisUntilFinished / 1000 + "s");
                        //设置手机号码为不可更改状态
                        mEtPhone.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        mTvGetCode.setText("重新获取");
                        //设置按钮为可以改变状态
                        mEtPhone.setEnabled(true);
                    }
                };
                //开启倒计时
                timer.start();
            }
}
