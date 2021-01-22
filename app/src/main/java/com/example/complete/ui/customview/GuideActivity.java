package com.example.complete.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.adapter.MyViewPagerAdapter;
import com.example.complete.view.VarticalViewPagerView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        VarticalViewPagerView vvp = findViewById(R.id.vvp_guide);
        List<Integer> datas = new ArrayList<>();
        datas.add(0xFFFFFF00);
        datas.add(0xFFFF00FF);
        datas.add(0xFF00FFFF);
        datas.add(0xFF0000FF);
        vvp.setAdapter(new MyViewPagerAdapter(this , datas));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context , GuideActivity.class));
    }

}
