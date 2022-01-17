package com.wma.study;

import android.content.Context;

/**
 * Created by WMA on 2022/1/14.
 */
public class ContextHolder {
    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }

    public static void setContext(Context context){
        mContext = context;
    }
}
