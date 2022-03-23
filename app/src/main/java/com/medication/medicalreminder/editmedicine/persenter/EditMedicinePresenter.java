package com.medication.medicalreminder.editmedicine.persenter;

import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;

public class EditMedicinePresenter implements EditMedicinePresenterInterface {
    private RepositoryInterface repo;
    private FirebaseOperationInterface firebaseOperation;

    public EditMedicinePresenter(RepositoryInterface repo, FirebaseOperationInterface firebaseOperation) {
        this.repo = repo;
        this.firebaseOperation = firebaseOperation;

    }


    @Override
    public void updateHealthTaker(Medicine medicine) {
        repo.updateMedicineHealthTaker(medicine);
    }

    @Override
    public void updateFireBase(Medicine medicine) {
        repo.updateMedicine(medicine);

    }
}
