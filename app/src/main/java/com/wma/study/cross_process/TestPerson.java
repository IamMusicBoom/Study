package com.wma.study.cross_process;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wma.study.LogUtil;

/**
 * Created by WMA on 2022/1/12.
 */
public class TestPerson {
    public static int TEST_AGE = 20;

    public static final String ACTION_TEST = "ACTION_TEST";


    static class TestReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            TEST_AGE++;
            LogUtil.d("TAG_WMA", "onReceive: " + TEST_AGE);
        }
    }
}
