package com.medication.medicalreminder.displaymedicine.view;


import com.medication.medicalreminder.model.Medicine;
import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.List;

public interface AllMedicinesViewInterface {

    void showAllMedicines(List<Medicine> medicines);

    void showMedicine(MedicinePojoo medicine);

    void showErrorMessage(String message);
}
