package com.wma.study.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;

import com.wma.study.R;

public class NotificationAdvancedActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_advanced);
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
        if (view.getId() == R.id.btn_notification_show_big_layout_1) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_1));
        } else if (view.getId() == R.id.btn_notification_show_big_layout_2) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_2));
        } else if (view.getId() == R.id.btn_notification_show_big_layout_3) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_3));
        } else if (view.getId() == R.id.btn_notification_show_big_layout_4) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_4));
        } else if (view.getId() == R.id.btn_notification_show_big_layout_5) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_LAYOUT_5));
        } else if (view.getId() == R.id.btn_notification_show_big_notification_in_all_version) {
            sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_BIG_NOTIFICATION));
        }
    }
}