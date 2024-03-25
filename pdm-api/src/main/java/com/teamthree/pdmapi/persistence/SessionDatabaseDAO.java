package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamthree.pdmapi.model.Session;

public class SessionDatabaseDAO  implements SessionDAO{

    private final ConnectionHandler ch;

    public SessionDatabaseDAO(ConnectionHandler ch) {
        this.ch = ch;
    }
    
    public Session getSession(String accountId, String bookId, java.sql.Timestamp startTime) {
        String query = "SELECT * FROM session WHERE account_id = '" + accountId + "' AND book_id = '" + bookId + "' AND session_start = '" + startTime + "';";
        Session session = null;
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) {
                java.sql.Timestamp sessionEnd = rs.getTimestamp("session_end");
                int progress = rs.getInt("session_progress");
                session = new Session(accountId, bookId, startTime, sessionEnd, progress);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ch.closeConnection();
        }
        return session;
    }

    public List<Session> getSessions(String accountId, String bookId) {
        String query = "SELECT * FROM session WHERE account_id = '" + accountId + "' AND book_id = '" + bookId + "';";
        List<Session> sessions = new ArrayList<>();
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) {
                while(rs.next()) {
                    java.sql.Timestamp sessionStart = rs.getTimestamp("session_start");
                    java.sql.Timestamp sessionEnd = rs.getTimestamp("session_end");
                    int progress = rs.getInt("session_progress");
                    Session session = new Session(accountId, bookId, sessionStart, sessionEnd, progress);
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

    public List<Session> getSessions(String accountId) {
        String query = "SELECT * FROM session WHERE account_id = '" + accountId + "';";
        List<Session> sessions = new ArrayList<>();
        try{
            Statement stmt = ch.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) {
                while(rs.next()) {
                    String bookId = rs.getString("book_id");
                    java.sql.Timestamp sessionStart = rs.getTimestamp("session_start");
                    java.sql.Timestamp sessionEnd = rs.getTimestamp("session_end");
                    int progress = rs.getInt("session_progress");
                    Session session = new Session(accountId, bookId, sessionStart, sessionEnd, progress);
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
