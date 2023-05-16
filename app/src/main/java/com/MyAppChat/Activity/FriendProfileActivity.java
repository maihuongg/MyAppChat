package com.MyAppChat.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Model.UpdateProfileModal;
import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myappchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendProfileActivity extends AppCompatActivity {
    TextView tvUsername;
    TextView tvFriends;
    EditText edtFN;
    EditText edtLN;
    EditText edtBday;
    EditText edtGender;
    Button btnAddFr, btnSendMess;
    ImageView imgAvatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_friend);

        tvUsername = findViewById(R.id.tvUsername_friend);
        tvFriends = findViewById(R.id.tvFriends_friend);
        edtFN = (EditText) findViewById(R.id.edtFN_friend);
        edtLN = (EditText) findViewById(R.id.edtLN_friend);
        edtBday = (EditText) findViewById(R.id.edtBday_friend);
        edtGender = (EditText) findViewById(R.id.edtGender_friend);
        btnAddFr = (Button) findViewById(R.id.buttonAddFriend);
        btnSendMess = findViewById(R.id.buttonSendMess);
        imgAvatar = findViewById(R.id.proFriend_image);
        //enabled editting
        edtFN.setEnabled(false);
        edtLN.setEnabled(false);
        edtBday.setEnabled(false);
        edtGender.setEnabled(false);

        //click button
        btnAddFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //intent, put


        //setText
        ApiService apiService = ApiClient.getApiService();
        //get FriendProfile
//        apiService.getProfile(id).enqueue(new Callback<ProfileResponse>() {
//            @Override
//            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
//                if (response.body() != null) {
//                    tvUsername.setText(response.body().getUsername());
//                    edtFN.setText(response.body().getFirst_name());
//                    edtLN.setText(response.body().getLast_name());
//                    edtBday.setText(response.body().getBirthday());
//                    edtGender.setText(response.body().getGender());
//                    Glide.with(view).load(response.body().getAvatar()).apply(RequestOptions.circleCropTransform()).override(350, 350).into(imgAvatar);
//                } else {
//                    Toast.makeText(getActivity().getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileResponse> call, Throwable t) {
//
//            }
//        });
        //enable edit when click button Edit

        btnSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển sang activity gửi tin nhắn
            }
        });
    }

}

