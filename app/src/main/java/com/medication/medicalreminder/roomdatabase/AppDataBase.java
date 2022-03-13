package com.medication.medicalreminder.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.medication.medicalreminder.model.UserPojo;


@Database(entities = UserPojo.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase appDataBase = null;

    public abstract UserDao movieDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (appDataBase == null)
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "movie").build();
        return appDataBase;
    }
}
