package com.medication.medicalreminder.model;

import android.content.Context;

import androidx.lifecycle.LiveData;


import com.medication.medicalreminder.Medication;
import com.medication.medicalreminder.remotedatabase.FirebaseOperation;
import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;
import com.medication.medicalreminder.roomdatabase.LocalSource;

import java.util.List;

public class Repository implements RepositoryInterface{

    private Context context ;
    LocalSource localSource ;
    private static  Repository repository = null;
    private FirebaseOperationInterface remoteSource;

    public Repository (Context context , LocalSource localSource, FirebaseOperationInterface remoteSource) {
        this.context = context ;
        this.localSource = localSource ;
        this.remoteSource =remoteSource;

    }

    public static Repository getInstance(Context context, LocalSource localSource,FirebaseOperationInterface remoteSource) {
        if(repository == null){
            repository = new Repository(context, localSource,remoteSource);
        }
        return repository;
    }



    @Override
    public LiveData<List<Medicine>> getStoredMedicine() {
        return localSource.getAllMedicine();
    }

    @Override
    public void insertMedicine(Medicine medicine) {
          localSource.insert(medicine);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
         localSource.delete(medicine);
    }

    @Override
    public void AddMedToFirebase(Medicine medicine) {
        remoteSource.addMedToFireBase(medicine);
    }


}
