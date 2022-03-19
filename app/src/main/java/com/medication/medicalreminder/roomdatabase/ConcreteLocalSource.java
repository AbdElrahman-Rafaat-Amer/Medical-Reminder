package com.medication.medicalreminder.roomdatabase;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {
    long id;

    private MedicineDAO dao;
    private static ConcreteLocalSource localSource = null;
    private LiveData<List<Medicine>> storedMedicine;
    NetworkDelegate networkDelegate;


    public ConcreteLocalSource(Context context) {
        AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
        dao = db.medicineDAO();
        storedMedicine = dao.getAllMedicine();

    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }


    @Override
    public void insert(Medicine medicine, NetworkDelegate networkDelegate) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                id = dao.insertMedicine(medicine);
                Log.i("TAG", "inside insert local source id is " + id);
                networkDelegate.onSuccessInsert(id);
            }
        }).start();


    }

    @Override
    public void delete(Medicine medicine) {
        Log.i("TAG", "DELETE inside local source activity");
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMedicine(medicine);
            }
        }).start();
    }

    @Override
    public void update(Medicine medicine) {
        Log.i("TAG", "UPDATE inside local source activity");
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.updateMedicine(medicine);
            }
        }).start();

    }

    @Override
    public void getStartDate() {
        dao.getStartDate();
    }

    @Override
    public void downloadDataLocal(List<Medicine> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for ( int i =0; i<list.size();i++)
              //  ) {
                {
                    Medicine medicine = list.get(i);
                      dao.insertMedicine(medicine);
                    Log.i("TAG", "inside insert local source id is " + id);
                }

            }
        }).start();

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
