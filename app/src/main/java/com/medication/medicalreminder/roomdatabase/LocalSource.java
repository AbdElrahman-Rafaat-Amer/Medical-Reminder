package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Query;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface LocalSource {

    void insert (Medicine medicine);
    void delete (Medicine medicine);
     void getStartDate();

     void  getEnddate();
     void getTime();


    @Query("Select * From medicines")
    LiveData<List<Medicine>> getAllMedicine();
}
