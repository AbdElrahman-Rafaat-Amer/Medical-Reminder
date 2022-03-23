package com.medication.medicalreminder.displaymedicine.persenter;

import com.medication.medicalreminder.model.Medicine;

public interface DisplayMedPresenterInterface {

    void deleteMedicineHealthTaker(Medicine medicine);

    void deleteFromFirebase(Medicine medicine);
}
