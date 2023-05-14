package com.MyAppChat.Utils;

public class RegisterRespone {
    private int id;
    private String email;
    private String gender;
    private String birthday;
    private String password;
    private String first_name;
    private String last_name;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterRespone(int id, String email, String gender, String birthday, String password, String first_name, String last_name, String token) {
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.token = token;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
