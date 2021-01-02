package com.example.store;

import androidx.room.Entity;

import java.io.Serializable;

public class MODEL_ITEM  implements Serializable {
    int img;
    String name;
    String id;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
