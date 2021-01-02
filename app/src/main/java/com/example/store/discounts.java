package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class discounts implements Serializable {
    @Expose
    @SerializedName("category")
    category categories;
    @Expose
    @SerializedName("products")
    ArrayList<products> products;

    public category getCategories() {
        return categories;
    }

    public void setCategories(category categories) {
        this.categories = categories;
    }

    public ArrayList<com.example.store.products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<com.example.store.products> products) {
        this.products = products;
    }
}
