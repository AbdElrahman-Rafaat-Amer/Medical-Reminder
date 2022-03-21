package com.medication.medicalreminder;

import android.util.Log;

import com.medication.medicalreminder.model.RepositoryInterface;

public class MainPresenter implements MainPresenterInterface{

    private RepositoryInterface repositoryInterface;
    private MainViewInterface viewInterface;

    public MainPresenter(MainViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
        this.viewInterface = viewInterface;
        Log.i("TAG", "constructor MainPresenter: ");
    }

    @Override
    public void sendReplyToHealthTaker(String replyMessage, String UID) {
        Log.i("TAG", "sendReplyToHealthTaker: ------------------------------------------------------------------------------");
        repositoryInterface.sendReplyAddHealthTaker(replyMessage, UID);
    }
}
