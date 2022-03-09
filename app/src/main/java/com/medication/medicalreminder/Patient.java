package com.medication.medicalreminder;

import android.graphics.Bitmap;

import java.util.List;

public class Patient {
    private String email;
    private String password;
    private String name;
    private List<Medication> medicationList;
    private Bitmap profileImage;
    private int profileImageTest;

    public Patient(String email) {
        this.email = email;
    }

    public Patient(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Patient() {
    }

    public Patient(String email, String password, String name, List<Medication> medicationList, Bitmap profileImage) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.medicationList = medicationList;
        this.profileImage = profileImage;
    }

    public Patient(String email, String password, String name, int profileImageTest) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImageTest = profileImageTest;
    }

    public Patient(String email, String password, String name, Bitmap profileImage) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public int getProfileImageTest() {
        return profileImageTest;
    }
}
