package com.medication.medicalreminder.model;

import androidx.lifecycle.LiveData;

import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    void addHealthTaker(String email, NetworkDelegate networkDelegate);

    void sendReplyAddHealthTaker(String replyMessage, String UID);

    void signWithEmail(String email, String password, NetworkDelegate networkDelegate);

    void createAccount(UserPojo userPojo, NetworkDelegate networkDelegate);

    void sendReplyOnInvitationOfTaker(boolean isAccept);

    ///////////////
  //  LiveData<List<Medicine>> getStoredMedicine();//room

 //   void insertMedicine(Medicine medicine, NetworkDelegate networkDelegate);

   // void deleteMedicine(Medicine medicine);

   // void updateMedicine(Medicine medicine);

    void AddMedToFirebase(Medicine medicine);

    void getStoredMedicineFireBase(NetworkDelegate networkDelegate);//firebase

   /* void getStartDate();

    void getEnddate();

    void getTime();

    void downloadDataLocal(List<Medicine> list);*/
}
