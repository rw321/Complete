package com.example.complete.ui.design;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.design.HangryClient;
import com.example.complete.design.PersonComplete;
import com.example.complete.design.User;
import com.example.complete.design.YellowPeople;

/**
 * 测试设计模式
 */
public class StyleModuleActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("设计模式测试");
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
            //建造者模式
            case R.id.one:
                User user = new User.Builder("Alex", 1).age(28).phone("13264497440").address("China").build();
                System.out.println(user.toString());
                break;
            //模板方法模式
            case R.id.two:
                PersonComplete person = new YellowPeople();
                person.desc();
                break;
            //外观模式
            case R.id.three:
                new HangryClient().eat();
                break;
            //观察者模式
            case R.id.four:
                ObserverActivity.startActivity(this);
                break;
            //适配器模式
            case R.id.five:
                AdapterActivity.startActivity(this);
                break;
            //
            case R.id.six:

                break;
            //
            case R.id.seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.eight:
                Toast.makeText(this, "eight", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context , StyleModuleActivity.class);
        context.startActivity(intent);
    }
}
