package com.medication.medicalreminder.remotedatabase;

import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.UserPojo;

import java.util.List;

public interface FirebaseOperationInterface {

    void insertUser(UserPojo userPojo);

    void insertMedicine();

    void updateMedicine(String UID);

    void deleteMedicine(String UID);

    UserPojo getUserByEmail(String email);

    List<Medication> getAllMedicine(String UID);

    Medication getSpecificMedicine(String UID);

    void addHealthTaker(String email, NetworkDelegate networkDelegate);

    void addMedToFireBase(Medicine medicine);

}
