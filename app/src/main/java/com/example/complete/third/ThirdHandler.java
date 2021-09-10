package com.example.complete.third;

import android.content.Intent;
import android.view.View;

public class ThirdHandler {

    public void skip2Drawable(View view) {
        Intent intent = new Intent(view.getContext() , DrawableTestActivity.class);
        view.getContext().startActivity(intent);
    }

}
