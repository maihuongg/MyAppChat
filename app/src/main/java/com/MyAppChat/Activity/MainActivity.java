package com.MyAppChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myappchat.R;
import com.example.myappchat.databinding.ActivityMainBinding;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        //xử lý put, get, intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0); // lấy userId từ login - Act-> Act
        String accessToken = intent.getStringExtra("access");
        FragmentManager fragmentManager5 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction5 = fragmentManager5.beginTransaction();
        HomeActivity homeActivity1 = new HomeActivity();
        fragmentTransaction5.replace(R.id.frame_layout, homeActivity1);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("id", userId);
        bundle5.putString("access", accessToken);
        homeActivity1.setArguments(bundle5);
        fragmentTransaction5.commit();
//        replaceFragment(new HomeActivity());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    HomeActivity homeActivity = new HomeActivity();
                    fragmentTransaction2.replace(R.id.frame_layout, homeActivity);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("id", userId);
                    bundle2.putString("access", accessToken);
                    homeActivity.setArguments(bundle2);
                    fragmentTransaction2.commit();
                    //replaceFragment(new HomeActivity());
                    break;

                case R.id.friend:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                    FriendActivity friendActivity = new FriendActivity();
                    fragmentTransaction3.replace(R.id.frame_layout, friendActivity);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("id", userId);
                    bundle3.putString("access", accessToken);
                    friendActivity.setArguments(bundle3);
                    fragmentTransaction3.commit();
                    //replaceFragment(new FriendActivity());
                    break;

                case R.id.person:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ProfileActivity profileActivity = new ProfileActivity();
                    fragmentTransaction.replace(R.id.frame_layout, profileActivity);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", userId);
                    bundle.putString("access", accessToken);
                    profileActivity.setArguments(bundle);
                    fragmentTransaction.commit();
//                    replaceFragment(new ProfileActivity());
                    break;

                case R.id.settings:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    SettingActivity settingActivity = new SettingActivity();
                    fragmentTransaction1.replace(R.id.frame_layout, settingActivity);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("id", userId);
                    bundle1.putString("access", accessToken);
                    settingActivity.setArguments(bundle1);
                    fragmentTransaction1.commit();
                    //replaceFragment(new SettingActivity());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}