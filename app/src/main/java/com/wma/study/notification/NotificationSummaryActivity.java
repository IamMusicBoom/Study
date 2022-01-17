package com.wma.study.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wma.study.R;

public class NotificationSummaryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_summary);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_show_importance_notification) {
            PushManager.showNotification(new TestCondition1());
        } else if (view.getId() == R.id.btn_shrink_notification) {
            PushManager.shrinkNotification(new TestCondition1());
        }
    }
}