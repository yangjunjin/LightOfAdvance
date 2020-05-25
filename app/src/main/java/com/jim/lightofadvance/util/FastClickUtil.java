package com.jim.lightofadvance.util;


import android.util.Log;

import org.jetbrains.annotations.NotNull;

/**
 * 防重复点击
 */
public class FastClickUtil {
    private static long startTime = 0;
    private static long exitTime = 0;
    public static long messageTime = 0;
    private static long notificationTime = 0;
    private static String thisClickId = null;

    /**
     *
     * @param doubleClickTime 小于这个时间的就是重复点击
     */
    public static boolean isFastClick(long doubleClickTime){
        long endTime = System.currentTimeMillis();
        long l = endTime - startTime;
        startTime = System.currentTimeMillis();
        return l < doubleClickTime;
    }

    /**
     *
     * @param doubleClickTime 小于这个时间的就是重复点击
     */
    public static boolean isFastClick(@NotNull String clickId, long doubleClickTime){
        boolean isClickAgain = clickId.equals(thisClickId);
        thisClickId = clickId;
        long endTime = System.currentTimeMillis();
        long l = endTime - startTime;
        startTime = System.currentTimeMillis();
        return l < doubleClickTime && isClickAgain;
    }

    /**
     * 退出应用时间间隔
     * @param doubleClickTime 间隔
     */
    public static boolean isExit(long doubleClickTime){
        long endTime = System.currentTimeMillis();
        long l = endTime - exitTime;
        exitTime = System.currentTimeMillis();
        return l < doubleClickTime;
    }

    /**
     * 发送消息时间间隔
     * @param nextMessagePre 间隔
     */
    public static boolean isMessageTime(long nextMessagePre){
        Log.i("isMessageTime == " ,messageTime + "    " + nextMessagePre);
        boolean isShowTime = messageTime < nextMessagePre - (3 * 60 * 1000);
        messageTime = nextMessagePre;
        return isShowTime;
    }

    /**
     * 发送通知间隔
     * @param doubleClickTime 间隔
     */
    public static boolean isNotificationTime(long doubleClickTime){
        long endTime = System.currentTimeMillis();
        long l = endTime - notificationTime;
        notificationTime = System.currentTimeMillis();
        return l < doubleClickTime;
    }
}
