package com.medication.medicalreminder.roomdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.List;

public class LocalSourceConcreteClass implements LocalSource {
    private UserDao userDao;
    private static LocalSourceConcreteClass sourceConcreteClass = null;
    private LiveData<List<MedicinePojoo>> storedMedicines;

    private LocalSourceConcreteClass(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        userDao = appDataBase.movieDao();
       // storedMedicines = userDao.getAllMedicines();
    }

    public static LocalSourceConcreteClass getInstance(Context context) {
        if (sourceConcreteClass == null)
            sourceConcreteClass = new LocalSourceConcreteClass(context);
        return sourceConcreteClass;
    }


    @Override
    public LiveData<List<MedicinePojoo>> getAllStoredMedicines() {
        return null;
    }

    @Override
    public void insertMedicineLocalSource(MedicinePojoo medicinePojoo) {

    }

    @Override
    public void deleteMovieLocalSource(MedicinePojoo medicinePojoo) {

    }

    @Override
    public void updateMedicine(String UID) {

    }
}
