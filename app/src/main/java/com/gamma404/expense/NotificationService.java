package com.gamma404.expense;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by forev on 2018/3/18.
 */

public class NotificationService extends IntentService {

    private static final String TAG = NotificationService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public NotificationService() {
        super(TAG);
    }

    public static void startNotification(Context applicationContext) {
        Intent intent = new Intent(applicationContext, NotificationService.class);
        applicationContext.startService(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // TODO: 2018/3/18 Eric Job
    }
}
