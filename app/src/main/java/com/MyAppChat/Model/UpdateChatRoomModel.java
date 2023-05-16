package com.MyAppChat.Model;

import java.io.Serializable;

public class UpdateChatRoomModel implements Serializable {
    private boolean isGroup;

    public UpdateChatRoomModel(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
