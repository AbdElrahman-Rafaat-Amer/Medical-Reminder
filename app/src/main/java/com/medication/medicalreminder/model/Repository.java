package com.medication.medicalreminder.model;

import android.content.Context;

import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;
import com.medication.medicalreminder.roomdatabase.LocalSource;

public class Repository implements RepositoryInterface {

    private static Repository repository = null;
    private Context context;
    private FirebaseOperationInterface remoteSource;
    private LocalSource localSource;

    private Repository(Context context, LocalSource localSource, FirebaseOperationInterface remoteSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    public static Repository getInstance(Context context, LocalSource localSource, FirebaseOperationInterface remoteSource) {
        if (repository == null) {
            repository = new Repository(context, localSource, remoteSource);
        }
        return repository;
    }


    @Override
    public void addHealthTaker(String email, NetworkDelegate networkDelegate) {
        remoteSource.addHealthTaker(email, networkDelegate);
    }

    @Override
    public void sendReplyAddHealthTaker(String replyMessage, String UID) {
        remoteSource.sendReplyAddHealthTaker(replyMessage, UID);
    }

    @Override
    public void signWithEmail(String email, String password, NetworkDelegate networkDelegate) {
        remoteSource.signWithEmail(email, password, networkDelegate);

    }

    @Override
    public void createAccount(UserPojo userPojo, NetworkDelegate networkDelegate) {
        remoteSource.createUserWithEmailAndPassword(userPojo, networkDelegate);
    }

    @Override
    public void sendReplyOnInvitationOfTaker(boolean isAccept) {
        remoteSource.sendReplyOnInvitationOfTaker(isAccept);
    }


    @Override
    public void AddMedToFirebase(Medicine medicine) {
        remoteSource.addMedToFireBase(medicine);
    }

    @Override
    public void addMedicineHealthTaker(Medicine medicine) {
        remoteSource.addMedicineHealthTaker(medicine);
    }

    @Override
    public void getStoredMedicineFireBase(NetworkDelegate networkDelegate) {
        remoteSource.getAllMedicine(networkDelegate);

    }

    @Override
    public void getStoredMedicineFireBaseOfHealthTaker(NetworkDelegate networkDelegate) {
        remoteSource.getAllMedicineOfHealthTaker(networkDelegate);
    }

    @Override
    public void updateMedicineHealthTaker(Medicine medicine) {
        remoteSource.updateMedicineHealthTaker(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        remoteSource.updateMedicine(medicine);
    }

    @Override
    public void deleteMedicineHealthTaker(Medicine medicine) {
        remoteSource.deleteMedicineHealthTaker(medicine);
    }

    @Override
    public void deleteFromFirebase(Medicine medicine) {
        remoteSource.deleteMedicineFB(medicine);
    }


}
