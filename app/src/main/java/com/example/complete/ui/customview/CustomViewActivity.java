package com.example.complete.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;


/**
 * 自定义View测试
 */
public class CustomViewActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("自定义View测试");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            //自定义垂直ViewPage测试
            case R.id.one:
                GuideActivity.startActivity(this);
                break;

            case R.id.two:
                CustomScrollViewActivity.startActivity(this);
                break;
            //手势识别
            case R.id.three:
                startActivity(new Intent(this , GestureRecognitionActivity.class));
                break;

            case R.id.four:
                startActivity(new Intent(this , PickkerViewTestActivity.class));
                break;

            case R.id.five:

                break;

            case R.id.six:

                break;

            case R.id.seven:

                break;

            case R.id.eight:

                break;

        }
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context , CustomViewActivity.class);
        context.startActivity(intent);
    }

}
