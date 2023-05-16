package com.MyAppChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Adapter.MessageAdapter;
import com.MyAppChat.Model.LastMessageModel;
import com.example.myappchat.R;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.message_layout);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0);
        String accessToken = intent.getStringExtra("accessToken");
        String nameChat = intent.getStringExtra("nameChat");
        String avaUrl = intent.getStringExtra("avaUrl");

        //Bundle extras = intent.getExtras();
        //Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        //Anh xa
        AnhXa();

        //Set
        //Glide.with(getApplicationContext()).load(avaUrl).apply(RequestOptions.circleCropTransform()).override(350, 350).into(imgAvaMessage);
        tvNameChat.setText(nameChat);
        //imgAvaMessage.setImageBitmap(bmp);

        ApiService apiService = ApiClient.getApiService();
        apiService.getChatMessageList("Bearer " + accessToken, userId).enqueue(new Callback<List<LastMessageModel>>() {
            @Override
            public void onResponse(Call<List<LastMessageModel>> call, Response<List<LastMessageModel>> response) {
                messageModelList = response.body();

                messageAdapter = new MessageAdapter(getApplicationContext(), messageModelList);
                rcvChatMessage.setHasFixedSize(true);
                rcvChatMessage.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rcvChatMessage.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<LastMessageModel>> call, Throwable t) {
                Log.d("Loi", "Loi fai");
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
}
