package com.example.store;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class productBoxDtos implements Serializable {
    @SerializedName("category")
    category category;

    @SerializedName("products")
    ArrayList<products> products;

    public com.example.store.category getCategory() {
        return category;
    }

    public void setCategory(com.example.store.category category) {
        this.category = category;
    }

    public ArrayList<com.example.store.products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<com.example.store.products> products) {
        this.products = products;
    }
}
