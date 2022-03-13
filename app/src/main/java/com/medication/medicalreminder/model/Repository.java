package com.medication.medicalreminder.model;

import android.content.Context;

import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.UserPojo;
import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;
import com.medication.medicalreminder.roomdatabase.LocalSource;

import java.util.List;

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
    public void insertUserRepository(UserPojo userPojo) {

    }

    @Override
    public void insertMedicineRepository() {

    }

    @Override
    public void updateMedicineRepository(String UID) {

    }

    @Override
    public void deleteMedicineRepository(String UID) {

    }

    @Override
    public UserPojo getUserByEmailRepository(String email) {
        return null;
    }

    @Override
    public List<Medication> getAllMedicineRepository(String UID) {
        return null;
    }

    @Override
    public Medication getSpecificMedicineRepository(String UID) {
        return null;
    }

    @Override
    public void addHealthTaker(String email, NetworkDelegate networkDelegate) {
        remoteSource.addHealthTaker(email, networkDelegate);
    }
}
