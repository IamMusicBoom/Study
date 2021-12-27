package com.wma.study;

import android.util.Log;

/**
 * Created by WMA on 2021/12/27.
 */
public class LogUtil {
    public static void d(String tag, String message) {
        Log.d(Constants.TAG + "_" + tag, message);
    }
}
