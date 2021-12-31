package com.wma.study.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.wma.study.R;

public class NotificationBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationService mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent serviceIntent = new Intent(this, NotificationService.class);
        bindService(serviceIntent, conn, BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof NotificationBinder) {
                NotificationBinder binder = ((NotificationBinder) iBinder);
                mService = binder.getService();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_notification_show_normal) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_NORMAL));
        } else if (view.getId() == R.id.btn_notification_show_progress) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_PROGRESS));
        } else if (view.getId() == R.id.btn_notification_show_big_text) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_TEXT));
        } else if (view.getId() == R.id.btn_notification_show_big_picture) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_PICTURE));
        } else if (view.getId() == R.id.btn_notification_show_in_box) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_IN_BOX));
        } else if (view.getId() == R.id.btn_notification_show_hang_up) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_HANG_UP));
        } else if (view.getId() == R.id.btn_notification_show_custom) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_CUSTOM));
        } else if (view.getId() == R.id.btn_notification_show_no_dismiss) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_NO_DISMISS));
        } else if (view.getId() == R.id.btn_notification_show_activity) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_ACTIVITY));
        }
    }
}