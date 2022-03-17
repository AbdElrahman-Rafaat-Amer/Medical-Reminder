package com.medication.medicalreminder.addhealthtaker.persenter;

import android.util.Log;

import com.medication.medicalreminder.addhealthtaker.view.AddHealthTakerViewInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class AddHealthTakerPresenter implements AddHealthTakerPresenterInterface, NetworkDelegate {

    private RepositoryInterface repositoryInterface;
    private AddHealthTakerViewInterface viewInterface;

    public AddHealthTakerPresenter(AddHealthTakerViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        this.viewInterface = viewInterface;
        Log.i("TAG", "constructor AddHealthTakerViewInterface: ");
    }


    @Override
    public void sendInvitationRequest(String email) {
       // repositoryInterface.addHealthTaker(email,this);
    }

    @Override
    public String receiveInvitationRequest() {
        return null;
    }

    @Override
    public void onSuccessInvitation(String successMessage) {
        viewInterface.receiveInvitationRequest(successMessage);
    }

    @Override
    public void onFailureInvitation(String errorMessage) {
        viewInterface.receiveInvitationRequest(errorMessage);
    }

    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {
    }

    @Override
    public void onSuccessInsert(long id) {

    }
}
