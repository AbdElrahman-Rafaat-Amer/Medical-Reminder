package com.medication.medicalreminder.model;

import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.UserPojo;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {


    void insertUserRepository(UserPojo userPojo);

    void insertMedicineRepository();

    void updateMedicineRepository(String UID);

    void deleteMedicineRepository(String UID);

    UserPojo getUserByEmailRepository(String email);

    List<Medication> getAllMedicineRepository(String UID);

    Medication getSpecificMedicineRepository(String UID);

    void addHealthTaker(String email, NetworkDelegate networkDelegate);
}
