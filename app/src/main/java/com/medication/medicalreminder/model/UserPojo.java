package com.medication.medicalreminder.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "user")
public class UserPojo {
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @PrimaryKey
    @NonNull
    private String email;

   @Nullable
    private String accessUID;

   @Nullable
   List<Medicine> medicineList;


    public UserPojo(@NonNull String userName, @NonNull String password, @NonNull String email, @Nullable String accessUID, @Nullable List<Medicine> medicineList) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accessUID = accessUID;
        this.medicineList = medicineList;
    }

    @Nullable
    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(@Nullable List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public UserPojo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public String getAccessUID() {
        return accessUID;
    }

    public void setAccessUID(@Nullable String accessUID) {
        this.accessUID = accessUID;
    }
}
