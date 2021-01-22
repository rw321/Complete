package com.example.complete.ui.androidbasic;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobScheduler extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        System.out.println("job start to perform");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.out.println("job finished");
        return false;
    }
}
