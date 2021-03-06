package com.example.ksion.wetaobao.presenter;

import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.bean.User;
import com.example.ksion.wetaobao.contract.FragForPwdContract;
import com.example.ksion.wetaobao.util.DataProcessingUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;
//import cn.bmob.v3.listener.VerifySMSCodeListener;


/**
 * Created by Ksion on 2017/9/17.
 */

public class ActForPwdPresenterImpl implements FragForPwdContract.FragforpwdPresenter {

    private FragForPwdContract.FragforPwdView view;
    private EditText mEtPhone, mEtCode, mEtPwd, mEtPwd2;
    private TextView mTvGetCode;
    boolean isPhone=false;
    String objectId;

    public ActForPwdPresenterImpl(FragForPwdContract.FragforPwdView view)
    {
        this.view=view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        mEtPhone=view.getmActForgetEtPhone();
        mEtPwd=view.getmActForgetEtPwd();
        mEtPwd2=view.getmActForgetEtPwd2();
        mEtCode=view.getmActForgetEtSmsCode();
        mTvGetCode=view.getmActForgetTvGetcode();
    }



    @Override
    public void reset() {
        final String phone = mEtPhone.getText().toString().trim();
        String Code = mEtCode.getText().toString().trim();
        final String pwd = mEtPwd.getText().toString().trim();
        String pwd2 = mEtPwd2.getText().toString().trim();
        if (phone.isEmpty()) {
            view.showMsg("手机号码不能为空");
        } else if (pwd.isEmpty()) {
            view.showMsg("初始密码不能为空");
        } else if (pwd2.isEmpty()) {
            view.showMsg("重置密码不能为空");
        } else if (!queryPhone(phone)) {
            view.showMsg("用户不存在");
        } else {
            //短信验证码验证
//            BmobSMS.verifySmsCode(CustomApplcation.getInstance().context, phone, Code, new VerifySMSCodeListener() {
//                @Override
//                public void done(BmobException e) {
//                   if(e==null) {
//                       User user=new User();
//                       user.setPwd(pwd);
//                       user.update(view.getContext(), objectId, new UpdateListener() {
//                           @Override
//                           public void onSuccess() {
//                               view.showMsg("密码修改成功");
//                           }
//
//                           @Override
//                           public void onFailure(int i, String s) {
//                               view.showMsg("密码修改失败"+s);
//                           }
//                       });
//                   }
//                }
//            });
        }
    }

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
//            BmobSMS.requestSMSCode(CustomApplcation.getInstance().context, strPhone, "忘记密码", new RequestSMSCodeListener() {
//                @Override
//                public void done(Integer integer, BmobException e) {
//                    if (e==null){
//                        view.showMsg("验证码发送成功!");
//                    }else {
//                        view.showMsg("验证码发送失败!");
//                    }
//                }
//            });
//        } else {
            view.showMsg("请输入11位正确的手机号码!");
            return;
        }
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


    public boolean queryPhone(String phone) {
        String sql="select * from User where phone='"+phone+"'";

//        new BmobQuery<User>().doSQLQuery(view.getContext(), sql, new SQLQueryListener<User>() {
//            @Override
//            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
//                if(bmobQueryResult!=null) {
//                    objectId=bmobQueryResult.getResults().get(0).getObjectId();
//                    isPhone=true;
//                }
//            }
//        });
        return isPhone;
    }
}
