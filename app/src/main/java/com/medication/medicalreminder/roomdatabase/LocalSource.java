package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Query;


import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public interface LocalSource {

    void insert (Medicine medicine, NetworkDelegate networkDelegate);
    void delete (Medicine medicine);
    void update(Medicine medicine);
     void getStartDate();
    void downloadDataLocal(List<Medicine> list);

     void  getEnddate();
     void getTime();


    @Query("Select * From medicines")
    LiveData<List<Medicine>> getAllMedicine();
}
