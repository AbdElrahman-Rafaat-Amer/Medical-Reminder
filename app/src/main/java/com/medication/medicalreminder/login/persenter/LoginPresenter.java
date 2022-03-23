package com.medication.medicalreminder.login.persenter;

import android.util.Log;

import com.medication.medicalreminder.login.view.LoginViewInterface;
import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.RepositoryInterface;
import com.medication.medicalreminder.remotedatabase.NetworkDelegate;

import java.util.List;

public class LoginPresenter implements LoginPresenterInterface, NetworkDelegate {

    private RepositoryInterface repositoryInterface;
    private LoginViewInterface viewInterface;

    public LoginPresenter(LoginViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        this.viewInterface = viewInterface;
    }

    @Override
    public void signWithEmail(String email, String password) {
        repositoryInterface.signWithEmail(email, password, this);
    }

    @Override
    public void onResponseLogin(String reply) {
        viewInterface.loginResponse(reply);
    }

    @Override
    public void onResponseRegister(String reply) {

    }

    @Override
    public void onSuccessGetMediciene(List<Medicine> successMessage) {

    }


    @Override
    public void onInvitationResponse(String successMessage) {

    }



}
