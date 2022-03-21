package com.medication.medicalreminder.model;

public class UserPojo {
    private String userName;
    private String password;
    private String email;
    private String accessUID;
    private String requestReply;
    private String requesterName;
    private String isYourRequestAccepted;

    public UserPojo(String userName, String password, String email, String accessUID, String requestReply) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accessUID = accessUID;
        this.requestReply = requestReply;
    }

    public UserPojo(String userName, String password, String email, String accessUID, String requestReply, String requesterName, String isYourRequestAccepted) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accessUID = accessUID;
        this.requestReply = requestReply;
        this.requesterName = requesterName;
        this.isYourRequestAccepted = isYourRequestAccepted;
    }

    public UserPojo() {
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getIsYourRequestAccepted() {
        return isYourRequestAccepted;
    }

    public void setIsYourRequestAccepted(String isYourRequestAccepted) {
        this.isYourRequestAccepted = isYourRequestAccepted;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequestReply() {
        return requestReply;
    }

    public void setRequestReply(String requestReply) {
        this.requestReply = requestReply;
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
