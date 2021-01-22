package com.example.complete.ui.customview;

import android.graphics.Color;
import android.view.View;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.view.pickerview.builder.OptionsPickerBuilder;
import com.example.complete.view.pickerview.listener.OnOptionsSelectChangeListener;
import com.example.complete.view.pickerview.listener.OnOptionsSelectListener;
import com.example.complete.view.pickerview.view.OptionsPickerView;

import java.util.ArrayList;

public class PickkerViewTestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picker;
    }

    public void pickTest(View view) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        })
                .setTitleText("阶段")
                .setContentTextSize(22)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setTitleColor(Color.BLACK)
                .setContentTextSize(20)
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)

                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {

                    }
                })
                .build();
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("0-2岁");
        options2Items_01.add("2-4岁");
        options2Items_01.add("4-6岁");
        pvOptions.setPicker(options2Items_01);
        pvOptions.show();
    }

}
