package com.MyAppChat.Model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class MemberModel implements Serializable {
    private int id;
    private UserMemberChat user;
    private String date_joined;
    private String role;
    private @Nullable String nickname;
    private int room_chat;


    public MemberModel(int id, UserMemberChat user, String date_joined, String role, @Nullable String nickname, int room_chat) {
        this.id = id;
        this.user = user;
        this.date_joined = date_joined;
        this.role = role;
        this.nickname = nickname;
        this.room_chat = room_chat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserMemberChat getUser() {
        return user;
    }

    public void setUser(UserMemberChat user) {
        this.user = user;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Nullable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(@Nullable String nickname) {
        this.nickname = nickname;
    }

    public int getRoom_chat() {
        return room_chat;
    }

    public void setRoom_chat(int room_chat) {
        this.room_chat = room_chat;
    }
}
