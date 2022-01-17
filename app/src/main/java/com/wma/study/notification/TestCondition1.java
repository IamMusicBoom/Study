package com.wma.study.notification;

import com.wma.study.ContextHolder;
import com.wma.study.R;

/**
 * Created by WMA on 2022/1/14.
 */
public class TestCondition1 implements IPushCondition{
    @Override
    public int getPushId() {
        return 101;
    }

    @Override
    public int getViewType() {
        return IPushCondition.BIG_VIEW;
    }

    @Override
    public int getPushIconId() {
        return R.drawable.ic_small_24dp;
    }

    @Override
    public CharSequence getPushDesc() {
        return ContextHolder.getContext().getString(R.string.app_name);
    }

    @Override
    public CharSequence getPushButtonText() {
        return ContextHolder.getContext().getString(R.string.app_name);
    }

    @Override
    public long getShowTime() {
        return 500;
    }
}
