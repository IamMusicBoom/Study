package com.wma.study.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.wma.study.ContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMA on 2022/1/17.
 */
public class PermissionUtils {

    /**
     * 权限申请后的处理
     *
     * @param permissions
     * @param grantResults
     */
    public static String[] requestResult(String[] permissions, int[] grantResults) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                list.add(permissions[i]);
            }
        }
        String[] notGrantedPermissions = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            notGrantedPermissions[i] = list.get(i);
        }
        return notGrantedPermissions;
    }


    /**
     * 检查某个权限是否被赋予
     *
     * @param permission
     * @return
     */
    public static boolean havePermission(String permission) {
        return havePermission(permission, "");
    }


    public static boolean havePermission(String permission, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            packageName = ContextHolder.getContext().getPackageName();
        }
        PackageManager pm = ContextHolder.getContext().getPackageManager();
        int i = pm.checkPermission(permission, packageName);
        return PackageManager.PERMISSION_GRANTED == i;
    }

    /**
     * 检查是否所有权限都被赋予
     *
     * @param permissions
     * @return
     */
    public static List<String> checkIsPermissionAllGranted(String... permissions) {
        if(permissions == null || permissions.length <= 0){
            return new ArrayList<>();
        }
        List<String> notGrantedPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (havePermission(permission)) {
                continue;
            }
            notGrantedPermissions.add(permission);
        }
        return notGrantedPermissions;
    }

    /**
     * 检查是否所有权限都被赋予
     *
     * @param permissions
     * @return
     */
    public static boolean isAllPermissionGranted(String... permissions) {
        if(permissions == null || permissions.length <= 0){
            return true;
        }
        List<String> list = checkIsPermissionAllGranted(permissions);
        return list.size() == 0;
    }


    /**
     * 是否包含存储权限
     *
     * @param permissions
     * @return
     */
    public static boolean isContainsStorage(String[] permissions) {
        if (permissions == null || permissions.length <= 0) {
            return false;
        }
        for (String permission : permissions) {
            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission) || Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 存储权限是否被赋予
     *
     * @return
     */
    public static boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                return false;
            } else {
                return true;
            }
        } else {
            return isAllPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }
}
