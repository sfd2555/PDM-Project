package com.teamthree.pdmapi.model;

public class User {
    private final String userId;
    private final String useLogin;
    private final String userPassword;
    private final String userFirstName;
    private final String userLastName;
    private final String userCreationDate;
    private final String userEmail;
    private String userLastAccessDate;
    
    public User(String userId, String useLogin, String userPassword, String userFirstName, String userLastName,
            String userCreationDate, String userLastAccessDate, String userEmail) {
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

    public void setUserLastAccessDate(String userLastAccessDate) {
        this.userLastAccessDate = userLastAccessDate;
    }
    
}
