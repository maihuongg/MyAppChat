package com.MyAppChat.Utils;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class CreateChatRoomResponse implements Serializable {
    private int id;
    private String roomName;
    private boolean isGroup;
    private String updated;
    private @Nullable String roomAvatar;

    public CreateChatRoomResponse(int id, String roomName, boolean isGroup, String updated, @Nullable String roomAvatar) {
        this.id = id;
        this.roomName = roomName;
        this.isGroup = isGroup;
        this.updated = updated;
        this.roomAvatar = roomAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Nullable
    public String getRoomAvatar() {
        return roomAvatar;
    }

    public void setRoomAvatar(@Nullable String roomAvatar) {
        this.roomAvatar = roomAvatar;
    }
}
