package com.example.sportsapp.utils;

public class ApiErrorModel {

    private String error;
    private Boolean retryStatus;

    public ApiErrorModel(String error,Boolean retryStatus)
    {
        this.error=error;
        this.retryStatus=retryStatus;
    }
    public Boolean getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Boolean retryStatus) {
        this.retryStatus = retryStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
