package com.wma.study.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.wma.study.R;

/**
 * Created by WMA on 2021/12/27.
 */
public class NotificationUtils {

    // 普通通知
    public static final int NORMAL_NOTIFICATION_ID = 1;
    private final String NORMAL_CHANNEL_ID = "NORMAL_CHANNEL_ID";
    private final String NORMAL_CHANNEL_NAME = "普通通知名称";

    // 下载通知
    public static final int PROGRESS_NOTIFICATION_ID = 2;
    private final String PROGRESS_CHANNEL_ID = "PROGRESS_CHANNEL_ID";
    private final String PROGRESS_CHANNEL_NAME = "下载通知名称";


    // BigText通知
    public static final int BIG_TEXT_NOTIFICATION_ID = 3;
    private final String BIG_TEXT_CHANNEL_ID = "BIG_TEXT_CHANNEL_ID";
    private final String BIG_TEXT_CHANNEL_NAME = "BIG_TEXT通知名称";

    // BigPicture通知
    public static final int BIG_PICTURE_NOTIFICATION_ID = 4;
    private final String BIG_PICTURE_CHANNEL_ID = "BIG_PICTURE_CHANNEL_ID";
    private final String BIG_PICTURE_CHANNEL_NAME = "BIG_PICTURE通知名称";

    // InBox通知
    public static final int IN_BOX_NOTIFICATION_ID = 5;
    private final String IN_BOX_CHANNEL_ID = "IN_BOX_CHANNEL_ID";
    private final String IN_BOX_CHANNEL_NAME = "IN_BOX通知名称";


    // HangUp通知
    public static final int HANG_UP_NOTIFICATION_ID = 6;
    public static final String HANG_UP_CHANNEL_ID = "HANG_UP_CHANNEL_ID";
    private final String HANG_UP_CHANNEL_NAME = "HANG_UP通知名称";

    public static final int CUSTOM_NOTIFICATION_ID = 7;
    public static final int NO_DISMISS_NOTIFICATION_ID = 8;
    private Context mContext;

    private static NotificationUtils mSelf;

    private NotificationUtils(Context context) {
        this.mContext = context;
    }

    public static NotificationUtils getInstance(Context context) {

        synchronized (NotificationUtils.class) {
            if (mSelf == null) {
                mSelf = new NotificationUtils(context);
            }
            return mSelf;
        }
    }


