package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class category implements Serializable {
    @Expose
    @SerializedName("title")
    String title;
    @Expose
    @SerializedName("image1")
    String image1;
    @Expose
    @SerializedName("image2")
    String image2;

    @Expose
    @SerializedName("id")
    String id;
    @Expose
    @SerializedName("description")
    String description;


    @Expose
    @SerializedName("subCategory")
    ArrayList<subCategory> subCategory;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<com.example.store.subCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ArrayList<com.example.store.subCategory> subCategory) {
        this.subCategory = subCategory;
    }
}
