package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyAppChat.Adapter.FriendApdapter;
import com.example.myappchat.R;

public class FriendActivity extends Fragment {
    FriendApdapter friendApdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_of_friend_layout,container,false);



        return view;










    }
}