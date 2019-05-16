package com.shaoxiang.midlayer_net.model;

/**
 * Created by LK on 2017/3/15.
 */

public class NetResponse {
    private boolean success;
    private int code;
    private ErrorMsg errorMsg;
    private String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
