package com.MyAppChat.Utils;

import java.io.Serializable;
import java.util.ArrayList;

public class SendMessageResponse implements Serializable {
    private int id;
    private String content;
    private String created;
    private boolean isRemoved;
    private int senderID;
    private int receiverID;
    private ArrayList files;

    public SendMessageResponse(int id, String content, String created, boolean isRemoved, int senderID, int receiverID, ArrayList files) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.isRemoved = isRemoved;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.files = files;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public ArrayList getFiles() {
        return files;
    }

    public void setFiles(ArrayList files) {
        this.files = files;
    }
}
