package com.example.store;

import com.example.store.Models.storeSetting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data {
  public String getToken() {
      return token;
  }

  public void setToken(String token) {
      this.token = token;
  }

  public String getSecurityKey() {
      return securityKey;
  }

  public void setSecurityKey(String securityKey) {
      this.securityKey = securityKey;
  }


    public com.example.store.appVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(com.example.store.appVersion appVersion) {
        this.appVersion = appVersion;
    }

    @Expose
    @SerializedName("appVersion")
    private appVersion appVersion;




  public int getGender() {
      return gender;
  }

  public void setGender(int gender) {
      this.gender = gender;
  }

  public String getFullName() {
      return fullName;
  }

  public void setFullName(String fullName) {
      this.fullName = fullName;
  }



  public String getBirthDayFa() {
      return birthDayFa;
  }

  public void setBirthDayFa(String birthDayFa) {
      this.birthDayFa = birthDayFa;
  }

  @Expose
  @SerializedName("token")
  private String token;
  @Expose
  @SerializedName("securityKey")
  String securityKey;

    public storeSetting getStoreSetting() {
        return storeSetting;
    }

    public void setStoreSetting(com.example.store.Models.storeSetting storeSetting) {
        this.storeSetting = storeSetting;
    }

    @Expose
  @SerializedName("gender")
  private int gender;


  @Expose
  @SerializedName("fullName")
  private String fullName;





  @Expose
  @SerializedName("birthDayFa")
  private String birthDayFa;


    @Expose
    @SerializedName("storeSetting")
      storeSetting storeSetting;


}
