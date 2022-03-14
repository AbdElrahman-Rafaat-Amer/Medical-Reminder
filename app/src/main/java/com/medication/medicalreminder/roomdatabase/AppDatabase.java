package com.medication.medicalreminder.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.medication.medicalreminder.model.Medicine;


@Database(entities = {Medicine.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;
    public abstract MedicineDAO medicineDAO();
    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Medicine")
                    .build();
        }
        return instance;
    }

}
