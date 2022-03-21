package com.medication.medicalreminder.remotedatabase;

import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.UserPojo;

import java.util.List;

public interface FirebaseOperationInterface {


    void addHealthTaker(String email, NetworkDelegate networkDelegate);

    void sendReplyAddHealthTaker(String replyMessage, String UID);

    void signWithEmail(String email, String password, NetworkDelegate networkDelegate);

    void createUserWithEmailAndPassword(UserPojo user, NetworkDelegate networkDelegate);

    void sendReplyOnInvitationOfTaker(boolean isAccept);

    //////////////
    void addMedToFireBase(Medicine medicine);

    void getAllMedicine(NetworkDelegate networkDelegate);
    void insertMedicine();

    void updateMedicine(Medicine medicine);

    void deleteMedicineFB(Medicine medicine);

   /* UserPojo getUserByEmail(String email);

    List<Medication> getAllMedicine(String UID);

    Medication getSpecificMedicine(String UID);*/
}
