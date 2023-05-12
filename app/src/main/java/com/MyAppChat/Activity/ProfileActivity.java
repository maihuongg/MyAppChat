package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappchat.R;

public class ProfileActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Bundle args = getArguments();
        if (args != null) {
            String value = args.getString("userId");
            // xử lý dữ liệu tại đây
        }
        return inflater.inflate(R.layout.activity_profile,container,false);
    }
}