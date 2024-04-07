package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Account;
import com.teamthree.pdmapi.model.Book;
/**
 * An account Data Access Object that gets it's data through an SQL database
 * 
 * @author Sean Droll
 */
public class AccountDatabaseDAO implements AccountDAO {

    private final ConnectionHandler ch;

    /**
     * Creates a new Account Data Access Object that connects to a SQL database
     * 
     * @param ch connection handler to database
     */
    public AccountDatabaseDAO(ConnectionHandler ch) {
        this.ch = ch;
    }


    /**
     * Creates a new, unique, primary key for an object in the database
     * @param charNum how many characters to make the key
     * @return a new primary key
     */
    private String getNewPrimaryKey(int charNum) {
        // Create a new query
        String query  = "SELECT account_id FROM account ORDER BY account_id DESC LIMIT 1;";
        int result = 0;
        try{
            // Execute Query
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Make sure something is returned, if not we can be assured nothing is in the database and return 0
            if(!rs.next()) return "000000";
            // Convert the results to an integer and increment it by 1
            result = Integer.parseInt(rs.getString("account_id"));
            result += 1;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ch.closeConnection();
        }
        // Add leading zeros to string
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        if(sb.length() >= charNum) return sb.toString();
        while(sb.length() < charNum) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    /**
     * Get account from database
     */
    @Override
    public Account getAccount(String username, String password) {
        String query = "SELECT * FROM Account WHERE account_login='" + username + "' AND account_password='" + password + "';";
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
        } finally {
            ch.closeConnection();
        }
        return account;
    }

