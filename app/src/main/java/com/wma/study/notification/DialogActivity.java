package com.wma.study.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wma.study.R;
import com.wma.study.StudyApplication;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    public static void showActivityNotification(Context context) {
        Intent actionIntent = new Intent(context, DialogActivity.class);
        actionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        actionIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(actionIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_function_reminder_super_big_push);
        View titleView = this.findViewById(R.id.title);
        titleView.setVisibility(View.GONE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.TOP;
        attributes.width = displayMetrics.widthPixels;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(WindowManager.LayoutParams.DIM_AMOUNT_CHANGED);
        getWindow().setDimAmount(0.0f);
//        findViewById(R.id.remind_notification_layout).setBackgroundResource(R.drawable.dialog_radius_white_shape);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Sure", Toast.LENGTH_SHORT).show();
        finish();
    }
}