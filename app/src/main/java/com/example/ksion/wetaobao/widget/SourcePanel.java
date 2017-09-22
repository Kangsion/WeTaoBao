package com.example.ksion.wetaobao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Ksion on 2017/9/1.
 */

public class SourcePanel extends GridView {
    public SourcePanel(Context context) {
        super(context);
    }

    public SourcePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SourcePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //禁止滑动
        if(ev.getAction()==MotionEvent.ACTION_MOVE)
            return true;

        return super.dispatchTouchEvent(ev);
    }
}
