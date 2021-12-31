package com.wma.study.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.LongDef;
import androidx.core.app.NotificationCompat;

import com.wma.study.LogUtil;
import com.wma.study.R;

import java.util.List;

/**
 * Created by WMA on 2021/12/27.
 */
public class NotificationReceiver extends BroadcastReceiver {

    public static final String TAG = "WMA-WMA";

    private NotificationService mService;

    private NotificationUtils mUtils;

    public static final String ACTION_SHOW_NORMAL = "action_show_normal";
    public static final String ACTION_SHOW_PROGRESS = "action_show_progress";
    public static final String ACTION_SHOW_BIG_TEXT = "action_show_big_text";
    public static final String ACTION_SHOW_BIG_PICTURE = "action_show_big_picture";
    public static final String ACTION_SHOW_IN_BOX = "action_show_in_box";
    public static final String ACTION_SHOW_HANG_UP = "action_show_hang_up";
    public static final String ACTION_SHOW_CUSTOM = "action_show_custom";
    public static final String ACTION_SHOW_NO_DISMISS = "action_show_no_dismiss";
    public static final String ACTION_SHOW_ACTIVITY = "action_show_activity";

    public static final String ACTION_SHOW_BIG_LAYOUT_1 = "action_show_big_layout_1";
    public static final String ACTION_SHOW_BIG_LAYOUT_2 = "action_show_big_layout_2";
    public static final String ACTION_SHOW_BIG_LAYOUT_3 = "action_show_big_layout_3";
    public static final String ACTION_SHOW_BIG_LAYOUT_4 = "action_show_big_layout_4";
    public static final String ACTION_SHOW_BIG_LAYOUT_5 = "action_show_big_layout_5";
    public static final String ACTION_SHOW_BIG_NOTIFICATION = "action_show_big_notification";



    public NotificationReceiver(NotificationService mService) {
        this.mService = mService;
        mUtils = NotificationUtils.getInstance(mService.getApplicationContext());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.d(TAG, "onReceive: action = " + action);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel notificationChannel = manager.getNotificationChannel(NotificationUtils.HANG_UP_CHANNEL_ID);
//            if (notificationChannel != null) {
//                int importance = notificationChannel.getImportance();
//                if (importance < NotificationManager.IMPORTANCE_HIGH) {
//                    mUtils.goOpenHangUpToggle();
//                }
//            }
//        }
        if (ACTION_SHOW_NORMAL.equals(action)) {
            Notification notification = mUtils.buildNormalNotification("标题", "内容");
            mUtils.showNotification(NotificationUtils.NORMAL_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_PROGRESS.equals(action)) {
            Notification notification = mUtils.buildProgressNotification(100, 50);
            mUtils.showNotification(NotificationUtils.PROGRESS_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_BIG_TEXT.equals(action)) {
            Notification notification = mUtils.buildBigTextNotification();
            mUtils.showNotification(NotificationUtils.BIG_TEXT_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_BIG_PICTURE.equals(action)) {
            Notification notification = mUtils.buildBigPictureNotification();
            mUtils.showNotification(NotificationUtils.BIG_PICTURE_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_IN_BOX.equals(action)) {
            Notification notification = mUtils.buildInBoxNotification();
            mUtils.showNotification(NotificationUtils.IN_BOX_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_HANG_UP.equals(action)) {
            Notification notification = mUtils.buildHangUpNotification("标题", "内容");
            mUtils.showNotification(NotificationUtils.HANG_UP_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_CUSTOM.equals(action)) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_custom);
            Notification notification = mUtils.buildCustomNotification(remoteViews);
            mUtils.showNotification(NotificationUtils.CUSTOM_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_NO_DISMISS.equals(action)) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_custom);
            Notification notification = mUtils.buildNoDismissNotification(remoteViews);
            mUtils.showNotification(NotificationUtils.NO_DISMISS_NOTIFICATION_ID, notification);
        } else if (ACTION_SHOW_ACTIVITY.equals(action)) {
            mUtils.showActivityNotification();
        } else if (ACTION_SHOW_BIG_LAYOUT_1.equals(action)) {
            mUtils.showBigLayout1();
        } else if (ACTION_SHOW_BIG_LAYOUT_2.equals(action)) {
            mUtils.showBigLayout2();
        } else if (ACTION_SHOW_BIG_LAYOUT_3.equals(action)) {
            mUtils.showBigLayout3();
        } else if (ACTION_SHOW_BIG_LAYOUT_4.equals(action)) {
            mUtils.showBigLayout4();
        } else if (ACTION_SHOW_BIG_LAYOUT_5.equals(action)) {
            mUtils.showBigLayout5();
        } else if (ACTION_SHOW_BIG_NOTIFICATION.equals(action)) {
            mUtils.showBigNotification();
        }

    }
}
