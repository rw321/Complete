package com.example.complete.ui.customview;

import androidx.core.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;


public class GestureRecognitionActivity extends BaseActivity implements GestureDetector.OnGestureListener
            , GestureDetector.OnDoubleTapListener{

    LinearLayout llContainer;
    private GestureDetectorCompat mDetectorCompat;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesture_recognition;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("手势识别");
        mDetectorCompat = new GestureDetectorCompat(this,this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        llContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetectorCompat.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        System.out.println("手指按下.....");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        System.out.println("手指按在触摸屏上，它的时间范围在按下起效，在长按之前");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("手指抬起....");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        System.out.println("手指在滑动...");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("长按.....");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        System.out.println("onFling...........");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        System.out.println("onSingleTap...........");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        System.out.println("onDoubleTap...........");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        System.out.println("onDoubleTapEvent..........." + e.getAction());
        return false;
    }
}
