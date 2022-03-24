package com.medication.medicalreminder.model;

import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

public interface RepositoryInterface {

    void addHealthTaker(String email, NetworkDelegate networkDelegate);

    void sendReplyAddHealthTaker(String replyMessage, String UID);

    void signWithEmail(String email, String password, NetworkDelegate networkDelegate);

    void createAccount(UserPojo userPojo, NetworkDelegate networkDelegate);

    void sendReplyOnInvitationOfTaker(boolean isAccept);

    void AddMedToFirebase(Medicine medicine);

    void addMedicineHealthTaker(Medicine medicine);

    void getStoredMedicineFireBase(NetworkDelegate networkDelegate);//firebase

    void getStoredMedicineFireBaseOfHealthTaker(NetworkDelegate networkDelegate);

    void updateMedicineHealthTaker(Medicine medicine);

    void updateMedicine(Medicine medicine);

    void deleteMedicineHealthTaker(Medicine medicine);

    void deleteFromFirebase(Medicine medicine);

}
