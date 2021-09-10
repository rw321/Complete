package com.example.complete.design;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

/**
 * 适配器模式:把一个类的接口转换成客户端需要的另一种接口
 */
public class AdapterActivity extends BaseActivity {

    private Mobile mobile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("测试适配器模式");
        mobile = new Mobile();
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
            //类适配器
            case R.id.one:
                mobile.work(new VoltageAdapter());
                break;

            //对象适配器
            case R.id.two:
                mobile.work(new VoltageAdapter2(new SrcVoltage()));
                break;

            //接口适配器
            case R.id.three:

                break;


        }
        return true;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context , AdapterActivity.class));
    }

}
