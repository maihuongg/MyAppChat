package com.MyAppChat.Model;

import java.io.Serializable;

public class SendMessageModel implements Serializable {
    private String content;
    private int receiverID;

    public SendMessageModel(String content, int receiverID) {
        this.content = content;
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
}
