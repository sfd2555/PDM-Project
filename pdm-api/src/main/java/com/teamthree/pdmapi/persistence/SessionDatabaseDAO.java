package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamthree.pdmapi.model.Session;

/**
 * A session Data Access Object that gets it's data through an SQL database
 * 
 * @author Caiden Williams
 */

public class SessionDatabaseDAO  implements SessionDAO{

    private final ConnectionHandler ch;

    /**
    * Creates a new Book Data Access Object that connects to a SQL database
    * 
    * @param ch connection handler to database
    */
    public SessionDatabaseDAO(ConnectionHandler ch) {
        this.ch = ch;
    }

    /**
    * {@inheritDoc}
    */
    public List<Session> getSessions(String accountId) {
        String query = "SELECT s.account_id, b.book_id, b.book_title, s.session_start, s.session_end, s.session_progress FROM Session s INNER JOIN  Book as b ON s.book_id = b.book_id WHERE s.account_id ='" + accountId + "';";
        List<Session> sessions = new ArrayList<>();
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String bookId = rs.getString("book_id");
                    String bookTitle = rs.getString("book_title");
                    java.sql.Timestamp sessionStart = rs.getTimestamp("session_start");
                    java.sql.Timestamp sessionEnd = rs.getTimestamp("session_end");
                    int progress = rs.getInt("session_progress");
                    Session session = new Session(accountId, bookId, bookTitle, sessionStart, sessionEnd, progress);
                    sessions.add(session);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ch.closeConnection();
        }
        return sessions;
    }

    /**
    * {@inheritDoc}
    */
    public boolean addSession(String accountId, String bookId, java.sql.Timestamp startTime, java.sql.Timestamp endTime, int progress) {
        String query = "INSERT INTO session VALUES('" + accountId + "', '" + bookId + "', '" + startTime + "', '" + endTime + "', '" + progress + "');";
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
}
