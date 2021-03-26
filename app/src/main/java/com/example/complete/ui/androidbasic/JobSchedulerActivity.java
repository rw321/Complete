package com.example.complete.ui.androidbasic;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

public class JobSchedulerActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        super.initData();
        int jobId = 1;
        JobInfo.Builder builder = new JobInfo.Builder(jobId , new ComponentName(getPackageName(), MyJobScheduler.class.getName()));
        builder.setMinimumLatency(5000);  //任务至少延迟多少毫秒后执行
        builder.setOverrideDeadline(30000); //任务至多延迟多少毫秒后执行
        //builder.setPeriodic(3000);  //任务周期性执行 该方法不能喝上面两个方法连用
        //JobInfo.NETWORK_TYPE_NONE（无网络时执行，默认）、
        //JobInfo.NETWORK_TYPE_ANY（有网络时执行）、
        //JobInfo.NETWORK_TYPE_UNMETERED（网络无需付费时执行）
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); //设置任务执行的网络环境
        //builder.setRequiresDeviceIdle(true); //设置是否在cpu空闲时执行
        //builder.setRequiresCharging(true);   //社会是否在充电时执行
        //RECEIVE_BOOT_COMPLETED权限与开机启动有关,有了该权限之后可以监听到开机启动的广播,可以让app随开机启动而启动
        //虽然可以开机启动,但是会导致手机开机慢,而且系统会将开机启动的应用放在一个列表中,供用户自己处理
        //builder.setPersisted(true);    //重启后是否还要继续执行,需要声明权限RECEIVE_BOOT_COMPLETED

        JobScheduler js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        js.schedule(builder.build());
    }
}
