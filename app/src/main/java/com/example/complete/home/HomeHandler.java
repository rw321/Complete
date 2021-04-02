package com.example.complete.home;

import android.content.Intent;
import android.view.View;

import com.example.complete.search.SearchActivity;

public class HomeHandler {

    public void skip(View view) {
        view.getContext().startActivity(new Intent(view.getContext() , SearchActivity.class));
    }

}
