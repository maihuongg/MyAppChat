package com.MyAppChat.Adapter;

import android.content.Context;
import android.util.Log;
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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private List<LastMessageModel> dataList;

    public MessageAdapter(Context context, List<LastMessageModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_recieve_mess, parent, false);
        return new MessageAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LastMessageModel lastMessageModel = dataList.get(position);
        if (lastMessageModel != null) {
            holder.tvcontentRecieve.setText(lastMessageModel.getContent());
            Glide.with(context).load(lastMessageModel.getSenderID().getAvatar()).into(holder.imgAvaRecieve);
        }
    }

    @Override
    public int getItemCount() {
        return (dataList == null ? 0 : dataList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvaRecieve;
        private TextView tvcontentRecieve;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgAvaRecieve = (ImageView) itemView.findViewById(R.id.imgAvaRecieve);
            tvcontentRecieve = (TextView) itemView.findViewById(R.id.tvContentRecieve);

        }
    }
}
