package com.medication.medicalreminder.model;

import androidx.lifecycle.LiveData;

import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    LiveData<List<Medicine>> getStoredMedicine ();
    void insertMedicine (Medicine medicine);
    void deleteMedicine (Medicine medicine);


}
