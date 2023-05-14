package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Utils.RegisterRespone;
import com.example.myappchat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtFN,edtLN,edtEmail,edtBday,edtPwd,edtConfirm;
    RadioButton rdFemale,rdMale;
    Button btnRegister,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ẩn thanh tiêu đề
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        edtFN= findViewById(R.id.et_FirstName);
        edtLN= findViewById(R.id.et_lastname);
        edtEmail= findViewById(R.id.et_username);
        edtBday= findViewById(R.id.et_bday);
        edtPwd= findViewById(R.id.et_password);
        edtConfirm= findViewById(R.id.et_repeatpassword);
        rdFemale=findViewById(R.id.radioButton);
        rdMale=findViewById(R.id.radioButton2);
        btnBack=findViewById(R.id.btnBack);
        btnRegister=findViewById(R.id.btnRegister2);
        RadioGroup radioGroup = findViewById(R.id.radio_group);

        rdMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If rdMale is checked, uncheck rdFemale
                    rdFemale.setChecked(false);
                }
            }
        });

        rdFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // If rdFemale is checked, uncheck rdMale
                    rdMale.setChecked(false);
                }
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String gender;
                if (rdMale.isChecked()) {
                    gender = "male";
                }
                else gender = "female";
                String birthday = edtBday.getText().toString();
                String password = edtPwd.getText().toString();
                String confirmPwd = edtConfirm.getText().toString();
                String firstname= edtFN.getText().toString();
                String lastname =edtLN.getText().toString();
                //API
                ApiService apiService = ApiClient.getApiService();
                apiService.register(email,gender,birthday,password,confirmPwd,firstname,lastname).enqueue(new Callback<RegisterRespone>() {
                    @Override
                    public void onResponse(Call<RegisterRespone> call, Response<RegisterRespone> response) {
                        String token = null;
                        if (response.body() != null) {
                            if(response.body().getToken()!=null)
                            {
                                Toast.makeText(RegisterActivity.this, "Successfull !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            if (response.body().equals("Your email existed!"))
                            {
                                Toast.makeText(RegisterActivity.this, "Your email existed!", Toast.LENGTH_SHORT).show();
                            }
                            if (response.body().equals("Confirm Password does not match!"))
                            {
                                Toast.makeText(RegisterActivity.this, "Confirm Password does not match!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterRespone> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void validate()
    {
        if(edtPwd.getText() != edtConfirm.getText())
        {
            Toast.makeText(getApplicationContext(), "Password not match ", Toast.LENGTH_SHORT).show();
        }
    }
}