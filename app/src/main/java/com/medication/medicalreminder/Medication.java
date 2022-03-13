package com.medication.medicalreminder;

import java.util.Date;
import java.util.List;

public class Medication {
    private String name;
    private int strength;
    private String reputation;
    private List<Date> medicationTime;
    private int amount;
    int refillNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public List<Date> getMedicationTime() {
        return medicationTime;
    }

    public void setMedicationTime(List<Date> medicationTime) {
        this.medicationTime = medicationTime;
    }

    public Medication(String name, int strength, String reputation, List<Date> medicationTime) {
        this.name = name;
        this.strength = strength;
        this.reputation = reputation;
        this.medicationTime = medicationTime;
    }
}
