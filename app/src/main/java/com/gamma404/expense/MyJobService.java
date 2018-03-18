package com.gamma404.expense;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by forev on 2018/3/17.
 */

class MyJobService extends JobService{
    private static final String TAG = MyJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG, "onStartJob: ");
        NotificationService.startNotification(getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
