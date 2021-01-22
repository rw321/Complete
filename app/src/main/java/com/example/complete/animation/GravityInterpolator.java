package com.example.complete.animation;

import android.animation.TimeInterpolator;

public class GravityInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - 0.15f / 4) * (2 * Math.PI) / 0.15f) + 1);
    }
}
