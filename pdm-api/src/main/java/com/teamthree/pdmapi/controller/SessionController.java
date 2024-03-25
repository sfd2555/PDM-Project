package com.teamthree.pdmapi.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamthree.pdmapi.model.Session;
import com.teamthree.pdmapi.persistence.ConnectionHandler;
import com.teamthree.pdmapi.persistence.SessionDAO;
import com.teamthree.pdmapi.persistence.SessionDatabaseDAO;

/**
 * A controller for the BookDatabaseDAO
 * 
 * @author Caiden Williams
 */
@RestController
@RequestMapping("session")
public class SessionController {

        private static final Logger LOG = Logger.getLogger(SessionController.class.getName());
        private SessionDAO sessionDAO;

        /**
        * Handles REST API requests for session resorces
        * @param ch a connection handler to the database
        */
        private SessionController(ConnectionHandler ch) {
            sessionDAO = new SessionDatabaseDAO(ch);
        }

        /**
         * Gets the session from the given account id, book id, and start time
         * @param accountId the account id
         * @param bookId the book it 
         * @param startTime the start time of the session (YYYY-MM-DD_HH:MM:SS.MMMMMM)
         * @return the session matching the parameters (if there is one)
         */
        @GetMapping("")
        public ResponseEntity<Session> getSession(@RequestParam("accountId") String accountId, @RequestParam("bookId") String bookId, @RequestParam("startTime") String startTime) {
            LOG.info("GET session?accountId=" + accountId + "&bookId" + bookId + "&startTime='" + startTime + "'");
            Session result = sessionDAO.getSession(accountId, bookId, Timestamp.valueOf(startTime.replace("_", " ")));
            return new ResponseEntity<Session>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
        }

        /**
         * Gets a list of sessions from the given account id and book id
         * @param accountId the account id
         * @param bookId the book id
         * @return the list of sessions
         */
        @GetMapping("/noTime")
        public ResponseEntity<List<Session>> getSessions(@RequestParam("accountId") String accountId, @RequestParam("bookId") String bookId) {
            LOG.info("GET session/noTime?accountId=" + accountId + "&bookId" + bookId);
            List<Session> result = sessionDAO.getSessions(accountId, bookId);
            return new ResponseEntity<List<Session>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
        }

        /**
         * Gets a list of sessions from the given account id
         * @param accountId the account id
         * @return a list of sessions
         */
        @GetMapping("/{accountId}")
        public ResponseEntity<List<Session>> getSessions(@PathVariable("accountId") String accountId) {
            LOG.info("GET session/" + accountId);
            List<Session> result = sessionDAO.getSessions(accountId);
            return new ResponseEntity<List<Session>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
        }

        /**
         * Adds a session from the given parameters
         * @param accountId the id of the account
         * @param bookId the id of the book
         * @param startTime the start time of the session (YYYY-MM-DD_HH:MM:SS.MMMMMM)
         * @param endTime the end time of the session (YYYY-MM-DD_HH:MM:SS.MMMMMM)
         * @param progress the progress from the session (in pages)
         * @return whether or not the create was successful
         */
        @PostMapping("")
        public ResponseEntity<Boolean> addSession(@RequestParam("accountId") String accountId, @RequestParam("bookId") String bookId, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, @RequestParam("progress") int progress) {
            LOG.info("POST session?accountId=" + accountId + "&bookId" + bookId + "&startTime=" + startTime + "&endTime=" + endTime + "&progress" + progress);
            boolean result = sessionDAO.addSession(accountId, bookId, Timestamp.valueOf(startTime.replace("_", " ")), Timestamp.valueOf(endTime.replace("_", " ")), progress);
            return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
        }
}
