package com.teamthree.pdmapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an Account on the website and all their metadata
 * 
 * @author Sean Droll
 */
public class Account {
    @JsonProperty("accountId") private final String accountId;
    @JsonProperty("accountLogin") private final String accountLogin;
    @JsonProperty("accountPassword") private final String accountPassword;
    @JsonProperty("accountFirstName") private final String accountFirstName;
    @JsonProperty("accountLastName") private final String accountLastName;
    @JsonProperty("accountCreationDate") private final Date accountCreationDate;
    @JsonProperty("accountEmail") private final String accountEmail;
    @JsonProperty("accountLastAccessDate") private Date accountLastAccessDate;
    /**
    * Create an account with the given email and pass
    * @param accountId 6 digit ID of the account
    * @param accountLogin username of the account
    * @param accountPassword password of the account
    * @param accountFirstName the first name of the account
    * @param accountLastName the last name of the account
    * @param accountCreationDate the day the account was created
    * @param accountEmail the email of the account
    * @param accountLastAccessDate the last date the account logged in
    * 
    * {@literal @}JsonProperty is used in serialization and deserialization
    * of the JSON object to the Java object in mapping the fields.  If a field
    * is not provided in the JSON object, the Java field gets the default Java
    * value, i.e. 0 for int
    */
    public Account(@JsonProperty("accountId") String accountId,
                   @JsonProperty("accountLogin") String accountLogin,
                   @JsonProperty("accountPassword") String accountPassword,
                   @JsonProperty("accountFirstName") String accountFirstName,
                   @JsonProperty("accountLastName") String accountLastName,
                   @JsonProperty("accountCreationDate") Date accountCreationDate,
                   @JsonProperty("accountEmail") String accountEmail,
                   @JsonProperty("accountLastAccessDate") Date accountLastAccessDate) {
        this.accountId = accountId;
        this.accountLogin = accountLogin;
        this.accountPassword = accountPassword;
        this.accountFirstName = accountFirstName;
        this.accountLastName = accountLastName;
        this.accountCreationDate = accountCreationDate;
        this.accountLastAccessDate = accountLastAccessDate;
        this.accountEmail = accountEmail;
    }

    /**
     * Retrieves the account id of the account
     * @return the account id of the account
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Retrieves the username of the account
     * @return the username of the account
     */
    public String getAccountLogin() {
        return accountLogin;
    }

    /**
     * Retrieves the password of the account
     * @return the password of the account
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * Retreives the first name of the account
     * @return the first name of the account
     */
    public String getAccountFirstName() {
        return accountFirstName;
    }

    /**
     * Retreives the last name of the account
     * @return the last name of the account
     */
    public String getAccountLastName() {
        return accountLastName;
    }

    /**
     * Retreives the full name of the account
     * @return the full name of the account
     */
    public String getAccountFullName() {
        return accountFirstName + " " + accountLastName;
    }

    /**
     * Retrieves the creation date of the account
     * @return the creation date of the account
     */
    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    /**
     * Retrieves the date the account last logged in
     * @return the date the account last logged in
     */
    public Date getAccountLastAccessDate() {
        return accountLastAccessDate;
    }

    /**
     * Retrieves the email of the account
     * @return the email of the account
     */
    public String getAccountEmail() {
        return accountEmail;
    }

    /**
     * Updates the last access date of the account
     * @param accountLastAccessDate new last access date of the account
     */
    public void setAccountLastAccessDate(@JsonProperty("accountLastAccessDate") Date accountLastAccessDate) {
        this.accountLastAccessDate = accountLastAccessDate;
    }
    
}
