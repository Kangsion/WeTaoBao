package com.example.ksion.wetaobao.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ksion.wetaobao.Application.CustomApplcation;
import com.example.ksion.wetaobao.R;
import com.example.ksion.wetaobao.base.BaseActivity;
import com.example.ksion.wetaobao.util.ToastFactory;
import com.example.ksion.wetaobao.widget.ErinieShow;

/**
 * Created by Ksion on 2017/9/20.
 */

public class LotteriesActivity extends BaseActivity implements View.OnClickListener{
    //获取控件

    RelativeLayout mContainer;
    ErinieShow erinieShow;

    TextView mActLotteriesTvTitle;

    Button mActLotteriesBtnLingqu;

    TextView mActLotteriesTvFinish;
    private int level = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lotteries);
        initView();
    }

    private void initView() {
        mContainer= (RelativeLayout) findViewById(R.id.container);
        mActLotteriesTvTitle= (TextView) findViewById(R.id.act_lotteries_tv_title);
        mActLotteriesBtnLingqu= (Button) findViewById(R.id.act_lotteries_btn_lingqu);
        mActLotteriesTvFinish= (TextView) findViewById(R.id.act_lotteries_tv_finish);

        mActLotteriesBtnLingqu.setOnClickListener(this);
        mActLotteriesTvFinish.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 显示刮奖图层
     */
    private void showErnie() {
        mContainer.removeAllViews();

        level = getLevel();
        erinieShow = new ErinieShow(this, level);
        mContainer.addView(erinieShow, new ViewGroup.LayoutParams(-2, -2));
    }

    /**
     * 获取奖励等级
     *
     * @return
     */
    public int getLevel() {
        //随机，看看几等奖
        double d = Math.random() * 100;
        if (d < 50) {
            return 0;
        }
        if (d < 80) {
            return 3;
        }
        if (d < 95) {
            return 2;
        }
        return 1;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_lotteries_tv_finish:
                finish();
                break;
            case R.id.act_lotteries_btn_lingqu:
                if (level == 0) {
                    ToastFactory.getToast(CustomApplcation.getInstance().context, "很遗憾，此次未中奖，再接再厉吧！").show();
                } else {
                    String money = "";
                    switch (level) {
                        case 1:
                            money = "10";
                            break;

                        case 2:
                            money = "20";
                            break;

                        case 3:
                            money = "50";
                            break;
                    }
                    ToastFactory.getToast(CustomApplcation.getInstance().context, "恭喜您，获得了" + money + "元优惠券。").show();
                }
                break;
        }
    }
}
