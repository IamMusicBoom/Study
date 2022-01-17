package com.wma.study.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.wma.study.ContextHolder;
import com.wma.study.R;

/**
 * Created by WMA on 2022/1/14.
 */
public class PushManager {
    private static final String SHOW_CHANNEL_ID = "show_channel_id";
    private static final String SHOW_CHANNEL_NAME = "show_channel_name";

    private static final String SHRINK_CHANNEL_ID = "shrink_channel_id";
    private static final String SHRINK_CHANNEL_NAME = "shrink_channel_name";


    private static NotificationManagerCompat mManger = null;

    static {
        mManger = NotificationManagerCompat.from(ContextHolder.getContext());
    }


    /**
     * show通知
     *
     * @return
     */
    public static void showNotification(IPushCondition condition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createImportanceChannel();
        }
        NotificationCompat.Builder builder = createBuilder(condition, SHOW_CHANNEL_ID);
        Notification notification = builder.build();
        mManger.notify(condition.getPushId(), notification);
    }


    /**
     * 收起通知
     *
     * @param condition
     */
    public static void shrinkNotification(IPushCondition condition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createDefaultChannel();
        }
        NotificationCompat.Builder builder = createBuilder(condition, SHRINK_CHANNEL_ID);
        Notification notification = builder.build();
        mManger.notify(condition.getPushId(), notification);
    }


    /**
     * 取消通知
     * @param condition
     */
    public static void cancelNotification(IPushCondition condition) {
        mManger.cancel(condition.getPushId());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createImportanceChannel() {
        NotificationChannel channel = mManger.getNotificationChannel(SHOW_CHANNEL_ID);
        if (channel == null) {
            channel = new NotificationChannel(SHOW_CHANNEL_ID, SHOW_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mManger.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createDefaultChannel() {
        NotificationChannel channel = mManger.getNotificationChannel(SHRINK_CHANNEL_ID);
        if (channel == null) {
            channel = new NotificationChannel(SHRINK_CHANNEL_ID, SHRINK_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mManger.createNotificationChannel(channel);
        }
    }

    private static NotificationCompat.Builder createBuilder(IPushCondition condition, String channelId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ContextHolder.getContext(), channelId);
        RemoteViews remoteView = getRemoteViewsByCondition(condition);
        RemoteViews smallRemoteView = getRemoteViewsByLayoutId(R.layout.layout_function_reminder_push, condition);
        builder.setSmallIcon(R.drawable.ic_small_24dp);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P || (RomUtils.isMiui() && Build.VERSION.SDK_INT == Build.VERSION_CODES.P)) { // Android P 以下设置 BigContentView 后，悬挂通知无法显示完全，需要一个小布局的展示
            builder.setCustomContentView(smallRemoteView);
        } else {
            builder.setCustomContentView(remoteView);
        }
        if (RomUtils.isVivo() && Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {// 适配vivo，悬挂也会拿BigView
            builder.setCustomBigContentView(smallRemoteView);
        } else {
            builder.setCustomBigContentView(remoteView);
        }
        if (SHOW_CHANNEL_ID.equals(channelId)) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            PendingIntent fullScreenIntent = createFullScreenIntent();
            builder.setFullScreenIntent(fullScreenIntent, true);
        } else {
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }
        return builder;
    }

    private static RemoteViews getRemoteViewsByCondition(IPushCondition condition) {
        int remoteViewId = R.layout.layout_function_reminder_push;
        if (condition.getViewType() == IPushCondition.SUPER_BIG_VIEW) {
            remoteViewId = R.layout.layout_function_reminder_super_big_push;
        } else if (condition.getViewType() == IPushCondition.BIG_VIEW) {
            remoteViewId = R.layout.layout_function_reminder_big_push;
        } else {
            remoteViewId = R.layout.layout_function_reminder_push;
        }
        return getRemoteViewsByLayoutId(remoteViewId, condition);
    }


    private static RemoteViews getRemoteViewsByLayoutId(int remoteViewId, IPushCondition condition) {
        RemoteViews remoteView = new RemoteViews(ContextHolder.getContext().getPackageName(), remoteViewId);
        remoteView.setImageViewResource(R.id.iv_icon, condition.getPushIconId());
        remoteView.setTextViewText(R.id.tv_push_desc, condition.getPushDesc());
        remoteView.setTextViewText(R.id.tv_push_button, condition.getPushButtonText());
        return remoteView;
    }

    private static PendingIntent createFullScreenIntent() {
        Intent intent = new Intent(ContextHolder.getContext(), DialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ContextHolder.getContext(), 201, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


}
