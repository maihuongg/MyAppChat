package com.MyAppChat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.Activity.LoginActivity;
import com.MyAppChat.Activity.MainActivity;
import com.MyAppChat.Activity.MessageActivity;
import com.MyAppChat.Model.ChatModel;
import com.MyAppChat.Model.MemberModel;
import com.MyAppChat.Model.UserModel;
import com.bumptech.glide.Glide;
import com.example.myappchat.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<ChatModel> dataList;
    private int id;
    private String access;

    public ChatAdapter(Context context, List<ChatModel> dataList, int id, String access) {
        this.context = context;
        this.dataList = dataList;
        this.id = id;
        this.access=access;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chatModel = dataList.get(position);
        String nameChat = "";
        if (chatModel.isGroup()) {
            for(MemberModel memberModel:chatModel.getMembers()){
                nameChat += memberModel.getUser().getLast_name() + ", ";
            }
        }
        else {
            nameChat = (chatModel.getMembers().get(0).getId() == id ? chatModel.getMembers().get(0).getUser().getFirst_name() + " " + chatModel.getMembers().get(0).getUser().getLast_name()
                    : chatModel.getMembers().get(1).getUser().getFirst_name() + " " + chatModel.getMembers().get(1).getUser().getLast_name());
        }
        holder.tvUserNameChat.setText(nameChat);
        holder.tvLastChat.setText(chatModel.getLatest_message().getContent());
        String avaUrl = "http:192.168.1.2:8000" + chatModel.getLatest_message().getSenderID().getAvatar();
        Glide.with(context).load(avaUrl).into(holder.imgAvaChat);
    }

    @Override
    public int getItemCount() {

        return (dataList == null ? 0 : dataList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvaChat;
        private TextView tvUserNameChat;
        private TextView tvLastChat;
        private LinearLayout layoutChat;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgAvaChat = (ImageView) itemView.findViewById(R.id.imgAvaChat);
            tvUserNameChat = (TextView) itemView.findViewById(R.id.tvUserNameChat);
            tvLastChat = (TextView) itemView.findViewById(R.id.tvLastChat);
            layoutChat = (LinearLayout) itemView.findViewById(R.id.layoutChat);
            layoutChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int chatRoomId = 1;
                    //click vào friend chuyển sang chat
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", id);
                    intent.putExtra("chatRoomId", dataList.get(getAdapterPosition()).getId());
                    intent.putExtra("accessToken", access);
                    intent.putExtra("nameChat", tvUserNameChat.getText());
                    intent.putExtra("avaUrl", dataList.get(getAdapterPosition()).getLatest_message().getSenderID().getAvatar());
                    context.startActivity(intent);
                }
            });
        }
    }
}
