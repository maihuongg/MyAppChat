package com.MyAppChat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Activity.MessageActivity;
import com.MyAppChat.Model.CreateChatRoomModel;
import com.MyAppChat.Model.MemberModel;
import com.MyAppChat.Model.SendMessageModel;
import com.MyAppChat.Model.UpdateChatRoomModel;
import com.MyAppChat.Utils.CreateChatRoomResponse;
import com.MyAppChat.Utils.DetailListFriendResponse;
import com.MyAppChat.Utils.RoomDetailResponse;
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

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;
    private List<DetailListFriendResponse> dataList;
    int userId;
    String accessToken;
    List<Integer> integerList;

    public FriendAdapter(Context context, List<DetailListFriendResponse> dataList, int userId, String accessToken, List<Integer> integerList) {
        this.context = context;
        this.dataList = dataList;
        this.userId = userId;
        this.accessToken = accessToken;
        this.integerList = new ArrayList<>(integerList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View itemView = li.inflate(R.layout.item_friends, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailListFriendResponse friendModel = dataList.get(position);
        if (friendModel.getResponseID().getId() == userId) {
            holder.firstNameTextView.setText(friendModel.getRequestID().getFirst_name() + " " + friendModel.getRequestID().getLast_name());
            Glide.with(context).load(friendModel.getRequestID().getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .override(250, 250).into(holder.avatarImageView);
        } else {
            holder.firstNameTextView.setText(friendModel.getResponseID().getFirst_name() + " " + friendModel.getResponseID().getLast_name());
            Glide.with(context).load(friendModel.getResponseID().getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .override(250, 250).into(holder.avatarImageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView firstNameTextView;
        LinearLayout linFriend;

        public ViewHolder(final View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.imgvAva);
            firstNameTextView = itemView.findViewById(R.id.tvFullName);
            linFriend = itemView.findViewById(R.id.linFriend);
            linFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //int created = 0;
                    int friendId =
                            dataList.get(getAdapterPosition()).getResponseID().getId() == userId
                                    ? dataList.get(getAdapterPosition()).getRequestID().getId()
                                    : dataList.get(getAdapterPosition()).getResponseID().getId();
                    Log.d("alo", String.valueOf(friendId));
                    Log.d("alo", accessToken);
                    for(int integer:integerList){
                        ApiService apiService = ApiClient.getApiService();
                        apiService.getRoomDetail("Bearer " + accessToken,integer).enqueue(new Callback<RoomDetailResponse>() {
                            @Override
                            public void onResponse(Call<RoomDetailResponse> call, Response<RoomDetailResponse> response) {
                                RoomDetailResponse roomDetailResponse = response.body();
                                //created = CheckChatRoom(roomDetailResponse, friendId);
                                CheckChatRoom(roomDetailResponse, friendId);
                            }

                            @Override
                            public void onFailure(Call<RoomDetailResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            });
        }

        private void CheckChatRoom(RoomDetailResponse roomDetailResponse, int friendId){
            for(MemberModel memberModel : roomDetailResponse.getMembers()){
                Log.d("abcde" + roomDetailResponse.getId(), String.valueOf(memberModel.getUser().getId()));
                Log.d("abcd", String.valueOf(friendId));
                Log.d("abcd", String.valueOf(!roomDetailResponse.isGroup()));
                if(memberModel.getUser().getId() == friendId && !roomDetailResponse.isGroup()){

                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", userId);
                    intent.putExtra("chatRoomId", roomDetailResponse.getId());
                    intent.putExtra("accessToken", accessToken);
                    intent.putExtra("nameChat", firstNameTextView.getText());
                    intent.putExtra("avaUrl", dataList.get(getAdapterPosition()).getResponseID().getAvatar());
                    context.startActivity(intent);
                    return ;
                }
            }
        }

        private void CreateChat(int friendId){
            ApiService apiService = ApiClient.getApiService();
            String memberId = String.valueOf(userId)+", "+String.valueOf(friendId);
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

                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", userId);
                    intent.putExtra("chatRoomId", createChatRoomResponse.getId());
                    intent.putExtra("accessToken", accessToken);
                    intent.putExtra("nameChat", firstNameTextView.getText());
                    intent.putExtra("avaUrl", dataList.get(getAdapterPosition()).getResponseID().getAvatar());
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(Call<CreateChatRoomResponse> call, Throwable t) {

                }
            });
        }

    }
}
