package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface LocalSource {

    void insert (Medicine medicine);
    void delete (Medicine medicine);
    LiveData<List<Medicine>> getAllMedicine();
}
