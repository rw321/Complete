package com.example.complete.view;

import android.content.Context;
import android.database.DataSetObserver;
import androidx.core.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import com.example.common.utils.DenityUtils;
import com.example.complete.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomScrollView extends ViewGroup {

    private static final int SCROLL_STATE_IDLE = 0;
    private static final int SCROLL_STATE_DRAG = 1;
    private static final int INVALID_POINTER_ID = -1;

    private Context mContext;

    private int mContentHeight;

    private int mLastY;

    private int mTouchSlop;

    private int mScrollState;

    private int mScrollPointerId = INVALID_POINTER_ID;

    private OverScroller mScroller;

    private VelocityTracker mVelocityTracker;

    private float mMinFlingVelocity;

    private BaseAdapter mAdapter;

    private List<View> mActiveViews;

    private List<View> mScrapViews;

    private float mNextTop;

    private boolean mDataChanged;

    private DataSetObserver mObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            mDataChanged = true;
            requestLayout();
        }
    };

    public CustomScrollView(Context context) {
        this(context , null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

        ViewConfiguration configuration = ViewConfiguration.get(mContext);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinFlingVelocity = configuration.getScaledMinimumFlingVelocity();
        mScroller = new OverScroller(mContext);
        mActiveViews = new ArrayList<>();
        mScrapViews = new ArrayList<>();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view , widthMeasureSpec , heightMeasureSpec);
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
                    childView.layout(l , mContentHeight, r , mContentHeight + childView.getMeasuredHeight());
                    mContentHeight += childView.getMeasuredHeight();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        int actionIndex = MotionEventCompat.getActionIndex(event);
        obtainVelocityTracker(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) (event.getY() + 0.5f);
                mScrollState = SCROLL_STATE_IDLE;
                mScrollPointerId = event.getPointerId(actionIndex);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                mScrollPointerId = event.getPointerId(actionIndex);
                mLastY = (int) (event.getY(actionIndex) + 0.5f);
                break;

            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(mScrollPointerId);
                if (index < 0) {
                    return false;
                }

                int dy = mLastY - (int) (event.getY(index) + 0.5f);
                if (mScrollState != SCROLL_STATE_DRAG) {
                    boolean startScroll = false;
                    if (Math.abs(dy) > mTouchSlop) {
                        startScroll = true;
                    }
                    if (startScroll) {
                        mScrollState = SCROLL_STATE_DRAG;
                    }
                }

                if (mScrollState == SCROLL_STATE_DRAG) {

                    if ((dy < 0 && getScrollY() + dy < -DenityUtils.dp2px(100))
                        || (dy > 0 && getScrollY() + dy > mContentHeight - getHeight() + DenityUtils.dp2px(100))) {
                        dy = 0;
                    }

                    scrollBy(0 , dy);
                    mLastY = (int) (event.getY(index) + 0.5f);
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerId(actionIndex) == mScrollPointerId) {
                    int newIndex = actionIndex == 0 ? 1: 0;
                    mScrollPointerId = event.getPointerId(newIndex);
                    mLastY = (int) (event.getY(newIndex) + 0.5f);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (Math.abs(getVelocity()) > mMinFlingVelocity) {
                    mScroller.fling(0 , getScrollY() ,0 , (int) -getVelocity(), 0,0,0,mContentHeight - getHeight());
                    recyclerVelocityTracker();
                    invalidate();
                }

                if (getScrollY() > mContentHeight - getHeight()) {
                    mScroller.startScroll(0 , getScrollY() , 0 , mContentHeight - getHeight() - getScrollY());
                    invalidate();
                }

                if (getScrollY() < 0) {
                    mScroller.startScroll(0 , getScrollY() , 0 , - getScrollY());
                    invalidate();
                }

                break;
        }
        return true;
    }

    private float getVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return mVelocityTracker.getYVelocity();
    }

    private void obtainVelocityTracker(MotionEvent event) {

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recyclerVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX() , mScroller.getCurrY());
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        if (mAdapter != null) {
            mAdapter.unRegisterDataSetObserver(mObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mObserver);
            mAdapter.notifyDataSetChanged();
        }
    }
}
