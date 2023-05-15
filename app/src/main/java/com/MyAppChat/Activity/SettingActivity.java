package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myappchat.R;

public class SettingActivity extends Fragment {

    Button btnLogout;
    Button btnChangePassword;
    Button btnDarkTheme;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.setting_layout,container,false);
        Bundle args = getArguments();

        btnLogout = view.findViewById(R.id.btnLogout);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnDarkTheme = view.findViewById(R.id.btnDarkTheme);

        int id = 0;
        String accessToken = "";
        if (args != null) {
            id = args.getInt("id");
            accessToken = args.getString("access");
            // xử lý dữ liệu tại đây
        }
        String finalAccessToken = accessToken;
        Log.d("accessTokenSetting", finalAccessToken);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                intent.putExtra("access", finalAccessToken);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}