package com.MyAppChat.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.MyAppChat.Adapter.FriendAdapter;
import com.MyAppChat.Utils.DetailListFriendResponse;
import com.example.myappchat.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendActivity extends Fragment {
    FriendAdapter friendAdapter;
    private RecyclerView rcvFriends;
    private List<DetailListFriendResponse> friendResponseList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_of_friend_layout,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
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
        rcvFriends = (RecyclerView) view.findViewById(R.id.rcvFriends);
        ApiService apiService = ApiClient.getApiService();
        apiService.getDetailFriendList(id).enqueue(new Callback<List<DetailListFriendResponse>>() {
            @Override
            public void onResponse(Call<List<DetailListFriendResponse>> call, Response<List<DetailListFriendResponse>> response) {

                friendResponseList = response.body();
                Log.d("abc",friendResponseList.get(0).getResponseID().getFirst_name());
                friendAdapter = new FriendAdapter(getContext(), friendResponseList);
                rcvFriends.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rcvFriends.setLayoutManager(layoutManager);
                //rcvFriends.setLayoutManager(new GridLayoutManager(requireContext(), 3));
                rcvFriends.setAdapter(friendAdapter);
                friendAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<DetailListFriendResponse>> call, Throwable t) {
                Log.d("Loi roi", "Loi fai");
            }
        });
    }
}