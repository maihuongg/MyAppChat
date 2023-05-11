package com.MyAppChat.Utils;

import com.MyAppChat.Model.UserModel;

public class Response {
    private String error;
    private String message;
    private UserModel user;

    public Response(String error, String message, UserModel user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
