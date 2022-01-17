package com.wma.study.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;

import com.wma.study.LogUtil;
import com.wma.study.R;
import com.wma.study.base.RequestPermissionActivity;
import com.wma.study.utils.PermissionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class NotificationAdvancedActivity extends RequestPermissionActivity implements View.OnClickListener {




    private NotificationService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_advanced);
        Intent serviceIntent = new Intent(this, NotificationService.class);
        bindService(serviceIntent, conn, BIND_AUTO_CREATE);
    }

    @Override
    public String[] getPermissions() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    }

    @Override
    public void onPermissionGranted() {
        LogUtil.d("TAG_WMA", "onPermissionGranted: ");
//        downloadImage(imgUrl);

        sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_NET_IMAGE));

    }

    @Override
    public void onPermissionDenied(String[] permissions, boolean isNeverTips) {
        LogUtil.d("TAG_WMA", "onPermissionGranted: " + Arrays.toString(permissions) + " isNeverTips = " + isNeverTips);

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
        } else if (view.getId() == R.id.btn_notification_show_net_img) {
            if (!isPermissionAllGranted()) {
                requestPermissions();
            } else {
                sendBroadcast(new Intent(NotificationReceiver.ACTION_SHOW_NET_IMAGE));
            }
        }
    }
}