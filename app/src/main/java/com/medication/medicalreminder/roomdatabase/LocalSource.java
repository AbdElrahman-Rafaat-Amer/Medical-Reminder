package com.medication.medicalreminder.roomdatabase;

import androidx.lifecycle.LiveData;

import com.medication.medicalreminder.model.MedicinePojoo;
import com.medication.medicalreminder.model.UserPojo;

import java.util.List;

public interface LocalSource {

    LiveData<List<MedicinePojoo>> getAllStoredMedicines();

    void insertMedicineLocalSource(MedicinePojoo medicinePojoo);

    void deleteMovieLocalSource(MedicinePojoo medicinePojoo);

    void updateMedicine(String UID);
}
