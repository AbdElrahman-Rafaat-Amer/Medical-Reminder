package com.medication.medicalreminder.healthtaker.persenter;

import android.util.Log;

import com.medication.medicalreminder.model.RepositoryInterface;

public class EditHealthTakerPresenter implements EditHealthTakerPresenterInterface {
    private final  String TAG = "EditHealthTakerPresnter";
    private RepositoryInterface repositoryInterface;


    public EditHealthTakerPresenter(RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        Log.i(TAG, "EditHealthTakerPresenter: ");
    }


    @Override
    public void sendReplyOnInvitation(boolean isAccept) {
        repositoryInterface.sendReplyOnInvitationOfTaker(isAccept);
    }
}
