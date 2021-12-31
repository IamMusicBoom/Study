package com.wma.study.notification;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.wma.study.LogUtil;
import com.wma.study.StudyApplication;

public class NotificationService extends Service {



    private NotificationReceiver mReceiver;

    public NotificationService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("WMA-WMA", "onCreate: ");
        NotificationUtils notificationUtils = NotificationUtils.getInstance(StudyApplication.getContext());
        startForeground(1001, notificationUtils.getForegroundNotification());
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
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_1);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_2);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_3);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_4);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_5);
        filter.addAction(NotificationReceiver.ACTION_SHOW_BIG_NOTIFICATION);
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