package com.wma.study;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.wma.study.notification.NotificationService;

/**
 * Created by WMA on 2021/12/30.
 */
public class StudyApplication extends Application {
    private static StudyApplication mStudyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mStudyApplication = this;
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public static Context getContext() {
        return mStudyApplication.getApplicationContext();
    }
}
