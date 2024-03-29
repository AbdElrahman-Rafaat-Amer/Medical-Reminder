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
   private MedicineViewInterface medicineViewInterface;


    public MedicinePresenter(RepositoryInterface repo, MedicineViewInterface medicineViewInterface ){
        this.repo = repo;
        this.medicineViewInterface = medicineViewInterface;

    }

    @Override
    public void addMedicineHealthTaker(Medicine medicine) {
       repo.addMedicineHealthTaker(medicine);
    }

    @Override
    public void addMedToFireBase(Medicine medicine) {
        repo.AddMedToFirebase(medicine);
    }


    @Override
    public void onInvitationResponse(String successMessage) {

    }

    @Override
    public void onResponseLogin(String reply) {

    }

    @Override
    public void onResponseRegister(String reply) {

    }

    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {

    }

}
