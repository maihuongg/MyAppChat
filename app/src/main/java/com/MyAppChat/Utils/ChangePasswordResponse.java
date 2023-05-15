package com.MyAppChat.Utils;

import java.io.Serializable;

public class ChangePasswordResponse implements Serializable {
    private boolean status;

    public ChangePasswordResponse(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
