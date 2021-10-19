package com.example.complete.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.utils.DenityUtils;
import com.example.complete.adapter.SuspendTestAdapter;


public class SuspendItemDecoration extends RecyclerView.ItemDecoration {

    Paint mPaint;
    private int mGroupHeaderHeight = 90;

    public SuspendItemDecoration() {
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(DenityUtils.dp2px(24));

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getAdapter() instanceof SuspendTestAdapter) {
            SuspendTestAdapter adapter = (SuspendTestAdapter) parent.getAdapter();
            int position = parent.getChildAdapterPosition(view);
            boolean isHeader = adapter.isHeader(position);
            if (isHeader){
                outRect.top = DenityUtils.dp2px(mGroupHeaderHeight);
            }else {
                outRect.top = 1;
            }
        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        SuspendTestAdapter adapter = (SuspendTestAdapter) parent.getAdapter();
        int count = parent.getChildCount();
        for (int i = 0 ; i < count ;i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            boolean isHeader = adapter.isHeader(position);
            Rect rect;
            if (isHeader) {
                rect = new Rect(0 , view.getTop() - DenityUtils.dp2px(mGroupHeaderHeight)
                        , parent.getRight() , view.getTop());
                c.drawRect(rect , mPaint);
                mPaint.setColor(Color.RED);
            }else {
                rect = new Rect(0 , view.getTop() - 1 , parent.getRight() , view.getTop());
                mPaint.setColor(Color.BLACK);
            }

            c.drawRect(rect , mPaint);


            if (isHeader) {
                mPaint.setColor(Color.WHITE);
                Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
                float y = view.getTop() - DenityUtils.dp2px(mGroupHeaderHeight)/2
                        - (fontMetrics.ascent + fontMetrics.descent)/2;
                float x = view.getLeft() + DenityUtils.dp2px(12);

                c.drawText(adapter.getGroupName(position) , x , y , mPaint);

            }

        }
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        SuspendTestAdapter adapter = (SuspendTestAdapter) parent.getAdapter();
        LinearLayoutManager manager = (LinearLayoutManager) parent.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

        if (adapter.isHeader(firstVisibleItemPosition + 1)) {
            View firstVisibleView = parent.findViewHolderForAdapterPosition(firstVisibleItemPosition).itemView;
            float bottom = Math.min(DenityUtils.dp2px(mGroupHeaderHeight)
                        ,firstVisibleView.getBottom());

            RectF rect = new RectF(parent.getLeft() , parent.getTop()
                    , parent.getRight() , bottom);

            mPaint.setColor(Color.RED);
            c.drawRect(rect , mPaint);


            mPaint.setColor(Color.WHITE);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float y = bottom - DenityUtils.dp2px (mGroupHeaderHeight) + DenityUtils.dp2px(mGroupHeaderHeight)/2
                    - (fontMetrics.ascent + fontMetrics.descent)/2;
            float x = parent.getLeft() + DenityUtils.dp2px(12);

            c.drawText(adapter.getGroupName(firstVisibleItemPosition) , x , y , mPaint);

        }else {
            Rect rect = new Rect(parent.getLeft() , parent.getTop()
                    , parent.getRight() , parent.getTop() + DenityUtils.dp2px(mGroupHeaderHeight));

            mPaint.setColor(Color.RED);
            c.drawRect(rect , mPaint);

            mPaint.setColor(Color.WHITE);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float y = DenityUtils.dp2px(mGroupHeaderHeight)/2
                    - (fontMetrics.ascent + fontMetrics.descent)/2;
            float x = parent.getLeft() + DenityUtils.dp2px(12);

            c.drawText(adapter.getGroupName(firstVisibleItemPosition) , x , y , mPaint);

        }
    }
}
