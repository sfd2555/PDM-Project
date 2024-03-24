package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Account;

public class AccountDatabaseDAO implements AccountDAO {

    private final ConnectionHandler ch;

    public AccountDatabaseDAO(ConnectionHandler ch) {
        this.ch = ch;
    }


    private String getNewPrimaryKey(int charNum) {
        String query  = "SELECT account_id FROM account ORDER BY account_id DESC LIMIT 1;";
        int result = 0;
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return "000000";
            result = Integer.parseInt(rs.getString("account_id"));
            result += 1;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        if(sb.length() >= charNum) return sb.toString();
        while(sb.length() < charNum) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    @Override
    public Account getAccount(String username, String password) {
        String query = "SELECT * FROM Account WHERE account_login='" + username + "'' AND account_password='" + password + "';";
        String update = "UPDATE account SET account_last_access_date = current_date WHERE account_id='";
        Statement stmt;
        Account account = null;
        try {
            stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            String accountId = rs.getString("account_id");
            String firstName = rs.getString("account_first_name");
            String lastName = rs.getString("account_last_name");
            Date accountCreationDate = rs.getDate("account_creation_date");
            Date accountLastAccessDate = rs.getDate("account_last_access_date");
            String accountEmail = rs.getString("account_email");
            account = new Account(accountId, username, password, firstName, lastName, accountCreationDate, accountEmail, accountLastAccessDate);
            stmt.executeUpdate(update + accountId + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccount(String accountId) {
        String query = "SELECT * FROM Account WHERE account_id='" + accountId + "';";
        Statement stmt;
        Account account = null;
        try {
            stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            String accountLogin = rs.getString("account_login");
            String accountPassword = rs.getString("account_password");
            String firstName = rs.getString("account_first_name");
            String lastName = rs.getString("account_last_name");
            Date accountCreationDate = rs.getDate("account_creation_date");
            Date accountLastAccessDate = rs.getDate("account_last_access_date");
            String accountEmail = rs.getString("account_email");
            account = new Account(accountId, accountLogin, accountPassword, firstName, lastName, accountCreationDate, accountEmail, accountLastAccessDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account[] getFriends(String accountId) {
        String query = "SELECT * FROM Friends INNER JOIN Account ON Friends.account2_id = Account.account_id WHERE Friends.account1_id='" + accountId + "';";
        List<Account> friends = new ArrayList<>();
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String friendId = rs.getString("Account.account_id");
                    String accountLogin = rs.getString("Account.account_login");
                    String accountPassword = rs.getString("Account.account_password");
                    String firstName = rs.getString("Account.account_first_name");
                    String lastName = rs.getString("Account.account_last_name");
                    Date accountCreationDate = rs.getDate("Account.account_creation_date");
                    Date accountLastAccessDate = rs.getDate("Account.account_last_access_date");
                    String accountEmail = rs.getString("Account.account_email");
                    Account account = new Account(friendId, accountLogin, accountPassword, firstName, lastName, accountCreationDate, accountEmail, accountLastAccessDate);
                    friends.add(account);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (Account[]) friends.toArray();
    }

    @Override
    public boolean addFriend(String accountId, String friendId) {
        String query = "INSERT INTO Friend VALUES('" + accountId + "', '" + friendId + "');";
        String queryTwo = "INSERT INTO Friend VALUES('" + friendId + "', '" + accountId + "');";
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate(queryTwo);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean createUser(String username, String password, String firstName, String lastName, String email) {
        String query = "INSERT INTO Account VALUES('" + getNewPrimaryKey(6) + "', '" + username + "', '" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', current_date, current_date);";
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
