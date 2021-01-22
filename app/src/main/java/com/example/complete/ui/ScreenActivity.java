package com.example.complete.ui;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.common.utils.ToastUtils;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.receiver.ScreenOffAdminReceiver;

import java.lang.reflect.InvocationTargetException;

public class ScreenActivity extends BaseActivity {

    private DevicePolicyManager policyManager;
    private ComponentName adminReceiver;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen;
    }

    @Override
    protected void initData() {
        super.initData();
        adminReceiver = new ComponentName(ScreenActivity.this, ScreenOffAdminReceiver.class);
        mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        policyManager = (DevicePolicyManager) ScreenActivity.this.getSystemService(Context.DEVICE_POLICY_SERVICE);

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
            //息屏
            case R.id.one:
                closeScreen();
                break;
            //检测屏幕状态
            case R.id.two:
                checkScreen();
                break;
            //开启超级管理员权限
            case R.id.three:
                checkAndTurnOnDeviceManager();
                break;
            //亮屏
            case R.id.four:
                openScreen();
                break;
            //
            case R.id.five:

                break;
            //
            case R.id.six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
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

    /**
     * 亮屏
     */
    private void openScreen() {

        mWakeLock = mPowerManager.newWakeLock((PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                | PowerManager.PARTIAL_WAKE_LOCK), "Notification:");
        mWakeLock.acquire();
        mWakeLock.release();

    }

    /**
     * 检测屏幕状态
     */
    private void checkScreen() {

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            //息屏
            ToastUtils.showCustomToast("屏幕是息屏");
        } else {
            //亮屏
            ToastUtils.showCustomToast("屏幕是亮屏");
        }

    }

    /**
     * 息屏
     */
    private void closeScreen() {

//        boolean admin = policyManager.isAdminActive(adminReceiver);
//        if (admin) {
//            policyManager.lockNow();
//        } else {
//            ToastUtils.showCustomToast("没有设备管理权限");
//        }

        try {
            mPowerManager.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(mPowerManager, SystemClock.uptimeMillis());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 检测并去激活设备管理器权限
     * @param
     */
    public void checkAndTurnOnDeviceManager() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminReceiver);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "开启后就可以使用锁屏功能了...");
        //显示位置见图二
        startActivityForResult(intent, 0);
    }




}
