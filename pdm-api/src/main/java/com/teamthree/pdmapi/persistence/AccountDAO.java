package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Account;

/**
 * Allows access to an accounts data
 * 
 * @author Sean Droll
 */
public interface AccountDAO {

    /**
     * Allows access to an account via username and password (used for login purposes)
     * @param username username of user
     * @param password password of user
     * @return a account if the combination matches a stored pair
     */
    Account getAccount(String username, String password);

    /**
     * Allows access to an account via account_id (used for getting data from other accounts)
     * @param accountId account id of user
     * @return
     */
    Account getAccount(String accountId);

    /**
     * Gets a list of accounts that are friends with the user
     * 
     * @param accountID id of account to get friends from
     * @return accounts the given account is friends with
     */
    Account[] getFriends(String accountID);

    /**
     * Stores that both ids are friends
     *
     * @param accountId one account id
     * @param friendId other account id
     * @return true if both accounts exits
     */
    boolean addFriend(String accountId, String friendEmail);

    /**
     * Creates a new account for the website
     * 
     * @param username username of new user
     * @param password password of new user
     * @param firstName first name of new user
     * @param lastName last name of new user
     * @param email email of new user
     * @return true if the new account is created with no problems
     */
    boolean createAccount(String username, String password, String firstName, String lastName, String email);
    
}
