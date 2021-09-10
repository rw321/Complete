package com.example.complete.design;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

public class ObserverActivity extends BaseActivity {

    private Observable mObservable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("观察者模式测试");
        mObservable = new Observable();
        mObservable.registerObserver(new FirstObserver());
        mObservable.registerObserver(new SecodObserver());

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
            //通知被观察者
            case R.id.one:
                mObservable.update();
                break;


        }
        return true;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context , ObserverActivity.class));
    }

}
