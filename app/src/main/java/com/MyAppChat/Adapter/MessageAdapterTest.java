package com.MyAppChat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.Model.LastMessageModel;
import com.bumptech.glide.Glide;
import com.example.myappchat.R;

import java.util.List;

public class MessageAdapterTest extends RecyclerView.Adapter<MessageAdapterTest.ViewHolder> {
    private Context context;
    private List<LastMessageModel> dataList;
    private int chatRoomId;

    public MessageAdapterTest(Context context, List<LastMessageModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MessageAdapterTest.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_recieve_mess, parent, false);
        return new MessageAdapterTest.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LastMessageModel lastMessageModel = dataList.get(position);
        holder.tvContentRecieve.setText(lastMessageModel.getContent());
        Glide.with(context).load(lastMessageModel.getSenderID().getAvatar()).into(holder.imgAvaRecieve);
    }

    @Override
    public int getItemCount() {
        return (dataList == null ? 0 : dataList.size());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvaRecieve;
        private TextView tvContentRecieve;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgAvaRecieve = (ImageView) itemView.findViewById(R.id.imgAvaSend);
            tvContentRecieve = (TextView) itemView.findViewById(R.id.tvContentSend);

        }
    }
}
