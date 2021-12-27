package com.wma.study.notification;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wma.study.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        View titleView = this.findViewById(R.id.title);
        titleView.setVisibility(View.GONE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.TOP;
        attributes.width = displayMetrics.widthPixels;  ;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(WindowManager.LayoutParams.DIM_AMOUNT_CHANGED);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Sure", Toast.LENGTH_SHORT).show();
        finish();
    }
}