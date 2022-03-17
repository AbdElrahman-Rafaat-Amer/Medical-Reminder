package com.medication.medicalreminder.displaymedicine.persenter;
import androidx.lifecycle.LiveData;

import com.medication.medicalreminder.displaymedicine.view.AllMedicinesViewInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.Repository;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class HomeMedicienePresenter implements HomeMedicienePresenterInterface  , NetworkDelegate {
    private RepositoryInterface repo ;
    private AllMedicinesViewInterface allMedicinesViewInterface;


    public HomeMedicienePresenter(Repository repo,AllMedicinesViewInterface allMedicinesViewInterface) {

        this.repo=repo;
        this.allMedicinesViewInterface= allMedicinesViewInterface;

    }
    @Override
    public LiveData<List<Medicine>> getAllMedicines() {
        return repo.getStoredMedicine();
    }

    @Override
    public void getStoredMedicineFireBase() {
         repo.getStoredMedicineFireBase(this);
    }

    @Override
    public void onSuccessInvitation(String successMessage) {
    }
    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {
        allMedicinesViewInterface.showAllMedicines(successMessage);

    }

    @Override
    public void onSuccessInsert(long id) {

    }

    @Override
    public void onFailureInvitation(String errorMessage) {

    }
}