package com.medication.medicalreminder.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserPojo {
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @PrimaryKey
    @NonNull
    private String email;

    private String accessUID;

    public UserPojo(String userName, String password, String email, String accessUID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accessUID = accessUID;
    }

    public UserPojo(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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

    public String getAccessUID() {
        return accessUID;
    }

    public void setAccessUID(String accessUID) {
        this.accessUID = accessUID;
    }
}
