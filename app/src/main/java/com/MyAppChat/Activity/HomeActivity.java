package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Adapter.ChatAdapter;
import com.MyAppChat.Model.ChatModel;
import com.example.myappchat.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {

    private RecyclerView recycleViewChat;
    private List<ChatModel> chatModelList;
    ChatAdapter chatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        Bundle args = getArguments();

        int id = 0;
        String accessToken = "";
        Log.d("accessToken", accessToken);
        if (args != null) {
            id = args.getInt("id");
            accessToken = args.getString("access");
            // xử lý dữ liệu tại đây
        }
        String finalAccessToken = accessToken;
        recycleViewChat = (RecyclerView) view.findViewById(R.id.recycleViewChat);
        ApiService apiService = ApiClient.getApiService();
        apiService.getChatList("Bearer " + finalAccessToken).enqueue(new Callback<List<ChatModel>>() {
            @Override
            public void onResponse(Call<List<ChatModel>> call, Response<List<ChatModel>> response) {
                chatModelList = response.body();
                if(response.body()!=null){
                    Log.d("abc", String.valueOf(response.body().size()));
                    Log.d("aaaa", "test");
                    System.out.println(response.body().size());

                }
                else{
                    Log.d("error","Loi");
                }
                chatAdapter = new ChatAdapter(getActivity().getApplicationContext(), chatModelList);
                recycleViewChat.setHasFixedSize(true);
                recycleViewChat.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                recycleViewChat.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ChatModel>> call, Throwable t) {
                Log.d("Loi","Loi fai");

            }
        });
        return view;
    }
}