    /**
     * 创建一个通知渠道
     *
     * @param channelID   渠道ID
     * @param channelNAME 渠道名称
     * @param level       渠道重要程度
     * @return
     */
    private String createNotificationChannel(String channelID, String channelNAME, int level) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelID, channelNAME, level);
            notificationManager.createNotificationChannel(channel);
            return channelID;
        } else {
            return null;
        }
    }

    public Notification buildNormalNotification(String title, String content) {
        String channel = createNotificationChannel(NORMAL_CHANNEL_ID, NORMAL_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);// 一般通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .build();
        return notification;
    }

    public Notification buildProgressNotification(int max, int progress) {
        String channel = createNotificationChannel(PROGRESS_CHANNEL_ID, PROGRESS_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);// 一般通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setContentTitle("下载中")
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setProgress(max, progress, false)
                .setContentText(progress * 1f / max * 100 + "%")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .build();
        return notification;
    }

    public Notification buildBigTextNotification() {
        String channel = createNotificationChannel(BIG_TEXT_CHANNEL_ID, BIG_TEXT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);// 一般通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("BigTextStyle")
//                .setContentText("BigTextStyle演示示例")// 没用
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_large_50dp))// 没用
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(mContext.getString(R.string.bit_text))
                        .setBigContentTitle("BigTextTitle")
                        .setSummaryText("BigTextTitle简介"))
                .build();
        return notification;
    }

    public Notification buildBigPictureNotification() {
        String channel = createNotificationChannel(BIG_PICTURE_CHANNEL_ID, BIG_PICTURE_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);// 一般通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("BigPictureStyle")
                .setContentText("BigPictureStyle演示示例")
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.yunzhi))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.yunzhi))
                        .setBigContentTitle("BigPictureTitle")
                        .setSummaryText("BigPicture简介")
                        .bigLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.yunzhi)))
                .build();
        return notification;
    }

    public Notification buildInBoxNotification() {
        String channel = createNotificationChannel(IN_BOX_CHANNEL_ID, IN_BOX_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);// 一般通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("InBoxStyle")
                .setContentText("InBoxStyle演示示例")
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.yunzhi))
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                        .addLine("第二行")
                        .addLine("第三行")
                        .addLine("第四行")
                        .addLine("第五行")
                        .setBigContentTitle("InBoxTitle")
                        .setSummaryText("InBox简介")
                )
                .build();
        return notification;
    }


    public Notification buildHangUpNotification(String title, String content) {
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setContentTitle(title)
                .setContentText(content)
                .setVibrate(new long[]{2000, 2000})
                .setContentIntent(activities)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build();
        return notification;
    }


    public Notification buildNoDismissNotification(RemoteViews remoteViews) {
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        PendingIntent fullPendingIntent = PendingIntent.getActivity(mContext, 10, new Intent(), PendingIntent.FLAG_MUTABLE);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setDefaults(Notification.DEFAULT_ALL)
                .setShowWhen(true)
                .setContentIntent(activities)
                .setFullScreenIntent(fullPendingIntent, true)// 可以是一个不消失的通知，需要增加权限 <uses-permission android:name="android.permission.USE_FULL_SCREEN_INTENT"/>
                .setCustomBigContentView(remoteViews)
//                .setCustomHeadsUpContentView(remoteViews)
//                .setVibrate(new long[]{2000,2000})
//                .setSound(uri)
//                .setCustomContentView(remoteViews)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        return notification;
    }

    public Notification buildCustomNotification(RemoteViews remoteViews) {
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
//                .setCustomContentView(remoteViews)
//                .setTimeoutAfter(2000)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        return notification;
    }

    public void showBigLayout1() {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setContent(superBigView)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(101, notification);
    }

    public void showBigLayout2() {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setCustomContentView(superBigView)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(102, notification);
    }

    public void showBigLayout3() {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setCustomBigContentView(superBigView)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(103, notification);
    }

    public void showBigLayout4() {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setContent(superBigView)
                .setCustomContentView(superBigView)
                .setCustomBigContentView(superBigView)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(104, notification);
    }

    public void showBigLayout5() {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        RemoteViews bigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_big_push);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent activities = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setCustomContentView(bigView)
                .setCustomBigContentView(superBigView)
                .setFullScreenIntent(activities, true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(105, notification);
    }


    public void showBigNotification() {
        int channelLevel = NotificationManager.IMPORTANCE_HIGH;
        int notificationLevel = Notification.PRIORITY_HIGH;

        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        RemoteViews bigView;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            channelLevel = NotificationManager.IMPORTANCE_MIN;
            notificationLevel = Notification.PRIORITY_MIN;
            bigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_big_push);
        } else {
            channelLevel = NotificationManager.IMPORTANCE_HIGH;
            notificationLevel = Notification.PRIORITY_HIGH;
            bigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        }
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ACTION_SHOW_ACTIVITY);
        PendingIntent fullScreenIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_MUTABLE);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, channelLevel);// 重要通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, channel);

        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setFullScreenIntent(fullScreenIntent, true)
                .setCustomContentView(bigView)
                .setCustomBigContentView(superBigView)
                .setTimeoutAfter(2000)
                .setPriority(notificationLevel);
        Notification notification = builder.build();

        showNotification(106, notification);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = manager.getNotificationChannel(NotificationUtils.HANG_UP_CHANNEL_ID);
                if (notificationChannel != null) {
                    int importance = notificationChannel.getImportance();
                    if (importance < NotificationManager.IMPORTANCE_HIGH) {
                        fullScreenIntent.send();
                        return;
                    }
                }
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                fullScreenIntent.send();
            }
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();

        }
    }


    public void showNotification(int id, Notification notification) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(id, notification);
    }

    public void showActivityNotification() {
//        Intent actionIntent = new Intent(mContext, DialogActivity.class);
//        actionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(actionIntent);
        DialogActivity.showActivityNotification(mContext);
    }

    public void goOpenHangUpToggle() {
        Intent permissionIntent = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            permissionIntent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            permissionIntent.putExtra(Settings.EXTRA_APP_PACKAGE, mContext.getPackageName());
            permissionIntent.putExtra(Settings.EXTRA_CHANNEL_ID, NotificationUtils.HANG_UP_CHANNEL_ID);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            permissionIntent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            permissionIntent.putExtra("app_package", mContext.getPackageName());
            permissionIntent.putExtra("app_uid", mContext.getApplicationInfo().uid);
        } else {
            permissionIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            permissionIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        }
        permissionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(permissionIntent);
    }


    public Notification getForegroundNotification() {

        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setContentTitle("通知栏")
                .setContentText("通知栏前台服务")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        return notification;
    }

    public void showNetImg(Uri bitmap) {
        RemoteViews superBigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_super_big_push);
        RemoteViews bigView = new RemoteViews(mContext.getPackageName(), R.layout.layout_function_reminder_big_push);
        superBigView.setImageViewUri(R.id.iv_icon, bitmap);
        bigView.setImageViewUri(R.id.iv_icon, bitmap);
        String channel = createNotificationChannel(HANG_UP_CHANNEL_ID, HANG_UP_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);// 重要通知
        Notification notification = new NotificationCompat.Builder(mContext, channel)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_small_24dp)
                .setShowWhen(true)
                .setCustomContentView(bigView)
                .setCustomBigContentView(superBigView)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        showNotification(105, notification);
    }
}
