package com.medication.medicalreminder.remotedatabase;

import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.UserPojo;

public interface FirebaseOperationInterface {


    void addHealthTaker(String email, NetworkDelegate networkDelegate);

    void sendReplyAddHealthTaker(String replyMessage, String UID);

    void signWithEmail(String email, String password, NetworkDelegate networkDelegate);

    void createUserWithEmailAndPassword(UserPojo user, NetworkDelegate networkDelegate);

    void sendReplyOnInvitationOfTaker(boolean isAccept);

    void addMedToFireBase(Medicine medicine);

    void addMedicineHealthTaker(Medicine medicine);

    void getAllMedicine(NetworkDelegate networkDelegate);

    void getAllMedicineOfHealthTaker(NetworkDelegate networkDelegate);

    void updateMedicine(Medicine medicine);

    void updateMedicineHealthTaker(Medicine medicine);

    void deleteMedicineFB(Medicine medicine);

    void deleteMedicineHealthTaker(Medicine medicine);

}
