package com.medication.medicalreminder.editmedicine.persenter;


import com.medication.medicalreminder.model.Medicine;

public interface EditMedicinePresenterInterface {

    void update(Medicine medicine);

    void updateFireBase(Medicine medicine);

}
