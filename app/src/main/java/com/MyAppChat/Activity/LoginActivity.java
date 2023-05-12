package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                ApiService apiService = ApiClient.getApiService();
                apiService.login(username, password).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                        UserModel user = response.body().getUser();
//                        String message = response.body().getDetail();
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            String userName = response.body().getUser().getUsername();
                            intent.putExtra("username", userName);

                            startActivity(intent);
                        }else{
                            String message = response.body().getDetail();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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