package com.example.store;

import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MainAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inser(MyTABLE myTABLE);


    @Delete()
    void del(MyTABLE RECORD);


    @Delete()
    void reset(List<MyTABLE> myTABLES);




    @Query("UPDATE  MyTABLE SET Name=:name  WHERE Id =:id")
    void update(int id,String name );




    @Query("SELECT * FROM  MyTABLE")
    List<MyTABLE> getall();






}
