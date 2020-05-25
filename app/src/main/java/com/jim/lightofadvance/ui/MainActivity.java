package com.jim.lightofadvance.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jim.lightofadvance.R;
import com.jim.lightofadvance.ui.RecycleViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecycleView:
                //RecycleView的使用
                startActivity(new Intent(this, RecycleViewActivity.class));
                break;
            case R.id.btnNotification:
                //RecycleView的使用
                startActivity(new Intent(this, NotificationActivity.class));
                break;
        }
    }
}
