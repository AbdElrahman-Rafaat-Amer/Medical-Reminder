package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

@Dao
public interface MedicineDAO {
    @Query("Select * From medicines")
    LiveData<List<Medicine>> getAllMedicine();

    @Insert
    void  insertMedicine (Medicine medicine);
    @Delete
    void  deleteMedicine (Medicine medicine);

    @Query("SELECT startDate FROM medicines ")
    String  getStartDate();

    @Query("SELECT startDate FROM medicines ")
    String getEnddate();

    @Query("SELECT time FROM medicines ")
    String getTime();

    @Update
    void updateMedicine(Medicine medicine);





}
