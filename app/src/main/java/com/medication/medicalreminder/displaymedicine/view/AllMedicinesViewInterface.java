package com.medication.medicalreminder.displaymedicine.view;


import com.medication.medicalreminder.model.MedicinePojoo;

import java.util.List;

public interface AllMedicinesViewInterface {

    void showAllMedicines(List<MedicinePojoo> medicines);

    void showMedicine(MedicinePojoo medicine);

    void showErrorMessage(String message);
}
