package com.MyAppChat.Activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Adapter.FriendAdapter;
import com.MyAppChat.Model.ChatModel;
import com.MyAppChat.Model.MemberModel;
import com.MyAppChat.Utils.DetailListFriendResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.example.myappchat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendActivity extends Fragment {
    FriendAdapter friendAdapter;
    private RecyclerView rcvFriends;
    private List<DetailListFriendResponse> friendResponseList;
    private List<ChatModel> chatModelList;
    private List<Integer> integerList;
    private List<Integer> friendList;
    private EditText edtSearchFriends;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_of_friend_layout, container, false);
        Bundle args = getArguments();
        int id = 0;
        String accessToken = "";

        if (args != null) {
            id = args.getInt("id");
            accessToken = args.getString("access");
            // xử lý dữ liệu tại đây
        }
        int finalId = id;
        String finalAccessToken = accessToken;
        rcvFriends = (RecyclerView) view.findViewById(R.id.rcvFriends);
        integerList = new ArrayList<>();
        friendList = new ArrayList<>();
        edtSearchFriends = view.findViewById(R.id.edtSearchFriends);
        callFirstAPI(finalId,finalAccessToken);

        edtSearchFriends.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                // If the event is a key-down event on the "enter" button
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (i == KeyEvent.KEYCODE_ENTER)) {
                    int peopleId = Integer.parseInt(edtSearchFriends.getText().toString());
                    // Perform action on key press
                    //Toast.makeText(getActivity(), edtSearchFriends.getText(), Toast.LENGTH_SHORT).show();
                    ApiService apiService = ApiClient.getApiService();
                    apiService.getProfile(peopleId).enqueue(new Callback<ProfileResponse>() {
                        @Override
                        public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                            ProfileResponse profileResponse = response.body();
                            if(profileResponse.getEmail() != null){
                                Intent intent = new Intent(getContext(), FriendProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("userId", finalId);
                                intent.putExtra("accessToken", finalAccessToken);
                                intent.putExtra("peopleId", peopleId);
                                intent.putIntegerArrayListExtra("listFriend", (ArrayList<Integer>) friendList);
                                Log.d("listFriend", String.valueOf((ArrayList<Integer>) friendList));
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(getActivity(), "User not found !!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ProfileResponse> call, Throwable t) {
                            Log.d("Loi","Loi roi");

                        }
                    });
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private void callFirstAPI(int finalId, String finalAccessToken){
        ApiService apiService = ApiClient.getApiService();
        //Tra ve IDRoom
        apiService.getChatList("Bearer " + finalAccessToken).enqueue(new Callback<List<ChatModel>>() {
            @Override
            public void onResponse(Call<List<ChatModel>> call, Response<List<ChatModel>> response) {
                chatModelList = response.body();

                for(ChatModel chatModel:chatModelList){
                    integerList.add(chatModel.getId());
                }
                callSecondAPI(finalId,finalAccessToken);

            }
            @Override
            public void onFailure(Call<List<ChatModel>> call, Throwable t) {

            }
        });
    }

    private void callSecondAPI(int finalId, String finalAccessToken){
        ApiService apiService = ApiClient.getApiService();
        //Trả về friend
        apiService.getDetailFriendList(finalId).enqueue(new Callback<List<DetailListFriendResponse>>() {
            @Override
            public void onResponse(Call<List<DetailListFriendResponse>> call, Response<List<DetailListFriendResponse>> response) {

                friendResponseList = response.body();

                for(DetailListFriendResponse detailListFriendResponse: friendResponseList){
                    friendList.add(detailListFriendResponse.getResponseID().getId() == finalId?detailListFriendResponse.getRequestID().getId():detailListFriendResponse.getResponseID().getId());
                }

                friendAdapter = new FriendAdapter(getContext(), friendResponseList, finalId, finalAccessToken, integerList);
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