package com.example.sportsapp.networkHandler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {


//    @SerializedName("_rb")
//    @Expose
//    private Rb rb;

    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

//    public Rb getRb() {
//        return rb;
//    }
//
//    public void setRb(Rb rb) {
//        this.rb = rb;
//    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}