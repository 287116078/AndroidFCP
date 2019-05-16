package com.shaoxiang.midlayer_net.model;

/**
 * Created by LK on 2017/3/15.
 */

public class ErrorMsg {
    private ErrorType errorType;
    private String errorInfo;

    public ErrorMsg(ErrorType errorType, String errorInfo) {
        this.errorType = errorType;
        this.errorInfo = errorInfo;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
