package com.example.complete.second;

import android.content.Intent;
import android.view.View;

import com.example.complete.annotation.AnnotationTestActivity;
import com.example.complete.generic.GenericTestActivity;
import com.example.complete.design.StyleModuleActivity;

public class SecondHandler {

    public void skipToGenaric(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), GenericTestActivity.class));
    }

    public void skipToAnnotation(View view) {
        Intent intent = new Intent(view.getContext(), AnnotationTestActivity.class);
        intent.putExtra("name" , "Alex");
        intent.putExtra("age" , 18);
        view.getContext().startActivity(intent);

    }

    public void skipToDesignModel(View view){
        StyleModuleActivity.startActivity(view.getContext());
    }


}
