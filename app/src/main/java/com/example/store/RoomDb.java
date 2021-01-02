package com.example.store;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MyTABLE.class},version = 1,exportSchema = false)
public abstract class RoomDb  extends RoomDatabase {
    private static RoomDb db;


     static   String  DATABASE_NAME="MYDB";


    public synchronized static RoomDb getInstance(Context context)
    {
        if (db==null)
        {
            db= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDb.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build();
        }


        return db;
    }


    public abstract MainAccess getdata();
}
