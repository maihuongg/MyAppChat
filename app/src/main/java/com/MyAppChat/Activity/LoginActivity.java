package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Model.UserModel;
import com.MyAppChat.Utils.LoginResponse;
import com.example.myappchat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button btnLogin;
    FloatingActionButton iconHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa
        editTextUsername = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.bt_go);
        iconHome = findViewById(R.id.floatingActionButton);
        //bat su kien cac nut login, floatingActionButton
        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                ApiService apiService = ApiClient.getApiService();
                apiService.login(email, password).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                        UserModel user = response.body().getUser();
                        String access = null;
                        if (response.body() != null) {
                            access = response.body().getAccess();
                        }
                        if (access != null) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            int userId = response.body().getId();
                            intent.putExtra("userId", userId);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                    }
                });

            }
        });
    }


}