package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myappchat.R;

public class ProfileActivity extends Fragment {
    TextView userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_profile,container,false);
        Bundle args = getArguments();
        userId = view.findViewById(R.id.profile_username);
        if (args != null) {
            Integer id = args.getInt("id");
            // xử lý dữ liệu tại đây
        }
        //setText

        return view;
    }
}