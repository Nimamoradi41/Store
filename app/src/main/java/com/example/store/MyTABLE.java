package com.example.store;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// create Table
@Entity(tableName = "MyTABLE")
public class MyTABLE implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name = "Name")
    private  String Name;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
