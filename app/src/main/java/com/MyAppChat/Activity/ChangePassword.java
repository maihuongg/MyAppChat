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
import com.MyAppChat.Utils.ChangePasswordResponse;
import com.example.myappchat.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        btnSaveChangePassword = (Button) findViewById(R.id.btnSaveChangePassword);

        btnSaveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword = edtCurrentPassword.getText().toString();
                String newPassword = edtNewPassword.getText().toString();
                String confirmNewPassword = edtConfirmNewPassword.getText().toString();

                if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All filed are required.", Toast.LENGTH_SHORT).show();
                } else {

                    JSONObject obj;
                    try {
                        obj = new JSONObject();
                        obj.put("password", currentPassword);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
//                    Log.d("abc", currentPassword);
                    Log.d("abc", obj.toString());

                    ApiService apiService = ApiClient.getApiService();
                    apiService.updatePassword("Bearer " + accessToken, obj).enqueue(new Callback<ChangePasswordResponse>() {
                        @Override
                        public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                            if (response.body() != null) {
                                if(response.body().isStatus()){
                                    if(newPassword == confirmNewPassword){

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