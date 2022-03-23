package com.medication.medicalreminder.displaymedicine.persenter;

import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;

public class DisplayMedPresenter implements DisplayMedPresenterInterface {
    private RepositoryInterface repo;
    private FirebaseOperationInterface firebaseOperation;

    public DisplayMedPresenter(RepositoryInterface repo, FirebaseOperationInterface firebaseOperation) {
        this.repo = repo;
        this.firebaseOperation = firebaseOperation;

    }


    @Override
    public void deleteMedicineHealthTaker(Medicine medicine) {
        repo.deleteMedicineHealthTaker(medicine);
    }

    @Override
    public void deleteFromFirebase(Medicine medicine) {
        repo.deleteFromFirebase(medicine);
    }


}

