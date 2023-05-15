package com.MyAppChat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.Model.FriendModel;
import com.bumptech.glide.Glide;
import com.example.myappchat.R;

import java.util.List;

public class FriendApdapter extends RecyclerView.Adapter<FriendApdapter.ViewHolder>{

    private Context context;
    private List<FriendModel> dataList;

    public FriendApdapter(Context context, List<FriendModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_friends, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendModel friendModel = dataList.get(position);
        Glide.with(context)
                .load(friendModel.getAvatar())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgvAva);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //click vào friend chuyển sang chat
                }
            });
        }
    }
}
