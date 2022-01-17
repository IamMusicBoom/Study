package com.wma.study.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.LongDef;
import androidx.core.app.NotificationCompat;

import com.wma.study.ContextHolder;
import com.wma.study.LogUtil;
import com.wma.study.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created by WMA on 2021/12/27.
 */
public class NotificationReceiver extends BroadcastReceiver {

    public static final String TAG = "WMA-WMA";

    private final String imgUrl = "http://www.xinhuanet.com/photo/titlepic/112826/1128262326_1642131076734_title0h.jpg";


    private NotificationService mService;

    private NotificationUtils mUtils;

    public static final String KEY_BITMAP = "key_bitmap";

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
    public static final String ACTION_SHOW_NET_IMAGE = "action_show_net_image";



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
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_function_reminder_big_push);
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
        } else if(ACTION_SHOW_NET_IMAGE.equals(action)){
           saveImage(imgUrl);
        }

    }


    private void saveImage(String imgUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imgUrl);
                    //打开输入流
                    InputStream inputStream = null;
                    inputStream = url.openStream();
                    //对网上资源进行下载转换位图图片
                    Bitmap mBitmap = BitmapFactory.decodeStream(inputStream);
                    saveFile(mBitmap);
//                    mUtils.showNetImg(mBitmap);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 保存图片
     * @param bm
     * @throws IOException
     */
    public void saveFile(Bitmap bm ) throws IOException {
        File dirFile = new File(ContextHolder.getContext().getExternalCacheDir().getAbsolutePath());
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File myCaptureFile = new File(ContextHolder.getContext().getExternalCacheDir().getAbsolutePath() + "/" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        Uri uri = Uri.parse(myCaptureFile.getAbsolutePath());
        mUtils.showNetImg(uri);
//        //把图片保存后声明这个广播事件通知系统相册有新图片到来
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri uri = Uri.fromFile(myCaptureFile);
//        intent.setData(uri);
//        context.sendBroadcast(intent);
    }

}
