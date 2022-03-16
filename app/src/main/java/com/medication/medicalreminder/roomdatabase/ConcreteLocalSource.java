package com.medication.medicalreminder.roomdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private MedicineDAO dao;
    private static ConcreteLocalSource localSource = null;
    private LiveData<List<Medicine>> storedMedicine;


    public ConcreteLocalSource(Context context) {
        AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
        dao = db.medicineDAO();
        storedMedicine = dao.getAllMedicine();

    }

    public static ConcreteLocalSource getInstance(Context context){
        if (localSource == null){
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }


    @Override
    public void insert(Medicine medicine) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertMedicine(medicine);
            }
        }).start();
    }

    @Override
    public void delete(Medicine medicine) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMedicine(medicine);
            }
        }).start();
    }

    @Override
    public void getStartDate() {
        dao.getStartDate();
    }
    @Override
    public void getEnddate() {
        dao.getEnddate();
    }
    @Override
    public void getTime() {
        dao.getTime();
    }
    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return storedMedicine;
    }
}
