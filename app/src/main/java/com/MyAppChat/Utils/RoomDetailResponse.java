package com.MyAppChat.Utils;

import androidx.annotation.Nullable;

import com.MyAppChat.Model.MemberModel;

import java.io.Serializable;
import java.util.List;

public class RoomDetailResponse implements Serializable {
    private int id;
    private String roomName;
    private List<MemberModel> members;
    private boolean isGroup;
    private @Nullable String roomAvatar;

    public RoomDetailResponse(int id, String roomName, List<MemberModel> members, boolean isGroup, @Nullable String roomAvatar) {
        this.id = id;
        this.roomName = roomName;
        this.members = members;
        this.isGroup = isGroup;
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

    @Nullable
    public String getRoomAvatar() {
        return roomAvatar;
    }

    public void setRoomAvatar(@Nullable String roomAvatar) {
        this.roomAvatar = roomAvatar;
    }
}
