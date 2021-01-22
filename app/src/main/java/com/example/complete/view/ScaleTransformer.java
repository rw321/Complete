package com.example.complete.view;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.70f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setPivotY(page.getHeight());
        if (position < -1 || position > 1) {
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
            if (position < 0) {
                float scaleX = 1 + 0.3f * position;
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.3f * position;
                page.setScaleY(scaleX);
            }
        }
    }
}
