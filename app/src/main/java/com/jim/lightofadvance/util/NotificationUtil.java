package com.jim.lightofadvance.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;


import com.jim.lightofadvance.R;
import com.jim.lightofadvance.config.ActivityConfig;
import com.jim.lightofadvance.ui.NotificationActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;


/**
 * @author jim
 * @date 2020/5/28
 */
public class NotificationUtil {
    public static int messageCount = 10;

    public static void sendNotification(Context context, String title, String messageBody, String targetId) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationActivity.class);
        intent.putExtra(ActivityConfig.TITLE, title);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(UUID.randomUUID().hashCode(), PendingIntent.FLAG_UPDATE_CURRENT);
        if (FastClickUtil.isNotificationTime(2000))
            sendLowNotification(context,title, messageBody, targetId, pendingIntent);
        else
            sendDefaultNotification(context, title, messageBody, targetId, pendingIntent);
    }

    /**
     * 发送有声音通知
     *
     * @param title         标题
     * @param messageBody   内容
     * @param targetId      通知ID
     * @param pendingIntent 通知点击跳转
     */
    private static void sendDefaultNotification(Context context,String title, String messageBody, String targetId, PendingIntent pendingIntent) {
        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getValidRingtoneUri(context);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setAutoCancel(false)
                        .setShowWhen(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setNumber(messageCount)
                        .setVibrate(new long[]{300, 300})
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "应用更新", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        sendNotification(targetId, notificationBuilder, notificationManager);
    }

    /**
     * 发送没声音通知
     *
     * @param targetId      通知ID
     * @param title         标题
     * @param messageBody   内容
     * @param pendingIntent 通知点击跳转
     */
    private static void sendLowNotification(Context context,String title, String messageBody, String targetId, PendingIntent pendingIntent) {
        String channelId = "fcm_default_channel";

        NotificationManager notificationManager;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = channelId;
            String description = "应用更新";
            int importance = NotificationManager.IMPORTANCE_LOW;
            notificationManager = context.getSystemService(NotificationManager.class);
            NotificationChannel channel;
            channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            channel.setSound(null, null);
            notificationManager.createNotificationChannel(channel);
        } else {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent);
        sendNotification(targetId, notificationBuilder, notificationManager);
    }

    /**
     * 发送通知
     *
     * @param targetId            通知ID
     * @param notificationBuilder 通知对象
     * @param notificationManager 通知管理器
     */
    private static void sendNotification(String targetId, NotificationCompat.Builder notificationBuilder, NotificationManager notificationManager) {
        Notification notification = notificationBuilder.build();
        if (RomUtils.checkIsMiuiRom()) {
            try {
                Field field = notification.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notification);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, messageCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        notificationManager.notify(targetId, ActivityConfig.NOTIFICATION_ID, notification);
    }

    /**
     * 清除通知
     * @param context
     * @param targetId
     */
    public static void clearNotifacition(Context context,String targetId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(targetId, ActivityConfig.NOTIFICATION_ID);
    }
}
