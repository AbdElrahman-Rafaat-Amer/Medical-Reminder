package com.medication.medicalreminder.remotedatabase;

public interface NetworkDelegate {

    void onSuccessInvitation(String successMessage);

    void onFailureInvitation(String errorMessage);
}
