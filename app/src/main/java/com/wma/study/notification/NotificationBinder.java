package com.wma.study.notification;

import android.os.Binder;

/**
 * Created by WMA on 2021/12/27.
 */
public class NotificationBinder extends Binder {

    private NotificationService mService;

    public NotificationBinder(NotificationService mService) {
        this.mService = mService;
    }

    public NotificationService getService(){
        return mService;
    }
}
