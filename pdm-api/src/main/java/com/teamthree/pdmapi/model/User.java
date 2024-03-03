package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a User on the website and all their metadata
 * 
 * @author Sean Droll
 */
public class User {
    @JsonProperty("userId") private final String userId;
    @JsonProperty("userLogin") private final String useLogin;
    @JsonProperty("userPassword") private final String userPassword;
    @JsonProperty("userFirstName") private final String userFirstName;
    @JsonProperty("userLastName") private final String userLastName;
    @JsonProperty("userCreationDate") private final String userCreationDate;
    @JsonProperty("userEmail") private final String userEmail;
    @JsonProperty("userLastAccessDate") private String userLastAccessDate;
    /**
    * Create an account with the given user and pass
    * @param userId 6 digit ID of the user
    * @param userLogin username of the user
    * @param userPassword password of the user
    * @param userFirstName the first name of the user
    * @param userLastName the last name of the user
    * @param userCreationDatae the day the user was created
    * @param userEmail the email of the user
    * @param userLastAccessDate the last date the user logged in
    * 
    * {@literal @}JsonProperty is used in serialization and deserialization
    * of the JSON object to the Java object in mapping the fields.  If a field
    * is not provided in the JSON object, the Java field gets the default Java
    * value, i.e. 0 for int
    */
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

    /**
     * Retrieves the user id of the user
     * @return the user id of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Retrieves the username of the user
     * @return the username of the user
     */
    public String getUseLogin() {
        return useLogin;
    }

    /**
     * Retrieves the password of the user
     * @return the password of the user
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Retreives the first name of the user
     * @return the first name of the user
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Retreives the last name of the user
     * @return the last name of the user
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Retreives the full name of the user
     * @return the full name of the user
     */
    public String getUserFullName() {
        return userFirstName + " " + userLastName;
    }

    /**
     * Retrieves the creation date of the account
     * @return the creation date of the account
     */
    public String getUserCreationDate() {
        return userCreationDate;
    }

    /**
     * Retrieves the date the user last logged in
     * @return the date the user last logged in
     */
    public String getUserLastAccessDate() {
        return userLastAccessDate;
    }

    /**
     * Retrieves the email of the user
     * @return the email of the user
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Updates the last access date of the user
     * @param userLastAccessDate new last access date of the user
     */
    public void setUserLastAccessDate(@JsonProperty("userLastAccessDate") String userLastAccessDate) {
        this.userLastAccessDate = userLastAccessDate;
    }
    
}
