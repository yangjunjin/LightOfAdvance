package com.jim.lightofadvance.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jim.lightofadvance.R;
import com.jim.lightofadvance.util.NotificationUtil;

public class FitNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_notification);

        CharSequence content = getMessageText(getIntent());

        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();

    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence("key_text_reply");
        }
        return null;
    }

    public void onClick(View view) {
        NotificationUtil.sendNotification(FitNotificationActivity.this,"我是头部信息","我是内容信息","targetId");
    }
}
