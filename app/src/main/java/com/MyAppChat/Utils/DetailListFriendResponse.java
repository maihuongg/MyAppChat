package com.MyAppChat.Utils;

import com.MyAppChat.Model.UserDetail;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DetailListFriendResponse implements Serializable {
    private int id;
    private UserDetail requestID;
    private UserDetail responseID;
    private boolean status;
    private String created;
    private String updated;

    public DetailListFriendResponse(int id, UserDetail requestID, UserDetail responseID, boolean status, String created, String updated) {
        this.id = id;
        this.requestID = requestID;
        this.responseID = responseID;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDetail getRequestID() {
        return requestID;
    }

    public void setRequestID(UserDetail requestID) {
        this.requestID = requestID;
    }

    public UserDetail getResponseID() {
        return responseID;
    }

    public void setResponseID(UserDetail responseID) {
        this.responseID = responseID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    // Constructors, getters, and setters


}
