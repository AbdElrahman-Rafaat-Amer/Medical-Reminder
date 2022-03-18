package com.medication.medicalreminder.displaymedicine.view;


import com.medication.medicalreminder.model.Medicine;

import java.util.List;

public interface AllMedicinesViewInterface {

    void showAllMedicines(List<Medicine> medicines);
    void showErrorMessage(String message);
}