    /**
     * Get account from database
     */
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
        } finally {
            ch.closeConnection();
        }
        return account;
    }

    /**
     * Get list of friends from database
     */
    @Override
    public Account[] getFriends(String accountId) {
        String query = "(SELECT * FROM Friends INNER JOIN Account ON Friends.account2_id = Account.account_id WHERE Friends.account1_id='" + accountId + "') UNION (SELECT * FROM Friends INNER JOIN Account ON Friends.account1_id = Account.account_id WHERE Friends.account2_id='" + accountId + "');";
        List<Account> friends = new ArrayList<>();
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String friendId = rs.getString("account_id");
                    String accountLogin = rs.getString("account_login");
                    String accountPassword = rs.getString("account_password");
                    String firstName = rs.getString("account_first_name");
                    String lastName = rs.getString("account_last_name");
                    Date accountCreationDate = rs.getDate("account_creation_date");
                    Date accountLastAccessDate = rs.getDate("account_last_access_date");
                    String accountEmail = rs.getString("account_email");
                    Account account = new Account(friendId, accountLogin, accountPassword, firstName, lastName, accountCreationDate, accountEmail, accountLastAccessDate);
                    friends.add(account);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ch.closeConnection();
        }
        return friends.toArray(new Account[0]);
    }

    /**
     * add a friend pair to database
     */
    @Override
    public boolean addFriend(String accountId, String friendEmail) {
        Account account = getAccountEmail(friendEmail);
        if(account == null) return false;
        String query = "INSERT INTO Friends VALUES('" + accountId + "', '" + account.getAccountId() + "');";
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ch.closeConnection();
        }

        return true;
    }

    /**
     * add a new account to databse
     */
    @Override
    public boolean createAccount(String username, String password, String firstName, String lastName, String email) {
        String query = "INSERT INTO Account VALUES('" + getNewPrimaryKey(6) + "', '" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', current_date, current_date, '"+ email + "');";
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ch.closeConnection();
        }
        return true;
    }

    private Account getAccountEmail(String email) {
        String query = "SELECT * FROM account WHERE account_email='" + email + "';";
        Statement stmt;
        Account account = null;
        try {
            stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            String accountId = rs.getString("account_id");
            String accountLogin = rs.getString("account_login");
            String accountPassword = rs.getString("account_password");
            String firstName = rs.getString("account_first_name");
            String lastName = rs.getString("account_last_name");
            Date accountCreationDate = rs.getDate("account_creation_date");
            Date accountLastAccessDate = rs.getDate("account_last_access_date");
            account = new Account(accountId, accountLogin, accountPassword, firstName, lastName, accountCreationDate, email, accountLastAccessDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ch.closeConnection();
        }
        return account;
    }


    @Override
    public boolean removeFriend(String accountId, String friendId) {
        String query1 = "SELECT * FROM friends WHERE account1_id='" + accountId + "'AND account2_id='" + friendId + "';";
        String query = "";
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            if(stmt.executeQuery(query1).next())
                query = "DELETE FROM friends WHERE account1_id='" + accountId + "' AND account2_id='" + friendId+ "';";
            else 
                query = "DELETE FROM friends WHERE account1_id='" + friendId + "' AND account2_id='" + accountId+ "';";
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ch.closeConnection();
        }
        return true;
    }
    
    @Override
    public List<Book> getForYou(String accountId) {
        String query = "SELECT g.genre_name, a.audience_name, f.format_type, contr.contributor_name FROM collection as c " + 
        "inner join contains as con ON c.collection_id = con.collection_id inner join book as b ON con.book_id = b.book_id " + 
        "inner join book_genre as bg ON b.book_id = bg.genre_id inner join genre as g ON bg.genre_id = g.genre_id " + 
        "inner join book_audience as ba ON ba.book_id = b.book_id inner join audience as a ON a.audience_id = ba.audience_id " + 
        "inner join format as f ON f.format_id = con.format_id inner join contributes as cont ON b.book_id = cont.book_id " + 
        "inner join contributor as contr ON cont.contributor_id = contr.contributor_id WHERE c.account_id = '" + accountId + "';";
        List<String> genres = new ArrayList<>();
        List<String> audience = new ArrayList<>();
        List<String> format = new ArrayList<>();
        List<String> contributor = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        try {
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null)
                while(rs.next()) {
                    if (!genres.contains("'" + rs.getString("genre_name") + "'")) genres.add("'" + rs.getString("genre_name") + "'");
                    if (!audience.contains("'" + rs.getString("audience_name") + "'")) audience.add("'" + rs.getString("audience_name") + "'");
                    if (!format.contains("'" + rs.getString("format_type") + "'")) format.add("'" + rs.getString("format_type") + "'");
                    if (!contributor.contains("'" + rs.getString("contributor_name") + "'")) contributor.add("'" + rs.getString("contributor_name") + "'");
                }
            query = "SELECT b.book_id, b.book_title FROM genre as g inner join book_genre as bg ON bg.genre_id = g.genre_id inner join book as b ON bg.book_id = b.book_id WHERE g.genre_name IN " + genres.toString().replace("[", "(").replace("]", ")") + 
                    " UNION SELECT b.book_id, b.book_title FROM audience as a inner join book_audience as ba ON a.audience_id = ba.audience_id inner join book as b ON ba.book_id = b.book_id WHERE a.audience_name IN " + audience.toString().replace("[", "(").replace("]", ")") +
                    " UNION SELECT b.book_id, b.book_title FROM format as f inner join book_format as bf ON f.format_id = bf.format_id inner join book as b ON bf.book_id = b.book_id WHERE f.format_type IN " + format.toString().replace("[", "(").replace("]", ")") +
                    " UNION SELECT b.book_id, b.book_title FROM contributor as con inner join contributes as cont ON con.contributor_id = cont.contributor_id inner join book as b ON cont.book_id = b.book_id WHERE con.contributor_name IN " + contributor.toString().replace("[", "(").replace("]", ")") + ";";
            rs = stmt.executeQuery(query);
            if(rs != null)
                while(rs.next()) {
                    String id = rs.getString("book_id");
                    String title = rs.getString("book_title");
                    Book book = new Book(id, title);
                    if(!books.contains(book)) books.add(book);
                }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ch.closeConnection();
        }
        return books;
    }
}
