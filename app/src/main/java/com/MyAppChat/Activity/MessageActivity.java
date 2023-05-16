package com.MyAppChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Adapter.MessageAdapter;
import com.MyAppChat.Model.LastMessageModel;
import com.MyAppChat.Model.SendMessageModel;
import com.MyAppChat.Utils.SendMessageResponse;
import com.example.myappchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView rcvChatMessage;
    private List<LastMessageModel> messageModelList;
    MessageAdapter messageAdapter;
    private ImageView imgAvaMessage;
    private TextView tvNameChat;
    private EditText edtMessage;
    private Button btnSend;
    private String accessToken;
    private int userId;
    private int chatRoomId;
    private Handler mHandler;
    private boolean isBottom = true;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.message_layout);

        //Auto load with Handler
        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 0);

        Intent intent = getIntent();
        userId = intent.getIntExtra("id", 0);
        accessToken = intent.getStringExtra("accessToken");
        String nameChat = intent.getStringExtra("nameChat");
        String avaUrl = intent.getStringExtra("avaUrl");
        chatRoomId = intent.getIntExtra("chatRoomId", 0);

        //Anh xa
        AnhXa();

        //Set
        tvNameChat.setText(nameChat);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendText = edtMessage.getText().toString();
                String jsonString = String.format("{\"content\":\"" + sendText + "\"," + "\"receiverID\":\"" + chatRoomId + "\"}");
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                SendMessageModel sendMessageModel = gson.fromJson(jsonString, SendMessageModel.class);

                ApiService apiService = ApiClient.getApiService();
                apiService.sendChat("Bearer " + accessToken, sendMessageModel).enqueue(new Callback<SendMessageResponse>() {
                    @Override
                    public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {
                        SendMessageResponse sendMessageResponse = response.body();
                        Log.d("abc", sendMessageResponse.getContent());
                    }

                    @Override
                    public void onFailure(Call<SendMessageResponse> call, Throwable t) {
                        Log.d("error", "Loi fai");
                        onPause();
                    }
                });
            }
        });
    }

    private void AnhXa() {
        imgAvaMessage = findViewById(R.id.imgAvaMessage);
        tvNameChat = findViewById(R.id.tvNameChat);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        rcvChatMessage = findViewById(R.id.rcvChatMessage);
    }


    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            //Toast.makeText(MessageActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            ApiService apiService = ApiClient.getApiService();
            apiService.getChatMessageList("Bearer " + accessToken, chatRoomId).enqueue(new Callback<List<LastMessageModel>>() {
                @Override
                public void onResponse(Call<List<LastMessageModel>> call, Response<List<LastMessageModel>> response) {
                    messageModelList = response.body();
                    messageAdapter = new MessageAdapter(getApplicationContext(), messageModelList, userId);
                    rcvChatMessage.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setStackFromEnd(true);
                    rcvChatMessage.setLayoutManager(linearLayoutManager);
                    rcvChatMessage.setAdapter(messageAdapter);
                    messageAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<LastMessageModel>> call, Throwable t) {
                    Log.d("Loi", "Loi fai");
                }
            });
            MessageActivity.this.mHandler.postDelayed(m_Runnable, 200);
        }

    };//runnable

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        finish();

    }
}
