package com.MyAppChat.Model;

import java.io.Serializable;

public class AddFriendModel implements Serializable {
    private int requestIDl;
    private int responseID;

    public AddFriendModel(int requestIDl, int responseID) {
        this.requestIDl = requestIDl;
        this.responseID = responseID;
    }

    public int getRequestIDl() {
        return requestIDl;
    }

    public void setRequestIDl(int requestIDl) {
        this.requestIDl = requestIDl;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }
}
