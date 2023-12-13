package com.example.xxqg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 2000; // 延迟时间，单位毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 使用 Handler 延迟跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 创建一个意图，用于跳转到 MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                // 启动 MainActivity
                startActivity(intent);
                // 关闭 WelcomeActivity
                finish();
            }
        }, DELAY_TIME);
    }
}