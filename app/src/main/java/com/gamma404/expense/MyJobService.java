package com.gamma404.expense;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by forev on 2018/3/17.
 */

class MyJobService extends JobService{
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
