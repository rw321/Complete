package com.example.complete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.complete.animation.AnimActivity;
import com.example.complete.dagger.DaggerTestActivity;
import com.example.complete.thread.ThreadTestActivity;
import com.example.complete.ui.ARouterActivity;
import com.example.complete.ui.SmartRefreshActivity;
import com.example.complete.ui.SyncTestActivity;
import com.example.complete.ui.ViewPagerTestActivity;
import com.example.complete.ui.androidbasic.JobSchedulerActivity;
import com.example.complete.ui.annotation.AnnotationTestActivity;
import com.example.complete.ui.customview.CustomViewActivity;
import com.example.complete.ui.ScreenActivity;
import com.example.complete.ui.customview.TableActivity;
import com.example.complete.ui.design.StyleModuleActivity;
import com.example.complete.ui.thirdlib.ThirdLibTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("First Page");

    }

    public void tableView(View view) {
        startActivity(new Intent(this , TableActivity.class));
    }

    public void viewPager(View view) {
        startActivity(new Intent(this , ViewPagerTestActivity.class));
    }

    public void goAnnotationPage(View view) {
        startActivity(new Intent(this , AnnotationTestActivity.class));
    }

    public void threadTest(View view) {
        startActivity(new Intent(this , ThreadTestActivity.class));
    }

    public void daggerTest(View view) {
        startActivity(new Intent(this , DaggerTestActivity.class));
    }

    public void syncTest(View view) {
        startActivity(new Intent(this , SyncTestActivity.class));
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
            //ARouter
            case R.id.one:
                ARouterActivity.startActivity(this);
                break;
            //设计模式
            case R.id.two:
                StyleModuleActivity.startActivity(this);
                break;
            //第三方库测试
            case R.id.three:
                startActivity(new Intent(this , ThirdLibTestActivity.class));
                break;
            //息屏测试
            case R.id.four:
                startActivity(new Intent(this , ScreenActivity.class));
                break;
            //自定义View测试
            case R.id.five:
                CustomViewActivity.startActivity(this);
                break;
            //下拉上拉刷新
            case R.id.six:
                startActivity(new Intent(this , SmartRefreshActivity.class));
                break;
            //
            case R.id.seven:
                startActivity(new Intent(this , AnimActivity.class));
                break;
            //JobScheduler测试
            case R.id.eight:
                startActivity(new Intent(this , JobSchedulerActivity.class));
                break;

        }
        return true;
    }
}
