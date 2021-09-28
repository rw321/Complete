package com.example.complete.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.common.utils.DenityUtils;

public class PercentTextView extends AppCompatTextView {


    private String text = "今天是个悲伤的日子";
    private float percent = 0.0f;
    private Paint mPaint;


    public PercentTextView(Context context) {
        super(context);
        init();
    }

    public PercentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PercentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(DenityUtils.dp2px(24));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText1(canvas);
    }

    private void drawText1(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.RED);
        float textWidth = mPaint.measureText(text);
        float textLeft = getWidth()/2 - textWidth/2;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine = getHeight()/2 -(fontMetrics.ascent + fontMetrics.descent)/2;
        Rect clipRect = new Rect((int)textLeft , 0 , (int)(textLeft + textWidth * percent)
                , getHeight());
        canvas.clipRect(clipRect);
        canvas.drawText(text , textLeft, baseLine , mPaint);
        canvas.restore();

        canvas.save();
        clipRect = new Rect((int)(textLeft + textWidth * percent) , 0
                , (int)(textLeft + textWidth) , getHeight());
        canvas.clipRect(clipRect);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(text , textLeft, baseLine , mPaint);
        canvas.restore();

    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

}
