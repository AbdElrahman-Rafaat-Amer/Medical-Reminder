package com.medication.medicalreminder.displaymedicine.persenter;

import com.medication.medicalreminder.model.Medicine;

public interface DisplayMedPresenterInterface {
    void deleteMedicine(Medicine medicine);
    void deleteFromFirebase(Medicine medicine);
}
