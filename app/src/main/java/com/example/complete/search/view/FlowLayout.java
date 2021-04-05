package com.example.complete.search.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.utils.DenityUtils;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private int mHorizonalSpace;
    private int mVerticalSpace;
    private List<List<View>> allViews;

    public FlowLayout(Context context) {
        this(context , null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mHorizonalSpace = DenityUtils.dp2px(6);
        mVerticalSpace = DenityUtils.dp2px(6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        int childViewNeedWidth = 0;
        int childViewNeedHeight = 0;
        allViews = new ArrayList();
        List<View> lineViews = new ArrayList<>();
        int lineWidth = 0;
        int lineHeight = 0;
        for (int i = 0 ; i < getChildCount() ; i++) {
            View childView = getChildAt(i);
            LayoutParams childLP = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec
                    , getPaddingLeft() + getPaddingRight(), childLP.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec
                    , getPaddingTop() + getPaddingBottom(), childLP.height);
            //childWidthMeasureSpec 和childHeightMeasureSpec只是父布局允许的宽高参数
            //子View的具体测量参数还得看子View自己的onMeasure()返回
            childView.measure(childWidthMeasureSpec , childHeightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            if (childWidth + lineWidth > selfWidth) {
                allViews.add(lineViews);
                childViewNeedHeight = childViewNeedHeight + lineHeight + mVerticalSpace;
                childViewNeedWidth = childHeightMeasureSpec > lineWidth ? childViewNeedWidth : lineWidth;
                lineViews = new ArrayList<>();
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(childView);
            lineWidth += (childWidth + mHorizonalSpace);
            lineHeight = lineHeight < childHeight ? childHeight : lineHeight;
            if(i == getChildCount() - 1) {
                allViews.add(lineViews);
                childViewNeedHeight = childViewNeedHeight + lineHeight + mVerticalSpace;
                childViewNeedWidth = childHeightMeasureSpec > lineWidth ? childViewNeedWidth : lineWidth;
            }
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int finalWidth = widthMode == MeasureSpec.EXACTLY?selfWidth : childViewNeedWidth;
        int finalHeight = heightMode == MeasureSpec.EXACTLY?selfHeight : childViewNeedHeight;

        setMeasuredDimension(finalWidth , finalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewTop = getPaddingTop();
        for (int i = 0; i < allViews.size();i++) {
            List<View> lineViews = allViews.get(i);
            int lineHeight = 0;
            int viewLeft = getPaddingLeft();
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                childView.layout(viewLeft , viewTop
                        , viewLeft+ childView.getMeasuredWidth()
                        , viewTop + childView.getMeasuredHeight());

                viewLeft = viewLeft + childView.getMeasuredWidth() + mHorizonalSpace;
                lineHeight = lineHeight > childView.getMeasuredWidth() ? lineHeight : childView.getMeasuredHeight();
            }

            viewTop = viewTop + lineHeight + mVerticalSpace;

        }
    }
}
