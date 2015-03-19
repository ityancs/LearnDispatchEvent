package com.gekson.train.dispatchevent.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by yanchangsen on 15/3/19.
 */
public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 正常情况下
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // 调用View的dispatchTouchEvent(event)
        Log.v("event", event.getAction() + "触发了!");
//        return true;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 返回false将不会触发后续的move和down操作
//        return false;
        return super.onTouchEvent(event);
    }
}
