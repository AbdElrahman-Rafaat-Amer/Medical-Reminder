package com.medication.medicalreminder.editmedicine.persenter;

import android.util.Log;

import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.FirebaseOperationInterface;

public class EditMedicinePresenter implements EditMedicinePresenterInterface {
    private RepositoryInterface repo ;
    private FirebaseOperationInterface firebaseOperation;

    public EditMedicinePresenter(RepositoryInterface repo, FirebaseOperationInterface firebaseOperation ){
        this.repo = repo;
        this.firebaseOperation = firebaseOperation;

    }

    @Override
    public void update(Medicine medicine) {
        Log.i("TAG", "inside presenter activity");
        repo.updateMedicine(medicine);
    }

    @Override
    public void updateFireBase(Medicine medicine) {
        firebaseOperation.updateMedicine(medicine);
    }
}
