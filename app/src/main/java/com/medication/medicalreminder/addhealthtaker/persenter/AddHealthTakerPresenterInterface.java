package com.medication.medicalreminder.addhealthtaker.persenter;

public interface AddHealthTakerPresenterInterface {
    void sendInvitationRequest(String email);

    String receiveInvitationRequest();
}
