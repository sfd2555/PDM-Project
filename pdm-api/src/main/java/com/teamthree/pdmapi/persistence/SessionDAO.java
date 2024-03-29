package com.teamthree.pdmapi.persistence;

import java.util.List;

import com.teamthree.pdmapi.model.Session;

/**
 * Allows access to a sessions data
 * 
 * @author Caiden Williams
 */
public interface SessionDAO {

    /**
    * Retrieves a list of sessions via an accountId
    * @param accountId the Id of the account
    * @return a list of sessions that matches the parameters (if there is one)
    */
    public List<Session> getSessions(String accountId);

    /**
    * Creates a session via the given parameters
    * @param accountId the Id of the account
    * @param bookId the Id of the book
    * @param startTime the start time of the session
    * @param endTime the end time of the session
    * @param progress the progress of the session
    * @return whether or not the creation was successful
    */
    public boolean addSession(String accountId, String bookId, java.sql.Timestamp startTime, java.sql.Timestamp endTime, int progress);    

}
