package com.wma.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wma.study.notification.NotificationAdvancedActivity;
import com.wma.study.notification.NotificationBasicActivity;
import com.wma.study.notification.NotificationService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_notification_basic) {
            startActivity(new Intent(this, NotificationBasicActivity.class));
        }else if(view.getId() == R.id.btn_notification_advanced){
            startActivity(new Intent(this, NotificationAdvancedActivity.class));
        }
    }
}