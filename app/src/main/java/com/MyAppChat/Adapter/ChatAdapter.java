package com.MyAppChat.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.MyAppChat.Model.ChatModel;
import com.bumptech.glide.Glide;
import com.example.myappchat.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context context;
    private List<ChatModel> dataList;

    public ChatAdapter(Context context, List<ChatModel> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        holder.tvUserNameChat.setText(chatModel.getLatest_message().getSenderID().getEmail());
        Glide.with(context)
                .load(chatModel.getLatest_message().getSenderID().getAvatar())
                .into(holder.imgAvaChat);
    }

    @Override
    public int getItemCount() {

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
                    //click vào friend chuyển sang chat
                    Intent intent = new Intent();
                    //intent.putExtra();
                }
            });
        }
    }
}
