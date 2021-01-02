package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Respons_AddEditDeleteItem_Card {
    @Expose
    @SerializedName("data")
    int data;
    @Expose
    @SerializedName("isSuccess")
    Boolean isSuccess;
    @Expose
    @SerializedName("statusCode")
    int statusCode;

    @Expose
    @SerializedName("message")
    String message;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
