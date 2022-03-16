package com.medication.medicalreminder.displaymedicine.view;

import com.medication.medicalreminder.model.Medicine;

public interface DisplayMedInterface {
    void deleteMedicine(Medicine medicine);
    void deleteFromFirebase(Medicine medicine);
}
