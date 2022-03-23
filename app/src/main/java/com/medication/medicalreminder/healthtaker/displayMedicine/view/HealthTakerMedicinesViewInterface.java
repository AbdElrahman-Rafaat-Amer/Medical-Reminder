package com.medication.medicalreminder.healthtaker.displayMedicine.view;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface HealthTakerMedicinesViewInterface {

    void showAllMedicines(List<Medicine> medicines);
    void showErrorMessage(String message);
}
