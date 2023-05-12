package com.MyAppChat.Utils;

import com.MyAppChat.Model.UserModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String refresh;
    private String access;
    private int id;
    private String user;
    private int access_expires;
    private int refresh_expires;
    private String detail; //trả về khi email ||pass error

    public LoginResponse(String refresh, String access, int id, String user, int access_expires, int refresh_expires, String detail) {
        this.refresh = refresh;
        this.access = access;
        this.id = id;
        this.user = user;
        this.access_expires = access_expires;
        this.refresh_expires = refresh_expires;
        this.detail = detail;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAccess_expires() {
        return access_expires;
    }

    public void setAccess_expires(int access_expires) {
        this.access_expires = access_expires;
    }

    public int getRefresh_expires() {
        return refresh_expires;
    }

    public void setRefresh_expires(int refresh_expires) {
        this.refresh_expires = refresh_expires;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}