package com.teamthree.pdmapi.persistence;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamthree.pdmapi.model.Session;

public class SessionDatabaseDAO  implements SessionDAO{

    private final ConnectionHandler connHandler;

    public SessionDatabaseDAO(ConnectionHandler connHandler) {
        this.connHandler = connHandler;
    }
    
    public Session getSession(String accountId, String bookId, Timestamp startTime) {
        return null;
    }

    public List<Session> getSessions(String accountId, String bookId) {
        String query = "SELECT * FROM session WHERE account_id = '" + accountId + "' AND book_id = '" + bookId + "';";
        List<Session> sessions = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Session getSessions(String accountId) {
        return null;
    }

    public Session startSession(String accountId, String bookId) {
        return null;
    }

    public Session endSession(String accountId, String bookId) {
        return null;
    }

    public boolean addSession(String accountId, String bookId, Timestamp startTime, Timestamp endTime, int progress) {
        return false;
    } 

}
