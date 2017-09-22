package com.example.ksion.wetaobao.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Ksion on 2017/9/5.
 */

public class NotSlipViewPager extends ViewPager {
     private boolean isCanSrcoll=false;
    public NotSlipViewPager(Context context) {
        super(context);
    }

    public NotSlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isCanSrcoll) {
            return super.onTouchEvent(ev);
        }
        else {
            return false;
        }
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isCanSrcoll)
        {
        return super.onInterceptTouchEvent(ev);
        }
        else
        {
            return false;
        }
    }
}
