package com.example.complete.ui.customview;

import android.content.Context;
import android.content.Intent;

import com.example.complete.R;
import com.example.complete.adapter.ListAdapter;
import com.example.complete.base.BaseActivity;
import com.example.complete.view.CustomScrollView;

import java.util.ArrayList;
import java.util.List;

public class CustomScrollViewActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_scroll;
    }



    @Override
    protected void initData() {
        super.initData();
        setTitle("自定义ScrollView测试");
        CustomScrollView lvContent = findViewById(R.id.lv_content);
        List<Integer> datas = new ArrayList<>();
        for (int index = 0; index < 40; index++) {
            datas.add(index);
        }
        ListAdapter adapter = new ListAdapter(this , datas);
        lvContent.setAdapter(adapter);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context , CustomScrollViewActivity.class);
        context.startActivity(intent);
    }

}
