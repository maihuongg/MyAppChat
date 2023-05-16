package com.MyAppChat.Utils;

import java.io.Serializable;

public class ListFriendResponse implements Serializable {
    private int id;
    private boolean status;
    private String created;
    private String updated;
    private int requestID;
    private int responseID;

    public ListFriendResponse(int id, boolean status, String created, String updated, int requestID, int responseID) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.requestID = requestID;
        this.responseID = responseID;
    }


    public int getId() {
        return id;
    }

    public ListFriendResponse() {
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }
}
