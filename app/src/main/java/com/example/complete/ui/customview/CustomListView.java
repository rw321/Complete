package com.example.complete.ui.customview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.complete.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomListView extends ViewGroup {

    private Context mContext;

    private int mLastY;

    private List<View> mActiveViews;

    private List<View> mScrapViews;

    private float mNextTop;

    private boolean mDataChanged;

    private BaseAdapter mAdapter;

    private int mItemCount;

    private int mFirstVisableItemPosition;

    private int mLastVisableItemPosition;

    private DataSetObserver mObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            mDataChanged = true;
            requestLayout();
        }
    };

    public CustomListView(Context context) {
        this(context , null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

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

    }

    private void layoutChildren() {
        int height = getTop() - getBottom();
        int pos = mFirstVisableItemPosition;
        while(mNextTop < height && pos < mItemCount) {
            View view = makeAndAddView(pos);
            //addView();
        }
    }

    private View makeAndAddView(int position) {
        View child = null;
        if (!mDataChanged) {

            child = getActiveView(position - mFirstVisableItemPosition);

            if (child != null) {
                return child;
            }
        }

        child = getScrapView();
        if (child == null) {
            child = mAdapter.getView(position , null , this);
        }else {
            mAdapter.getView(position , child , this);
        }

        return child;
    }

    private View getScrapView() {
        View scrapView = null;
        if (mScrapViews.size() > 0) {
            scrapView = mScrapViews.get(0);
            mScrapViews.remove(0);
        }
        return scrapView;
    }

    private View getActiveView(int index) {
        View activeView = null;
        if (index >= 0 && index < mActiveViews.size()) {
            activeView = mActiveViews.get(index);
            mActiveViews.remove(index);
        }
        return activeView;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y =  (int) (event.getY() + 0.5f);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) (event.getY() + 0.5f);
                break;

            case MotionEvent.ACTION_MOVE:

                int dy = mLastY - y;

                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
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
