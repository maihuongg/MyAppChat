package com.MyAppChat.Utils;

import java.io.Serializable;

public class ProfileResponse implements Serializable {

    private int id;
    private String last_login;
    private boolean is_superuser;
    private String username;
    private boolean is_staff;
    private boolean is_active;
    private String date_joined;
    private String email;
    private String first_name;
    private String last_name;
    private String cover;
    private String avatar;
    private String gender;
    private String birthday;
    private String created;
    private String updated;
    private boolean online;

    public ProfileResponse(int id, String last_login, boolean is_superuser, String username, boolean is_staff, boolean is_active, String date_joined, String email, String first_name, String last_name, String cover, String avatar, String gender, String birthday, String created, String updated, boolean online) {
        this.id = id;
        this.last_login = last_login;
        this.is_superuser = is_superuser;
        this.username = username;
        this.is_staff = is_staff;
        this.is_active = is_active;
        this.date_joined = date_joined;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cover = cover;
        this.avatar = avatar;
        this.gender = gender;
        this.birthday = birthday;
        this.created = created;
        this.updated = updated;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public boolean isIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
