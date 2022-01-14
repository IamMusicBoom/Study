package com.wma.study.cross_process;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wma.study.LogUtil;
import com.wma.study.R;

public class LocalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_broadcast){
            LogUtil.d("TAG_WMA", "onClick: " + TestPerson.TEST_AGE);
            sendBroadcast(new Intent(TestPerson.ACTION_TEST));
        }
    }
}