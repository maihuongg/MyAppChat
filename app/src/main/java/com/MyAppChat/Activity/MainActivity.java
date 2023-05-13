package com.MyAppChat.Activity;

import android.content.Intent;
import android.os.Bundle;

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
        setContentView(binding.getRoot());
        //xử lý put, get, intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("id",0); // lấy userId từ login - Act-> Act

        replaceFragment(new HomeActivity());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeActivity());
                    break;

                case R.id.friend:
                    replaceFragment(new FriendActivity());
                    break;

                case R.id.person:
                    Process p=null;
                    try {
                        p = new ProcessBuilder()
                                .command("token.js")
                                .start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(p!=null) p.destroy();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ProfileActivity profileActivity = new ProfileActivity();
                    fragmentTransaction.replace(R.id.frame_layout, profileActivity);
                    Bundle bundle =new Bundle();
                    bundle.putInt("id",userId);
                    profileActivity.setArguments(bundle);
                    fragmentTransaction.commit();
//                    replaceFragment(new ProfileActivity());
                    break;

                case R.id.settings:
                    replaceFragment(new SettingActivity());
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