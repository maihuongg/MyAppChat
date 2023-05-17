package com.MyAppChat.Activity;

import android.content.Intent;
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
import com.MyAppChat.Model.AddFriendModel;
import com.MyAppChat.Model.CreateChatRoomModel;
import com.MyAppChat.Model.UpdateChatRoomModel;
import com.MyAppChat.Model.UpdateProfileModal;
import com.MyAppChat.Utils.CreateChatRoomResponse;
import com.MyAppChat.Utils.DetailListFriendResponse;
import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myappchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendProfileActivity extends AppCompatActivity {

    EditText edtFN;
    EditText edtLN;
    EditText edtBday;
    EditText edtGender;
    Button btnAddFr, btnSendMess;
    ImageView imgAvatar;
    String fullName;
    String avaUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_friend);

        //Get intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        String accessToken = intent.getStringExtra("accessToken");
        int peopleId = intent.getIntExtra("peopleId", 0);
        ArrayList<Integer> listFriend = intent.getIntegerArrayListExtra("listFriend");
        //AnhXa
        AnhXa();

        //enabled editting
        edtFN.setEnabled(false);
        edtLN.setEnabled(false);
        edtBday.setEnabled(false);
        edtGender.setEnabled(false);
        btnSendMess.setVisibility(View.INVISIBLE);

        if (listFriend.contains(peopleId)) {
            btnAddFr.setVisibility(View.INVISIBLE);
            //hiện button save
            btnSendMess.setVisibility(View.VISIBLE);
        }
        //click button
        btnAddFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddFr.setVisibility(View.INVISIBLE);
                //hiện button save
                btnSendMess.setVisibility(View.VISIBLE);

                ApiService apiService = ApiClient.getApiService();
                String memberId = String.valueOf(userId) + ", " + String.valueOf(peopleId);
                String jsonString = String.format("{\"members\":\"" + memberId + "\"," + "\"content\":\"  \"}");
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                CreateChatRoomModel createChatRoomModel = gson.fromJson(jsonString, CreateChatRoomModel.class);
                apiService.createChatRoom("Bearer " + accessToken, createChatRoomModel).enqueue(new Callback<CreateChatRoomResponse>() {
                    @Override
                    public void onResponse(Call<CreateChatRoomResponse> call, Response<CreateChatRoomResponse> response) {
                        CreateChatRoomResponse createChatRoomResponse = response.body();

                        Log.d("abc", String.valueOf(createChatRoomResponse.getId()));

                        String jsonString = String.format("{\"isGroup\":\"false\"}");
                        GsonBuilder builder = new GsonBuilder();
                        builder.setPrettyPrinting();
                        Gson gson = builder.create();
                        UpdateChatRoomModel updateChatRoomModel = gson.fromJson(jsonString, UpdateChatRoomModel.class);

                        apiService.updateChatRoom("Bearer " + accessToken, updateChatRoomModel, createChatRoomResponse.getId()).enqueue(new Callback<CreateChatRoomResponse>() {
                            @Override
                            public void onResponse(Call<CreateChatRoomResponse> call, Response<CreateChatRoomResponse> response) {
                                Log.d("Oke", "Oke");
                            }

                            @Override
                            public void onFailure(Call<CreateChatRoomResponse> call, Throwable t) {
                                Log.d("loi roi", "Oke");

                            }
                        });

                        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id", userId);
                        intent.putExtra("chatRoomId", createChatRoomResponse.getId());
                        intent.putExtra("accessToken", accessToken);
                        intent.putExtra("nameChat", edtFN.getText());
                        //intent.putExtra("avaUrl", dataList.get(getAdapterPosition()).getResponseID().getAvatar());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CreateChatRoomResponse> call, Throwable t) {

                    }
                });
            }
        });
        //intent, put


        //setText
        ApiService apiService = ApiClient.getApiService();
        //get FriendProfile
        apiService.getProfile(peopleId).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.body() != null) {
                    edtFN.setText(response.body().getFirst_name());
                    edtLN.setText(response.body().getLast_name());
                    edtBday.setText(response.body().getBirthday());
                    edtGender.setText(response.body().getGender());
                    fullName = response.body().getFirst_name() + " " + response.body().getLast_name();
                    avaUrl = response.body().getAvatar();
                    Glide.with(getApplicationContext()).load(avaUrl).apply(RequestOptions.circleCropTransform()).override(350, 350).into(imgAvatar);
                } else {
                    Toast.makeText(getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
        //enable edit when click button Edit

        btnSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for(int roomId:listFriend)
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", userId);
                //intent.putExtra("chatRoomId", dataList.get(getAdapterPosition()).getId());
                intent.putExtra("accessToken", accessToken);
                intent.putExtra("nameChat", fullName);
                intent.putExtra("avaUrl", avaUrl);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edtFN = (EditText) findViewById(R.id.edtFN_friend);
        edtLN = (EditText) findViewById(R.id.edtLN_friend);
        edtBday = (EditText) findViewById(R.id.edtBday_friend);
        edtGender = (EditText) findViewById(R.id.edtGender_friend);
        btnAddFr = (Button) findViewById(R.id.buttonAddFriend);
        btnSendMess = findViewById(R.id.buttonSendMess);
        imgAvatar = findViewById(R.id.proFriend_image);
    }

}

