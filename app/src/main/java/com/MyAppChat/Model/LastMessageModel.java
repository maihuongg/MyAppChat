package com.MyAppChat.Model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class LastMessageModel implements Serializable {
    private int id;
    private UserMemberChat senderID;
    private String content;
    private String created;
    private boolean isRemoved;
    private int receiverID;
    private @Nullable String seen_by;
    private ArrayList files;


    public LastMessageModel(int id, UserMemberChat senderID, String content, String created, boolean isRemoved, int receiverID, @Nullable String seen_by, ArrayList files) {
        this.id = id;
        this.senderID = senderID;
        this.content = content;
        this.created = created;
        this.isRemoved = isRemoved;
        this.receiverID = receiverID;
        this.seen_by = seen_by;
        this.files = files;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserMemberChat getSenderID() {
        return senderID;
    }

    public void setSenderID(UserMemberChat senderID) {
        this.senderID = senderID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    @Nullable
    public String getSeen_by() {
        return seen_by;
    }

    public void setSeen_by(@Nullable String seen_by) {
        this.seen_by = seen_by;
    }

    public ArrayList getFiles() {
        return files;
    }

    public void setFiles(ArrayList files) {
        this.files = files;
    }
}
