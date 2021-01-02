package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RESPOSNHOME {
    @Expose
    @SerializedName("data")
    data_2 data;
    @Expose
    @SerializedName("isSuccess")
    Boolean isSuccess;
    @Expose
    @SerializedName("statusCode")
    int statusCode;
    @Expose
    @SerializedName("message")
    String message;

    public data_2 getData() {
        return data;
    }


    public void setData(data_2 data) {
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
