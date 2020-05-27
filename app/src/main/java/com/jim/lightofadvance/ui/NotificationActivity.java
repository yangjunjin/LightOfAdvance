package com.jim.lightofadvance.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.jim.lightofadvance.R;

/**
 * 通知的样式
 */
public class NotificationActivity extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //最基本、精简形式（也称为折叠形式）
    public void sendNomalNotification(View view) {

        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 50;

        Intent intent = new Intent(this, FitNotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String CHANNEL_ID = "channel_id";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                //通知图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //通知title
                .setContentTitle("ContentTitle")
                //通知内容
                .setContentText("Much longer text that cannot fit one line.Much longer text that cannot fit one line,Much longer text that cannot fit one line,Much longer text that cannot fit one lineMuch longer text that cannot fit one line")
                //设置大文本
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                //设置点击跳转的类
                .setContentIntent(pendingIntent)
                //设置通知的等级
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //用户点按通知后自动移除通知
                .setAutoCancel(true);
        //进度条
        //.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
        //指定时间消失
        //.setTimeoutAfter(1000);
        //添加操作按钮
        //.addAction(R.mipmap.ic_launcher, "Action", pendingIntent);
        //.addAction(getAction());

        //适配8.0,不然显示不了喔
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, builder.build());
    }

    /**
     * 创建展开式通知
     *
     * @param view
     */
    public void sendSpreadNotification(View view) {
        NotificationCompat.MessagingStyle.Message message1 =
                new NotificationCompat.MessagingStyle.Message("title1",
                        System.currentTimeMillis(), "Sender1");
        NotificationCompat.MessagingStyle.Message message2 =
                new NotificationCompat.MessagingStyle.Message("title2",
                        System.currentTimeMillis(), "Sender2");

        Intent intent = new Intent(this, FitNotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Bundle bundle = new Bundle();
        bundle.putString("name","name1");
        Person person = Person.fromBundle(bundle);

        String CHANNEL_ID = "CHANNEL_ID";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Notification notification = new NotificationCompat.Builder(NotificationActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("imageTitle")
                .setContentText("imageDescription")
                //显示图片
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(bitmap)
//                        .bigLargeIcon(bitmap))
                //显示文本
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("面向Android 应用开发者的官方网站。提供 Android SDK 工具和 API 文档。... Android 11 开发者预览版现已推出,该预览版增强了对用户隐私的保护,提供了吸引用户的新..."))

                //创建收件箱样式的通知
//                .setStyle(new NotificationCompat.InboxStyle()
//                        .addLine("11我是最刷机的发到的发到的的的地方的的的的的辅导费地方的地方的的发到发")
//                        .addLine("22我是最刷机的发到的发到的的的地方的的的的的辅导费地方的地方的的发到发"))

                //在通知中显示对话
//                .setStyle(new NotificationCompat.MessagingStyle(person)
//                        .addMessage(message1)
//                        .addMessage(message2))

                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);
    }


    public void sendHangNotification(View view) {
        String CHANNEL_ID = "channel_id";
        Intent fullScreenIntent = new Intent(this, FitNotificationActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setTimeoutAfter(3000);
        notificationManager.notify(1, builder.build());
    }


    //7.0添加回复动作
    public NotificationCompat.Action getAction() {
        Intent intent = new Intent(this, FitNotificationActivity.class);
        intent.putExtra("name", "内容");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = "reply_label";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        // Build a PendingIntent for the reply action to trigger.
        PendingIntent replyPendingIntent =
                PendingIntent.getBroadcast(getApplicationContext(),
                        1,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input.
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
                        "label", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
        return action;
    }
    String CHANNEL_ID = "channel_id";
    public void sendTimeLinessNotification(View view) {
        Intent fullScreenIntent = new Intent(this, FitNotificationActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.app_icon)
                        .setContentTitle("Incoming call")
                        .setContentText("(919) 555-1234")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_CALL)

                        .setFullScreenIntent(fullScreenPendingIntent, true);

        notificationManager.notify(1, notificationBuilder.build());

    }
}
