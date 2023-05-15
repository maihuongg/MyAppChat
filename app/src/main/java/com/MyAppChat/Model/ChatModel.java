package com.MyAppChat.Model;

import java.io.Serializable;
import java.util.List;

public class ChatModel implements Serializable {
    private int id;
    private String roomName;
    private List<MemberModel> members;
    private boolean isGroup;
    private LastMessageModel latest_message;
    private String updated;
    private String roomAvatar;

    public ChatModel(int id, String roomName, List<MemberModel> members, boolean isGroup, LastMessageModel latest_message, String updated, String roomAvatar) {
        this.id = id;
        this.roomName = roomName;
        this.members = members;
        this.isGroup = isGroup;
        this.latest_message = latest_message;
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

    public List<MemberModel> getMembers() {
        return members;
    }

    public void setMembers(List<MemberModel> members) {
        this.members = members;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public LastMessageModel getLatest_message() {
        return latest_message;
    }

    public void setLatest_message(LastMessageModel latest_message) {
        this.latest_message = latest_message;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getRoomAvatar() {
        return roomAvatar;
    }

    public void setRoomAvatar(String roomAvatar) {
        this.roomAvatar = roomAvatar;
    }
}
