package com.MyAppChat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MyAppChat.Utils.DetailListFriendResponse;
import com.bumptech.glide.Glide;
import com.example.myappchat.R;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;
    private List<DetailListFriendResponse> dataList;

    public FriendAdapter(Context context, List<DetailListFriendResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        holder.firstNameTextView.setText(friendModel.getResponseID().getFirst_name() + " " + friendModel.getResponseID().getLast_name());
        Glide.with(context).load(friendModel.getResponseID().getAvatar()).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView firstNameTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.imgvAva);
            firstNameTextView = itemView.findViewById(R.id.tvFullName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //click vào friend chuyển sang chat
                }
            });
        }
    }
}
