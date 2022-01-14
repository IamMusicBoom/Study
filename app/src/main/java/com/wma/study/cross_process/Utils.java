package com.wma.study.cross_process;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by WMA on 2022/1/12.
 */
public class Utils {

    public static String getProcessName(Context context) {
        File cmdFile = new File("/proc/self/cmdline");

        if (cmdFile.exists() && !cmdFile.isDirectory()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmdFile)));
                String procName = reader.readLine();

                if (!TextUtils.isEmpty(procName))
                    return procName.trim();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // try to fix SELinux limit due to unable access /proc file system
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (null != am) {
                List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = am.getRunningAppProcesses();
                if (null != appProcessInfoList) {
                    for (ActivityManager.RunningAppProcessInfo i : appProcessInfoList) {
                        if (i.pid == android.os.Process.myPid()) {
                            return i.processName.trim();
                        }
                    }
                }
            }
        }

        // Warnning: getApplicationInfo().processName only return package name for some reason, you will not see
        // the real process name, such as com.cleanmaster.mguard:service
        return context.getApplicationInfo().processName;
    }
}
