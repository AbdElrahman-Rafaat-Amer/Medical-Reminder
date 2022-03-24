package com.medication.medicalreminder.healthtaker.displayMedicine.presnter;

import com.medication.medicalreminder.healthtaker.displayMedicine.view.HealthTakerMedicinesViewInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class HealthTakerMedicinePresenter implements HealthTakerMedicinePresenterInterface, NetworkDelegate {
    private RepositoryInterface repo;
    private HealthTakerMedicinesViewInterface medicinesViewInterface;


    public HealthTakerMedicinePresenter(Repository repo, HealthTakerMedicinesViewInterface medicinesViewInterface) {

        this.repo = repo;
        this.medicinesViewInterface = medicinesViewInterface;

    }


    @Override
    public void getStoredMedicineFireBase() {
        repo.getStoredMedicineFireBase(this);
    }

    @Override
    public void getStoredMedicineFireBaseOfHealthTaker() {
        repo.getStoredMedicineFireBaseOfHealthTaker(this);
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
        medicinesViewInterface.showAllMedicines(successMessage);

    }


}