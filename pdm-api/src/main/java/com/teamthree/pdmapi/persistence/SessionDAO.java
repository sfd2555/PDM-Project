package com.teamthree.pdmapi.persistence;

import java.security.Timestamp;
import java.util.List;

import com.teamthree.pdmapi.model.Session;

public interface SessionDAO {
    
    public Session getSession(String accountId, String bookId, Timestamp startTime);

    public List<Session> getSessions(String accountId, String bookId);

    public List<Session> getSessions(String accountId);

    public Session startSession(String accountId, String bookId);

    public Session endSession(String accountId, String bookId);

    public boolean addSession(String accountId, String bookId, Timestamp startTime, Timestamp endTime, int progress);    

}
