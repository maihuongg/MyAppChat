package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myappchat.R;

public class LogOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_log_out);
        Intent intent = new Intent(LogOutActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Kết thúc Activity hiện tại
    }
}