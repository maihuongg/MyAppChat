package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Adapter.ChatAdapter;
import com.MyAppChat.Adapter.MessageAdapter;
import com.MyAppChat.Model.ChatModel;
import com.MyAppChat.Model.LastMessageModel;
import com.example.myappchat.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {

    private RecyclerView recycleViewChat;
    private List<ChatModel> chatModelList;
    ChatAdapter chatAdapter;
    private Handler mHandler;
    int userId;

String accessToken = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        Bundle args = getArguments();

        int id = 0;
        //String accessToken = "";
        if (args != null) {
            userId = args.getInt("id");
            accessToken = args.getString("access");
            // xử lý dữ liệu tại đây
        }
        //Log.d("accessToken", accessToken);
        //Log.d("accessTokenTest", String.valueOf(id));
        //Auto load with Handler
        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 0);

        int finalId = id;
        String finalAccessToken = accessToken;
        recycleViewChat = (RecyclerView) view.findViewById(R.id.recycleViewChat);
//        ApiService apiService = ApiClient.getApiService();
//        apiService.getChatList("Bearer " + finalAccessToken).enqueue(new Callback<List<ChatModel>>() {
//            @Override
//            public void onResponse(Call<List<ChatModel>> call, Response<List<ChatModel>> response) {
//                chatModelList = response.body();
//                chatAdapter = new ChatAdapter(getContext(), chatModelList, finalId, finalAccessToken);
//                recycleViewChat.setHasFixedSize(true);
//                recycleViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
//                recycleViewChat.setAdapter(chatAdapter);
//                chatAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<ChatModel>> call, Throwable t) {
//                Log.d("Loi","Loi fai");
//            }
//        });
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("night", false);
// Set the initial state of the switch
        if (isDarkModeEnabled) {
            recycleViewChat.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_background));
        }
        return view;
    }

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            //Toast.makeText(MessageActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            ApiService apiService = ApiClient.getApiService();
            apiService.getChatList("Bearer " + accessToken).enqueue(new Callback<List<ChatModel>>() {
                @Override
                public void onResponse(Call<List<ChatModel>> call, Response<List<ChatModel>> response) {
                    chatModelList = response.body();
                    chatAdapter = new ChatAdapter(getContext(), chatModelList, userId, accessToken);
                    recycleViewChat.setHasFixedSize(true);
                    recycleViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycleViewChat.setAdapter(chatAdapter);
                    chatAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<ChatModel>> call, Throwable t) {
                    Log.d("Loi","Loi fai");
                }
            });
            mHandler.postDelayed(m_Runnable, 500);
        }

    };//runnable


    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        //finish();

    }
}