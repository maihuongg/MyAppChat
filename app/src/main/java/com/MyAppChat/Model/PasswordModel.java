package com.MyAppChat.Model;

import java.io.Serializable;

public class PasswordModel implements Serializable {
    private String password;

    public PasswordModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
