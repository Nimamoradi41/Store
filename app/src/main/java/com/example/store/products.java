package com.example.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class products implements Serializable {
    @Expose
    @SerializedName("title")
    String title;

//    @SerializedName("images")
//    ArrayList<String> images;
//    // TODO CHANGE IT
    @Expose
    @SerializedName("firstImage")
    String firstImage;

    @Expose
    @SerializedName("description")
    String description;
    @Expose
    @SerializedName("price")
    int price;

    @Expose
    @SerializedName("discountPercent")
    int discountPercent;
    @Expose
    @SerializedName("priceAfterDiscount")
    int priceAfterDiscount;

    @Expose
    @SerializedName("count")
    int count;


    @Expose
    @SerializedName("maxCountReserve")
    int maxCountReserve;





    @Expose
    @SerializedName("barcode")
    String barcode;





    @Expose
    @SerializedName("categoryProductId")
    String categoryProductId;

    @Expose
    @SerializedName("brandId")
    String brandId;

    @Expose
    @SerializedName("id")
    String id;
    @Expose
    @SerializedName("priceForShow")
    String priceForShow;


    @Expose
    @SerializedName("priceAfterDiscountForShow")
    String priceAfterDiscountForShow;

    @Expose
    @SerializedName("currentReserved")
    int currentReserved;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public ArrayList<String> getImages() {
//        return images;
//    }
//
//    public void setImages(ArrayList<String> images) {
//        this.images = images;
//    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(int priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxCountReserve() {
        return maxCountReserve;
    }

    public void setMaxCountReserve(int maxCountReserve) {
        this.maxCountReserve = maxCountReserve;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategoryProductId() {
        return categoryProductId;
    }

    public void setCategoryProductId(String categoryProductId) {
        this.categoryProductId = categoryProductId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriceForShow() {
        return priceForShow;
    }

    public void setPriceForShow(String priceForShow) {
        this.priceForShow = priceForShow;
    }

    public String getPriceAfterDiscountForShow() {
        return priceAfterDiscountForShow;
    }

    public void setPriceAfterDiscountForShow(String priceAfterDiscountForShow) {
        this.priceAfterDiscountForShow = priceAfterDiscountForShow;
    }

    public int getCurrentReserved() {
        return currentReserved;
    }

    public void setCurrentReserved(int currentReserved) {
        this.currentReserved = currentReserved;
    }
}
