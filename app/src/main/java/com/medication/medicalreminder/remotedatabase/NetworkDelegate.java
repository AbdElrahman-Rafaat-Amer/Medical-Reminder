package com.medication.medicalreminder.remotedatabase;

import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface NetworkDelegate {

    void onInvitationResponse(String successMessage);

    void onResponseLogin(String reply);

    void onResponseRegister(String reply);

    void onSuccessGetMediciene(List<Medicine> successMessage);

}
