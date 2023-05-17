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
import com.bumptech.glide.request.RequestOptions;
import com.example.myappchat.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LastMessageModel> dataList;
    private int userId;

    public MessageAdapter(Context context, List<LastMessageModel> dataList, int userId) {
        this.context = context;
        this.dataList = dataList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                View itemView = li.inflate(R.layout.item_recieve_mess, parent, false);
                return new MessageAdapter.ViewHolder0(itemView);
            case 1:
                View itemView1 = li.inflate(R.layout.item_send_mess, parent, false);
                return new MessageAdapter.ViewHolder1(itemView1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                LastMessageModel lastMessageModel = dataList.get(position);
                viewHolder0.tvContentRecieve.setText(lastMessageModel.getContent());
                Glide.with(context).load(lastMessageModel.getSenderID().getAvatar()).apply(RequestOptions.circleCropTransform())
                        .override(250, 250).into(viewHolder0.imgAvaRecieve);
                break;
            case 1:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                LastMessageModel lastMessageModel1 = dataList.get(position);
                viewHolder1.tvContentSend.setText(lastMessageModel1.getContent());
                Glide.with(context).load(lastMessageModel1.getSenderID().getAvatar()).apply(RequestOptions.circleCropTransform())
                        .override(250, 250).into(viewHolder1.imgAvaSend);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return (dataList == null ? 0 : dataList.size());
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getSenderID().getId() == userId ? 1 : 0;
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView imgAvaRecieve;
        private TextView tvContentRecieve;

        public ViewHolder0(@NonNull View itemView) {

            super(itemView);
            imgAvaRecieve = (ImageView) itemView.findViewById(R.id.imgAvaRecieve);
            tvContentRecieve = (TextView) itemView.findViewById(R.id.tvContentRecieve);

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private ImageView imgAvaSend;
        private TextView tvContentSend;

        public ViewHolder1(@NonNull View itemView) {

            super(itemView);
            imgAvaSend = (ImageView) itemView.findViewById(R.id.imgAvaSend);
            tvContentSend = (TextView) itemView.findViewById(R.id.tvContentSend);

        }
    }
}
