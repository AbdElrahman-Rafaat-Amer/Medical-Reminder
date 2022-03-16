package com.medication.medicalreminder.model;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicines")
public class Medicine {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "form")
    String form;

    @ColumnInfo(name = "strength")
    String strength;

    @ColumnInfo(name = "reason")
    String reason;

    @ColumnInfo(name = "isDaily")
    String isDaily;

    @ColumnInfo(name = "often")
    String often;

    @ColumnInfo(name = "time")
    String time;

    @ColumnInfo(name = "startDate")
    String startDate;

    @ColumnInfo(name = "endDate")
    String endDate;

    @ColumnInfo(name = "medLeft")
    int medLeft;

    @ColumnInfo(name = "refillLimit")
    int refillLimit;

    @ColumnInfo(name = "image")
    int image;

   @Nullable
   @ColumnInfo(name="uid")
   String Uid ;

    int strengthNum;
    String strengthDose;



    private static Medicine instance = null;

    private Medicine() { }

    public static Medicine getInstance() {
        if(instance == null){
            instance = new Medicine();
        }
        return instance;
    }

    @Nullable
    public String getUid() {
        return Uid;
    }

    public void setUid(@Nullable String uid) {
        Uid = uid;
    }

    public Medicine(int id, String name, String form, String strength, String reason, String isDaily, String often, String time, String startDate, String endDate, int medLeft, int refillLimit, int image, String Uid) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.strength = strength;
        this.reason = reason;
        this.isDaily = isDaily;
        this.often = often;
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medLeft = medLeft;
        this.refillLimit = refillLimit;
        this.image = image;
        this.Uid = Uid;
    }

    public int getStrengthNum() {
        return strengthNum;
    }

    public void setStrengthNum(int strengthNum) {
        this.strengthNum = strengthNum;
    }

    public String getStrengthDose() {
        return strengthDose;
    }

    public void setStrengthDose(String strengthDose) {
        this.strengthDose = strengthDose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIsDaily() {
        return isDaily;
    }

    public void setIsDaily(String isDaily) {
        this.isDaily = isDaily;
    }

    public String getOften() {
        return often;
    }

    public void setOften(String often) {
        this.often = often;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public int getMedLeft() {
        return medLeft;
    }

    public void setMedLeft(int medLeft) {
        this.medLeft = medLeft;
    }

    public int getRefillLimit() {
        return refillLimit;
    }

    public void setRefillLimit(int refillLimit) {
        this.refillLimit = refillLimit;
    }

    public int getImage() {
        return image;
    }

    public int setImage(int image) {
        this.image = image;
        return image;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", strength='" + strength + '\'' +
                ", reason='" + reason + '\'' +
                ", isDaily='" + isDaily + '\'' +
                ", often='" + often + '\'' +
                ", time='" + time + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", medLeft=" + medLeft +
                ", refillLimit=" + refillLimit +
                ", image=" + image +
                '}';
    }
}