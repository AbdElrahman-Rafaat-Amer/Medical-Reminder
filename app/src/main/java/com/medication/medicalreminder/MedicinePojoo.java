package com.medication.medicalreminder;

import java.util.ArrayList;
import java.util.List;

public class MedicinePojoo {
private  String medicineName;
 private  String takenMediceneDate;
    private int medicineImage;
    private List<MedicinePojoo> medicineList;

    public MedicinePojoo(String medicineName, String takenMediceneDate, int medicineImage) {
        this.medicineName = medicineName;
        this.takenMediceneDate = takenMediceneDate;
        this.medicineImage = medicineImage;


    }

    public MedicinePojoo() {
    }

    public List<MedicinePojoo> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicinePojoo> medicineList) {
        medicineList.add(new MedicinePojoo("fvjvf","3/12/2020",5));
        medicineList.add(new MedicinePojoo("agfl","3/12/2020",5));
        medicineList.add(new MedicinePojoo("ayyyyyl","3/12/2020",5));
        medicineList.add(new MedicinePojoo("jjjj","3/12/2020",5));
        medicineList.add(new MedicinePojoo("aaaa","3/12/2020",5));
        medicineList.add(new MedicinePojoo("bbbbb","3/12/2020",5));
        medicineList.add(new MedicinePojoo("mmmm","3/12/2020",5));
        this.medicineList = medicineList;

    }

    public int getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(int medicineImage) {
        this.medicineImage = medicineImage;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getTakenMediceneDate() {
        return takenMediceneDate;
    }

    public void setTakenMediceneDate(String takenMediceneDate) {
        this.takenMediceneDate = takenMediceneDate;
    }

}

