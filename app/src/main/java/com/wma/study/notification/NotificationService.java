package com.wma.study.notification;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

public class NotificationService extends Service {

    private NotificationReceiver mReceiver;

    public NotificationService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new NotificationReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationReceiver.ACTION_SHOW_NORMAL);
        filter.addAction(NotificationReceiver.ACTION_SHOW_PROGRESS);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_TEXT);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_PICTURE);
        filter.addAction(NotificationReceiver.ACTION_SHOW_IN_BOX);
        filter.addAction(NotificationReceiver.ACTION_SHOW_HANG_UP);
        filter.addAction(NotificationReceiver.ACTION_SHOW_CUSTOM);
        filter.addAction(NotificationReceiver.ACTION_SHOW_NO_DISMISS);
        filter.addAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new NotificationBinder(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }
}