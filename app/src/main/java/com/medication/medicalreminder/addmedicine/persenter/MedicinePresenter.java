package com.medication.medicalreminder.addmedicine.persenter;


import android.util.Log;

import com.medication.medicalreminder.addmedicine.view.MedicineViewInterface;
import com.medication.medicalreminder.addmedicine.view.saveFragment;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class MedicinePresenter implements MedicinePresenterInterface, NetworkDelegate {


    private RepositoryInterface repo ;
    MedicineViewInterface medicineViewInterface;


    public MedicinePresenter(RepositoryInterface repo, MedicineViewInterface medicineViewInterface ){
        this.repo = repo;
        this.medicineViewInterface = medicineViewInterface;

    }

    @Override
    public void addMedicine(Medicine medicine) {
        Log.i("TAG", "inside insert presenter id is ");
        repo.insertMedicine(medicine, this);

    }

    @Override
    public void addMedToFireBase(Medicine medicine) {
        repo.AddMedToFirebase(medicine);
    }


    @Override
    public void onSuccessInvitation(String successMessage) {

    }

    @Override
    public void onFailureInvitation(String errorMessage) {

    }

    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {

    }

    @Override
    public void onSuccessInsert(long id) {
        medicineViewInterface.sendId(id);
    }
}
