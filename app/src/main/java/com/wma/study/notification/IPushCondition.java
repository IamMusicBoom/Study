package com.wma.study.notification;

/**
 * Created by WMA on 2022/1/14.
 */
public interface IPushCondition {

    int NORMAL_VIEW = 0;
    int BIG_VIEW = 1;
    int SUPER_BIG_VIEW = 2;

    int getPushId();

    int getViewType();

    int getPushIconId();

    CharSequence getPushDesc();

    CharSequence getPushButtonText();

    long getShowTime();
}
