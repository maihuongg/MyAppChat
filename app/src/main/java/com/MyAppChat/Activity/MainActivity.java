package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.myappchat.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //will hide the title not the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //int flag, int mask
        setContentView(R.layout.activity_main);
        drawerLayout= findViewById(R.id.drawer_layout);
//
//// Open the navigation drawer
        drawerLayout.openDrawer(GravityCompat.START);
//
//// Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

    }
}