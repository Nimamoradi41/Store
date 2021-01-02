package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class sliders implements Serializable {
    @Expose
    @SerializedName("fileName")
    String fileName;
    @Expose
    @SerializedName("type")
    int type;

    @Expose
    @SerializedName("actionId")
    String actionId;


//    @SerializedName("filterProductJson")
//    String filterProductJson;

    @Expose
    @SerializedName("id")
    String id;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

//    public String getFilterProductJson() {
//        return filterProductJson;
//    }
//
//    public void setFilterProductJson(String filterProductJson) {
//        this.filterProductJson = filterProductJson;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
