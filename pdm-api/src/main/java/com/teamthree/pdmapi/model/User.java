package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("userId") private final String userId;
    @JsonProperty("userLogin") private final String useLogin;
    @JsonProperty("userPassword") private final String userPassword;
    @JsonProperty("userFirstName") private final String userFirstName;
    @JsonProperty("userLastName") private final String userLastName;
    @JsonProperty("userCreationDate") private final String userCreationDate;
    @JsonProperty("userEmail") private final String userEmail;
    @JsonProperty("userLastAccessDate") private String userLastAccessDate;
    
    public User(@JsonProperty("userId") String userId, @JsonProperty("userLogin") String useLogin, @JsonProperty("userPassword") String userPassword, @JsonProperty("userFirstName") String userFirstName, @JsonProperty("userLastName") String userLastName,
    @JsonProperty("userCreationDate") String userCreationDate, @JsonProperty("userEmail") String userEmail, @JsonProperty("userLastAccessDate") String userLastAccessDate) {
        this.userId = userId;
        this.useLogin = useLogin;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userCreationDate = userCreationDate;
        this.userLastAccessDate = userLastAccessDate;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUseLogin() {
        return useLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserCreationDate() {
        return userCreationDate;
    }

    public String getUserLastAccessDate() {
        return userLastAccessDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserLastAccessDate(@JsonProperty("userLastAccessDate") String userLastAccessDate) {
        this.userLastAccessDate = userLastAccessDate;
    }
    
}
