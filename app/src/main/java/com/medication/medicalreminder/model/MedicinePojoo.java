package com.medication.medicalreminder.model;

import java.util.List;

public class MedicinePojoo {
    private  String medicineName;
    private  String takenMediceneDate;
    private  String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private int medicineImage;
    private List<MedicinePojoo> medicineList;

    public MedicinePojoo(String medicineName, String takenMediceneDate, int medicineImage , String startDate,String endDate) {
        this.medicineName = medicineName;
        this.takenMediceneDate = takenMediceneDate;
        this.medicineImage = medicineImage;
        this.startDate=startDate;
        this.endDate= endDate;

    }

    public MedicinePojoo() {
    }

    public List<MedicinePojoo> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicinePojoo> medicineList) {
        medicineList.add(new MedicinePojoo("fvjvf","3/12/2020",5,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("agfl","3/12/2020",5 ,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("ayyyyyl","3/12/2020",5,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("jjjj","3/12/2020",5,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("aaaa","3/12/2020",5,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("bbbbb","3/12/2020",5,"12-3-2022","17-3-2022"));
        medicineList.add(new MedicinePojoo("mmmm","3/12/2020",5,"12-3-2022","17-3-2022"));
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

