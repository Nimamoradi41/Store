package com.example.store;

import com.google.gson.annotations.SerializedName;

public class Model_Checkout {
    @SerializedName("AddressId")
    String AddressId;

    public String getAddressId() {
        return AddressId;
    }

    public void setAddressId(String addressId) {
        AddressId = addressId;
    }

    public String getDiscountCode() {
        return DiscountCode;
    }

    public void setDiscountCode(String discountCode) {
        DiscountCode = discountCode;
    }

    @SerializedName("DiscountCode")
    String DiscountCode;
}
