package com.example.ksion.wetaobao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;

/**
 * Created by Ksion on 2017/8/31.
 */

public class SplashActivity extends BaseActivity {
    //停止时间
    private final static  int PAUSE_TIME=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        handler.sendEmptyMessageDelayed(3000,PAUSE_TIME);


    }

    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3000:
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
            }

        }
    };

}
