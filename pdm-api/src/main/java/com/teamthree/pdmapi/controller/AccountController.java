package com.teamthree.pdmapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamthree.pdmapi.model.Account;
import com.teamthree.pdmapi.persistence.AccountDAO;
import com.teamthree.pdmapi.persistence.AccountDatabaseDAO;
import com.teamthree.pdmapi.persistence.ConnectionHandler;

import java.util.logging.Logger;

@RestController
@RequestMapping("account")
public class AccountController {

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());
    private AccountDAO accountDAO;
    

    /**
     * Handles REST API requests for account resorces
     * @param ch a connection handler to the database
     */
    private AccountController(ConnectionHandler ch) {
        accountDAO = new AccountDatabaseDAO(ch);
    }


    /**
     * Creates a new account on the server
     * @param user username of the new account
     * @param pass password of the new account
     * @param first first name of the new account
     * @param last last name of the new account
     * @param email email of the new account
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Boolean> createAccount(@RequestParam("user") String user, @RequestParam("pass") String pass, @RequestParam("first") String first, @RequestParam("last") String last, @RequestParam("email") String email) {
        LOG.info("POST /account/register?user = " + user + "&pass=" + pass + "&first=" + first + "&last=" + last + "&email=" + email);
        boolean result = accountDAO.createAccount(user, pass, first, last, email);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
        
    }

    /**
     * Gets an account assigned to a username and password
     * @param user username of the account
     * @param pass password of the account
     * @return ResponseEntity containing the account upon success
     *         ResponseEntity with code NOT_FOUND if account does not exist
     */
    @GetMapping("/login")
    public ResponseEntity<Account> login(@RequestParam("user") String user, @RequestParam("pass") String pass) {
        LOG.info("GET /account/login?user = " + user + "&pass=" + pass);
        Account acct = accountDAO.getAccount(user, pass);
        if(acct != null) {
            return new ResponseEntity<>(acct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Gets an account assigned to a account_id
     * @param id account id of the account 
     * @return ResponseEntity containing the account upon success
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        LOG.info("GET /account/" + id);
        Account acct = accountDAO.getAccount(id);
        if(acct != null) {
            return new ResponseEntity<>(acct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a friend relation between two accounts
     * @param accountOneId id of an account
     * @param accountTwoId id of another account
     * @return ResponseEntity containing the account upon success
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @PostMapping("/friend") 
    public ResponseEntity<Boolean> addFriend(@RequestParam("account1_id") String accountOneId, @RequestParam("account2_id") String accountTwoId) {
        LOG.info("POST /account/friend?account1_id=" + accountOneId + "&account2_id" + accountTwoId);
        Boolean result = accountDAO.addFriend(accountOneId, accountTwoId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Gets a list of accounts the account is friends with
     * @param id account id of the user
     * @return ResponseEntity containing a list of accounts upon success
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @GetMapping("/friend/{id}")
    public ResponseEntity<Account[]> getFriends(@PathVariable String id) {
        LOG.info("GET /account/" + id);
        Account[] friends = accountDAO.getFriends(id);
        if(friends == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }


}
