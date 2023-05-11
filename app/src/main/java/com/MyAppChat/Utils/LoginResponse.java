package com.MyAppChat.Utils;

import com.MyAppChat.Model.UserModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String refreshToken;
    private String accessToken;

    private int userId;

    private String userEmail;
    private String detail;
    private UserModel user;

    private int accessExpires;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getAccessExpires() {
        return accessExpires;
    }

    public void setAccessExpires(int accessExpires) {
        this.accessExpires = accessExpires;
    }

    public LoginResponse(String refreshToken, String accessToken, int userId, String userEmail, String detail, UserModel user, int accessExpires) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.userId = userId;
        this.userEmail = userEmail;
        this.detail = detail;
        this.user = user;
        this.accessExpires = accessExpires;
    }
}
