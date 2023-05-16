package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Model.PasswordModel;
import com.MyAppChat.Model.UpdateProfileModal;
import com.MyAppChat.Utils.ChangePasswordResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.example.myappchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    EditText edtCurrentPassword;
    EditText edtNewPassword;
    EditText edtConfirmNewPassword;
    Button btnSaveChangePassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0); // lấy userId từ login - Act-> Act
        String accessToken = intent.getStringExtra("access");

        edtCurrentPassword = (EditText) findViewById(R.id.edtCurrentPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = (EditText) findViewById(R.id.edtConfirmNewPassword);
        btnSaveChangePassword = (Button) findViewById(R.id.btnBackHome);

        btnSaveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword = edtCurrentPassword.getText().toString();
                String newPassword = edtNewPassword.getText().toString();
                String confirmNewPassword = edtConfirmNewPassword.getText().toString();

                if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All filed are required.", Toast.LENGTH_SHORT).show();
                } else {



                    String jsonString = String.format("{\"password\":\"" + currentPassword+ "\"}");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    PasswordModel passwordModel = gson.fromJson(jsonString, PasswordModel.class);
                    //Log.d("abcd", String.valueOf(test));
                    //jsonString = gson.toJson(passwordModel);
                    //Log.d("abcd", jsonString);

                    ApiService apiService = ApiClient.getApiService();
                    apiService.updatePassword("Bearer " + accessToken, passwordModel).enqueue(new Callback<ChangePasswordResponse>() {
                        @Override
                        public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                            if (response.body() != null) {
                                if(response.body().isStatus()){
                                    if(newPassword.equals(confirmNewPassword)){

                                        String jsonString = String.format("{\"password\":\"" + newPassword+ "\"}");
                                        GsonBuilder builder = new GsonBuilder();
                                        builder.setPrettyPrinting();
                                        Gson gson = builder.create();
                                        UpdateProfileModal updateProfileModal = gson.fromJson(jsonString, UpdateProfileModal.class);
                                        apiService.updateProfile("Bearer " + accessToken, updateProfileModal).enqueue(new Callback<ProfileResponse>() {
                                            @Override
                                            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                                                if (response.body() != null) {
                                                    Toast.makeText(getApplicationContext(), "Updated success", Toast.LENGTH_SHORT).show();

                                                } else Log.d("error", "loi");
                                            }

                                            @Override
                                            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "New password is different Confirm new password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Wrong current password", Toast.LENGTH_SHORT).show();
                                }
                            } else Log.d("Error", "Loi");
                        }
                        @Override
                        public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}