package com.medication.medicalreminder.addmedicine.persenter;


import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;

public class MedicinePresenter implements MedicinePresenterInterface{

    private RepositoryInterface repo ;

    public MedicinePresenter(RepositoryInterface repo ){
        this.repo = repo;

    }

    @Override
    public void addMedicine(Medicine medicine) {
        repo.insertMedicine(medicine);
    }
}
