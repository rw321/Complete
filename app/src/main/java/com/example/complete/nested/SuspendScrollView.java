package com.example.complete.nested;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.utils.ScreenUtils;

public class SuspendScrollView extends NestedScrollView {

    private ViewGroup mContentView;
    private View mHeaderView;
    private int velocity;
    private boolean isStartFling;
    private FlingHelper mFlingHelper;
    private int totalDy;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SuspendScrollView(@NonNull Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SuspendScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SuspendScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(){
        mFlingHelper = new FlingHelper(getContext());
        setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (isStartFling) {
                    isStartFling = false;
                    totalDy = 0;
                }

                if (scrollY == (getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    dispatchChildFling();
                }
                //在RecyclerView fling情况下，记录当前RecyclerView在y轴的偏移
                totalDy += scrollY - oldScrollY;

            }
        });
    }

    private void dispatchChildFling(){
        if (velocity > 0) {
            double splineFlingDistance = mFlingHelper.getSplineFlingDistance(velocity);

            if (splineFlingDistance >  totalDy) {
                childFling(mFlingHelper.getVelocityByDistance(splineFlingDistance - Double.valueOf(totalDy)));
            }
        }
    }

    private void childFling(int velY) {

        RecyclerView childRecyclerView = getChildRecyclerView(mContentView);
        if (childRecyclerView != null) {

            childRecyclerView.fling(0, velY);
        }
    }

    private RecyclerView getChildRecyclerView(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof RecyclerView) {
                return (RecyclerView) viewGroup.getChildAt(i);
            } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                ViewGroup childRecyclerView = getChildRecyclerView((ViewGroup) viewGroup.getChildAt(i));
                if (childRecyclerView instanceof RecyclerView) {
                    return (RecyclerView) childRecyclerView;
                }
            }
            continue;
        }
        return null;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = (ViewGroup) ((ViewGroup)getChildAt(0)).getChildAt(1);
        mHeaderView = ((ViewGroup)getChildAt(0)).getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.height = getMeasuredHeight() + ScreenUtils.getStatusHeight();
        mContentView.setLayoutParams(layoutParams);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(target, dx, dy, consumed, type);
        if (dy > 0 && getScrollY() < mHeaderView.getMeasuredHeight()) {
            scrollBy(0 , dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
        if (velocityY <= 0) {
            velocity = 0;
        }else {
            this.velocity = velocityY;
            isStartFling = true;
        }
    }
}
