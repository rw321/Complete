package com.example.complete.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.sax.StartElementListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishDrawable extends Drawable {

    private static

    Paint mPaint;
    Path mPath;
    PointF mMiddlePoint;

    private int OTHER_ALPHA = 110;
    private int BODY_ALPHA = 160;

    // 鱼的主要朝向角度
    private float fishMainAngle = 90;

    /**
     * 鱼的长度值
     */
    // 绘制鱼头的半径
    private float HEAD_RADIUS = 100;
    // 鱼身长度
    private float BODY_LENGTH = HEAD_RADIUS * 3.2f;
    // 寻找鱼鳍起始点坐标的线长
    private float FIND_FINS_LENGTH = 0.9f * HEAD_RADIUS;
    // 鱼鳍的长度
    private float FINS_LENGTH = 1.3f * HEAD_RADIUS;
    // 大圆的半径
    private float BIG_CIRCLE_RADIUS = 0.7f * HEAD_RADIUS;
    // 中圆的半径
    private float MIDDLE_CIRCLE_RADIUS = 0.6f * BIG_CIRCLE_RADIUS;
    // 小圆半径
    private float SMALL_CIRCLE_RADIUS = 0.4f * MIDDLE_CIRCLE_RADIUS;
    // --寻找尾部中圆圆心的线长
    private final float FIND_MIDDLE_CIRCLE_LENGTH = BIG_CIRCLE_RADIUS * (0.6f + 1);
    // --寻找尾部小圆圆心的线长
    private final float FIND_SMALL_CIRCLE_LENGTH = MIDDLE_CIRCLE_RADIUS * (0.4f + 2.7f);
    // --寻找大三角形底边中心点的线长
    private final float FIND_TRIANGLE_LENGTH = MIDDLE_CIRCLE_RADIUS * 2.7f;

    public FishDrawable() {
        init();
    }

    private void init(){
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(OTHER_ALPHA, 244, 92, 71);
        mMiddlePoint = new PointF(HEAD_RADIUS * 4.19f , HEAD_RADIUS*4.19f);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        float fishAngle = fishMainAngle;

        PointF headerCenter = calcPoint(mMiddlePoint , BODY_LENGTH/2 , fishAngle);
        canvas.drawCircle(headerCenter.x , headerCenter.y , HEAD_RADIUS , mPaint);

        PointF finsRightStart = calcPoint(headerCenter , FIND_FINS_LENGTH , fishAngle - 110);
        drawFins(canvas , fishAngle , finsRightStart , true);

        PointF finsLeftStart = calcPoint(headerCenter , FIND_FINS_LENGTH , fishAngle + 110);
        drawFins(canvas , fishAngle , finsLeftStart , false);

        PointF segmentStart = calcPoint(headerCenter , BODY_LENGTH , fishAngle-180);
        drawSegment(canvas , fishAngle , segmentStart , BIG_CIRCLE_RADIUS
                , MIDDLE_CIRCLE_RADIUS , FIND_MIDDLE_CIRCLE_LENGTH , true);


        segmentStart = calcPoint(headerCenter , BODY_LENGTH + FIND_MIDDLE_CIRCLE_LENGTH , fishAngle-180);
        drawSegment(canvas , fishAngle , segmentStart , MIDDLE_CIRCLE_RADIUS
                , SMALL_CIRCLE_RADIUS , FIND_SMALL_CIRCLE_LENGTH , false);

        drawBody(canvas , headerCenter , fishAngle);

        PointF triangleStart = calcPoint(headerCenter , BODY_LENGTH + FIND_MIDDLE_CIRCLE_LENGTH , fishAngle - 180);

        makeTriangel(canvas, triangleStart, FIND_TRIANGLE_LENGTH, BIG_CIRCLE_RADIUS, fishAngle);
        makeTriangel(canvas, triangleStart, FIND_TRIANGLE_LENGTH - 10,
                BIG_CIRCLE_RADIUS - 20, fishAngle);

    }

    private void makeTriangel(Canvas canvas, PointF startPoint, float findCenterLength,
                              float findEdgeLength, float fishAngle) {
        // 三角形底边的中心坐标
        PointF centerPoint = calcPoint(startPoint, findCenterLength, fishAngle - 180);
        // 三角形底边两点
        PointF leftPoint = calcPoint(centerPoint, findEdgeLength, fishAngle + 90);
        PointF rightPoint = calcPoint(centerPoint, findEdgeLength, fishAngle - 90);

        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(leftPoint.x, leftPoint.y);
        mPath.lineTo(rightPoint.x, rightPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    private void drawBody(Canvas canvas , PointF start , float fishAngle) {
        PointF rightTop = calcPoint(start , HEAD_RADIUS , fishAngle+90);
        PointF rightBottom = calcPoint(start , HEAD_RADIUS , fishAngle-90);
        PointF leftCenter = calcPoint(start , BODY_LENGTH , fishAngle - 180);
        PointF leftTop = calcPoint(leftCenter , BIG_CIRCLE_RADIUS , fishAngle + 90);
        PointF leftBottom = calcPoint(leftCenter , BIG_CIRCLE_RADIUS , fishAngle - 90);

        PointF topControl = calcPoint(start , BODY_LENGTH * 0.56f , fishAngle + 130);
        PointF bottomControl = calcPoint(start , BODY_LENGTH * 0.56f , fishAngle - 130);

        mPath.reset();
        mPath.moveTo(rightTop.x , rightTop.y);
        mPath.lineTo(rightBottom.x , rightBottom.y);
        mPath.quadTo(bottomControl.x , bottomControl.y , leftBottom.x ,leftBottom.y);
        mPath.lineTo(leftTop.x , leftTop.y);
        mPath.quadTo(topControl.x , topControl.y , rightTop.x , rightTop.y);
        canvas.drawPath(mPath , mPaint);


    }

    /**
     *
     * @param canvas
     * @param fishAngle
     * @param start   起始位置
     * @param radius1 大圆半径
     * @param radius2 小圆半径
     * @param circleCenterDistance 两个圆心之间的距离
     * @param isBigSegment 是大结节
     */
    private void drawSegment(Canvas canvas , float fishAngle , PointF start
            , float radius1 , float radius2 ,float circleCenterDistance , boolean isBigSegment) {
        PointF rightTop = calcPoint(start ,radius1 , fishAngle + 90);
        PointF rightBottom = calcPoint(start ,radius1 , fishAngle - 90);
        PointF leftCenter = calcPoint(start ,circleCenterDistance , fishAngle - 180);
        PointF leftTop = calcPoint(leftCenter ,radius2 , fishAngle + 90);
        PointF leftBottom = calcPoint(leftCenter ,radius2 , fishAngle - 90);

        if (isBigSegment)
            canvas.drawCircle(start.x , start.y , radius1 , mPaint);

        canvas.drawCircle(leftCenter.x , leftCenter.y , radius2 , mPaint);

        mPath.reset();
        mPath.moveTo(rightTop.x , rightTop.y);
        mPath.lineTo(rightBottom.x , rightBottom.y);
        mPath.lineTo(leftBottom.x , leftBottom.y);
        mPath.lineTo(leftTop.x , leftTop.y);
        canvas.drawPath(mPath , mPaint);


    }

    private void drawFins(Canvas canvas , float fishAngle , PointF start , boolean isRight) {
        PointF end = calcPoint(start , FINS_LENGTH , fishAngle - 180);
        PointF control = calcPoint(start , FINS_LENGTH * 1.8f
                , isRight ? fishAngle - 115 : fishAngle + 115);

        mPath.reset();
        mPath.moveTo(start.x , start.y);
        mPath.quadTo(control.x , control.y , end.x , end.y);
        canvas.drawPath(mPath , mPaint);
    }

    private PointF calcPoint(PointF start , float radius , float angle) {
        float x = (float) (Math.cos(Math.toRadians(angle))*radius + start.x);
        float y = (float) (Math.sin(Math.toRadians(angle - 180)) * radius + start.y);
        return new PointF(x , y);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;  //半透明
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38 * HEAD_RADIUS);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38 * HEAD_RADIUS);
    }
}
