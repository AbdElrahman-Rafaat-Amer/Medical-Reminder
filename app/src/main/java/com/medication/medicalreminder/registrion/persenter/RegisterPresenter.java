package com.medication.medicalreminder.registrion.persenter;


import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.model.UserPojo;
import com.medication.medicalreminder.registrion.view.RegisterViewInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class RegisterPresenter implements RegisterPresenterInterface, NetworkDelegate {

    private RepositoryInterface repositoryInterface;
    private RegisterViewInterface viewInterface;

    public RegisterPresenter(RegisterViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        this.viewInterface = viewInterface;
    }

    @Override
    public void createAccount(UserPojo userPojo) {
        repositoryInterface.createAccount(userPojo, this);
    }

    @Override
    public void onResponseLogin(String reply) {

    }

    @Override
    public void onResponseRegister(String reply) {
        viewInterface.registerResponse(reply);
    }

    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {

    }

    @Override
    public void onSuccessInsert(long id) {

    }

    @Override
    public void onInvitationResponse(String successMessage) {

    }


}
