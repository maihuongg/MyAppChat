package com.MyAppChat.Model;

import java.io.Serializable;

public class CreateChatRoomModel implements Serializable {
    private String members;
    private String content;

    public CreateChatRoomModel(String members, String content) {
        this.members = members;
        this.content = content;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
