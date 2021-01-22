package com.example.complete.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.example.complete.adapter.MyViewPagerAdapter;

public class VarticalViewPagerView extends ViewGroup {

    private Context mContext;

    private int mScreenHeight;

    private int mStartScroll;

    private int mEndScroll;

    private int mLastY;

    private Scroller mScroller;

    private boolean isScrolling;

    private VelocityTracker mVelocityTracker;

    private int mContentHeight;

    private MyViewPagerAdapter mAdapter;

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateView();
        }
    };

    private void updateView() {
        removeAllViews();
        for (int index = 0; index < mAdapter.getCount(); index++) {
            addView(mAdapter.getView(index , this));
        }
    }

    public VarticalViewPagerView(Context context) {
        this(context , null);
    }

    public VarticalViewPagerView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public VarticalViewPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

        WindowManager mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        mScreenHeight = mDisplayMetrics.heightPixels;
        mScroller = new Scroller(mContext);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView , widthMeasureSpec , heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            mContentHeight = 0;
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (childView.getVisibility() != View.GONE) {
                    childView.layout(l , mContentHeight, r , mContentHeight + mScreenHeight);
                    mContentHeight += mScreenHeight;
                }
            }
        }
    }

        @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isScrolling)
            return super.onTouchEvent(event);

        int y = (int) event.getY();
        obtainVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartScroll = getScrollY();
                mLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished())
                    mScroller.abortAnimation();
                int dy = mLastY - y;
                int scrollY = getScrollY();
                if (dy < 0 && scrollY + dy < 0) {
                    dy = - scrollY;
                }
                if (dy > 0 && scrollY + dy > mContentHeight - mScreenHeight) {
                    dy = mContentHeight - mScreenHeight - scrollY;
                }
                scrollBy(0 , dy);
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                mEndScroll = getScrollY();
                int dScrollY = mEndScroll - mStartScroll;
                if (wantToScrollNext()) {
                    if (shouldScrollToNext()) {
                        mScroller.startScroll(0 , getScrollY() , 0 , mScreenHeight - dScrollY);
                    }else {
                        mScroller.startScroll(0 , getScrollY() , 0 , -dScrollY);
                    }
                }

                if (wantToScrollPre()) {
                    if (shouldScrollToPre()) {
                        mScroller.startScroll(0 , getScrollY() , 0 , -(dScrollY+mScreenHeight));
                    }else {
                        mScroller.startScroll(0 , getScrollY() , 0 , -dScrollY);
                    }
                }
                isScrolling = true;
                postInvalidate();
                recycler();
                break;
        }

        return true;
    }

    /**
     * 是否有向下滚动的意图
     * @return
     */
    private boolean wantToScrollNext() {
        return mStartScroll < mEndScroll;
    }

    /**
     * 是否有向上滑动的意图
     * @return
     */
    private boolean wantToScrollPre() {
        return mStartScroll > mEndScroll;
    }

    /**
     * 已滑动距离是否满足滑动到下一视图的距离
     * @return
     */
    private boolean shouldScrollToNext() {
        return mEndScroll - mStartScroll > mScreenHeight/2 || getVelocity() > 600;
    }

    /**
     * 已滑动距离是否满足滑动到上一视图的距离
     * @return
     */
    private boolean shouldScrollToPre() {
        return mStartScroll - mEndScroll > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 获取滑动速率
     * @return
     */
    private int getVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }

    /**
     * 初始化速率监测对象
     * @param event
     */
    private void obtainVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 释放速率监测对象
     */
    private void recycler() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0 , mScroller.getCurrY());
            postInvalidate();
        }else {
            isScrolling = false;
        }

    }

    public void setAdapter(MyViewPagerAdapter adapter) {
        if (mAdapter != null) {
            mAdapter.unRegisterDataSetObserver(mDataSetObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mAdapter.notifyDataSetChanged();
        }

    }



}
