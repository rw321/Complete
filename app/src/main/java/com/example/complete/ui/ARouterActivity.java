package com.example.complete.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.complete.Constant;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;

import butterknife.BindView;

@Route(path = Constant.ACTIVITY_URL_TEST)
public class ARouterActivity extends BaseActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Autowired()
    String name;
    @Autowired()
    int age;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_arouter;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("ARouter 测试");
        if (!TextUtils.isEmpty(name)) {
            tvText.setText(name + " is " + age + " years old");
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);

                    ARouter.getInstance()
                            .build(Constant.ACTIVITY_URL_TEST)
                            .withString("name" , "Alex")
                            .withInt("age" , 18)
                            .withOptionsCompat(compat)
                            //.withTransition(R.anim.push_in_right , R.anim.push_out_left)
                            .navigation();
                }
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context , ARouterActivity.class);
        context.startActivity(intent);
    }

}
