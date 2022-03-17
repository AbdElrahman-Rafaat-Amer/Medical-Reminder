package com.medication.medicalreminder.remotedatabase;

import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface NetworkDelegate {

    void onSuccessInvitation(String successMessage);

    void onFailureInvitation(String errorMessage);

    void onSuccessGetMediciene(List<Medicine> successMessage);

    void onSuccessInsert(long id);
}
