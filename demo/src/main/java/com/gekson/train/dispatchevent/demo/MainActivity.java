package com.gekson.train.dispatchevent.demo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity implements View.OnTouchListener, View.OnClickListener {

    private String action;

    @InjectView(R.id.bt_test)
    Button view_btTitle1;

    @InjectView(R.id.bt_test2)
    Button viewGroup_btTitle2;

    @InjectView(R.id.tv_test)
    TextView view_tvText1;

    @InjectView(R.id.tv_test2)
    TextView viewGroup_tvText2;

    @InjectView(R.id.iv_test)
    ImageView view_ivImg1;

    @InjectView(R.id.iv_test2)
    ImageView viewGroup_ivImg2;

    @InjectView(R.id.myTextView)
    MyTextView view_myText;

    @InjectView(R.id.myLinearLayout)
    MyLinearLayout viewGroup_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
//        testViewEvent();
        testViewGroupEvent();
    }

    // 测试ViewGroup事件分发
    private void testViewGroupEvent() {
        viewGroup_linearLayout.setOnTouchListener(this);
        // 此处如果不设置点击事件就会跟之前一样，调用View的onTouch()，返回false,不会有后续的操作，
        // 因为LinearLayout是不可点击的
        viewGroup_linearLayout.setOnClickListener(this);
        viewGroup_btTitle2.setOnTouchListener(this);
        viewGroup_btTitle2.setOnClickListener(this);
        viewGroup_tvText2.setOnTouchListener(this);
        viewGroup_tvText2.setOnClickListener(this);
        viewGroup_ivImg2.setOnTouchListener(this);
        viewGroup_ivImg2.setOnClickListener(this);
    }

    // 测试View的事件分发
    private void testViewEvent() {
    /*view_btTitle1.setOnTouchListener(this);
    view_btTitle1.setOnClickListener(this);
    view_tvText1.setOnTouchListener(this);
    view_tvText1.setOnClickListener(this);
    view_ivImg1.setOnTouchListener(this);
    view_ivImg1.setOnClickListener(this);*/

        //可点击与不可点击
        view_btTitle1.setOnTouchListener(this);
        /*view_btTitle1.setOnClickListener(this);*/
        view_tvText1.setOnTouchListener(this);
        view_ivImg1.setOnTouchListener(this);

        view_myText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getActionName(event);
                Log.w("Touch_myText", action + ",被摸了！");
                return false;
            }
        });

        view_myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Touch_myText", "myText被点击了！");
            }
        });
    }

    // 正常情况下touch返回false
    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        getActionName(event);
        switch (v.getId()) {
            case R.id.bt_test:
                Log.i("Touch_Button", action + ",被摸了！");
                return false;
            case R.id.tv_test:
                Log.i("Touch_TextView", action + ",被摸了！");
                return false;
            case R.id.iv_test:
                Log.i("Touch_ImageView", action + ",被摸了！");
                return false;
            default:
                Log.i("screen", "屏幕被触摸了");
                return false;
        }
    }*/

    // onTouch返回false，屏蔽掉点击事件
    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        getActionName(event);
        switch (v.getId()) {
            case R.id.bt_test:
                Log.i("Touch_Button", action + ",被摸了！");
                return true;
            case R.id.tv_test:
                Log.i("Touch_TextView", action + ",被摸了！");
                return true;
            case R.id.iv_test:
                Log.i("Touch_ImageView", action + ",被摸了！");
                return true;
            default:
                Log.i("screen", "屏幕被触摸了");
                return true;
        }
    }*/

    // ImageView 和 Button 的区别___1__正常情况
    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        getActionName(event);
        switch (v.getId()) {
            case R.id.bt_test:
                Log.i("Touch_Button", action + ",被摸了！");
                return false;
            case R.id.tv_test:
                Log.i("Touch_TextView", action + ",被摸了！");
                return false;
            case R.id.iv_test:
                Log.i("Touch_ImageView", action + ",被摸了！");
                return false;
            default:
                Log.i("screen", "屏幕被触摸了");
                return false;
        }
    }*/

    // ImageView 和 Button 的区别___2___修改使之正常能够正常触发move down
    // 方法一:不可点击的控件，onTouch中返回true，使之能够返回true,但是触发不了Click事件(即使给设置点击事件)
    // 方法二:不用改变onTouch返回值，将不可点击的控件设为可点击setClickable
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getActionName(event);
        switch (v.getId()) {
            // View
            case R.id.bt_test:
                Log.i("Touch_Button", action + ",被摸了！");
                // 此处如果返回True,试想下是什么情况（返回true，设置了onClick事件，onClick事件将得不到执行）
                return false;
            case R.id.tv_test:
                Log.i("Touch_TextView", action + ",被摸了！" + view_tvText1.isEnabled());
                return true;
            case R.id.iv_test:
                Log.i("Touch_ImageView", action + ",被摸了！" + view_ivImg1.isEnabled());
                return true;
            // ViewGroup
            case R.id.myLinearLayout:
                Log.i("ViewGroup_LinearLayout", action + ",屏幕被摸了！");
                return false;
            case R.id.bt_test2:
                Log.i("ViewGroup_Button", action + ",Button被摸了！");
                return false;
            case R.id.tv_test2:
                Log.i("ViewGroup_TextView", action + ",TextView被摸了！");
                return false;
            case R.id.iv_test2:
                Log.i("ViewGroup_ImageView", action + ",ImageView被摸了！");
                return false;
            default:
                Log.i("screen", "屏幕被触摸了");
                return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // View
            case R.id.bt_test:
                Log.i("Click_Button", "Button被点击了!");
                break;
            case R.id.tv_test:
                Log.i("Click_TextView", "TextView被点击了!");
                break;
            case R.id.iv_test:
                Log.i("Click_ImageView", "ImageView被点击了!");
                break;
            // ViewGroup
            case R.id.myLinearLayout:
                Log.i("Click_LinearLayout", "LinearLayout被点击了!");
                break;
            case R.id.bt_test2:
                Log.i("Click_Button", "Button被点击了!");
                break;
            case R.id.tv_test2:
                Log.i("Click_TextView", "TextView被点击了!");
                break;
            case R.id.iv_test2:
                Log.i("Click_ImageView", "ImageView被点击了!");
                break;
            default:
                Log.i("screen", "屏幕被点击了!!");
                break;
        }
    }

    private void getActionName(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                action = "down";
                break;
            case 1:
                action = "up";
                break;
            case 2:
                action = "move";
                break;
            default:
                action = "";
        }
    }
}
