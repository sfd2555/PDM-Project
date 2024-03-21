package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Account;

public interface AccountDAO {

    Account getAccount(String username, String password);

    Account getAccount(String accountId);

    Account[] getFriends(String accountID);

    boolean createUser(String username, String password, String firstName, String lastName, String email);
    
}
