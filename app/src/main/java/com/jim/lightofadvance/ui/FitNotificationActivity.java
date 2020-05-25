package com.jim.lightofadvance.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jim.lightofadvance.R;
import com.jim.lightofadvance.util.NotificationUtil;

public class FitNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_notification);
    }

    public void onClick(View view) {
        NotificationUtil.sendNotification(FitNotificationActivity.this,"我是头部信息","我是内容信息","targetId");
    }
}
