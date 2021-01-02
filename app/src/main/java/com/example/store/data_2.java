package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class data_2  implements  Serializable {
    @Expose
    @SerializedName("categories")
    private   ArrayList<categories> categories;
    @Expose
    @SerializedName("specials")
    private  specials specials;
    @Expose
    @SerializedName("discounts")
    private  discounts discounts ;
    @Expose
    @SerializedName("sliders")
    private  ArrayList<sliders> sliders;
    @Expose
    @SerializedName("productBoxDtos")
    private  ArrayList<productBoxDtos> productBoxDtos;

    @Expose
    @SerializedName("cartCount")
   private   int cartCount;

    public ArrayList<com.example.store.categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<com.example.store.categories> categories) {
        this.categories = categories;
    }
//
    public specials getSpecials() {
        return specials;
    }

    public void setSpecials(specials specials) {
        this.specials = specials;
    }
//
    public  discounts getDiscounts() {
        return discounts;
    }

    public void setDiscounts(discounts discounts) {
        this.discounts = discounts;
    }
//
    public ArrayList<com.example.store.sliders> getSliders() {
        return sliders;
    }

    public void setSliders(ArrayList<com.example.store.sliders> sliders) {
        this.sliders = sliders;
    }

    public ArrayList<com.example.store.productBoxDtos> getProductBoxDtos() {
        return productBoxDtos;
    }

    public void setProductBoxDtos(ArrayList<com.example.store.productBoxDtos> productBoxDtos) {
        this.productBoxDtos = productBoxDtos;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }
}
