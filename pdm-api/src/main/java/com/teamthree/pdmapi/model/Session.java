package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a reading session
 *
 * @author Christopher Liu
 */
public class Session {
    @JsonProperty("accountId") private final String accountId;
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("sessionStartedAt") private final java.sql.Timestamp sessionStartedAt;
    @JsonProperty("sessionEndedAt") private java.sql.Timestamp sessionEndedAt;
    @JsonProperty("sessionProgress") private int sessionProgress;

    /**
     * Creates a reading session and populates it with information about the session
     * @param accountId         The ID of the account reading the book
     * @param bookId            The ID of the book being read
     * @param sessionStartedAt  The UNIX timestamp that the session began at
     * @param sessionEndedAt    The UNIX timestamp that the session ended at (if the session is not ongoing)
     * @param sessionProgress   The progress made in this session (number of pages read)
     */
    public Session(@JsonProperty("accountId") String accountId,
                   @JsonProperty("bookId") String bookId,
                   @JsonProperty("sessionStartedAt") java.sql.Timestamp sessionStartedAt,
                   @JsonProperty("sessionEndedAt") java.sql.Timestamp sessionEndedAt,
                   @JsonProperty("sessionProgress") int sessionProgress) {
        this.accountId = accountId;
        this.bookId = bookId;
        this.sessionStartedAt = sessionStartedAt;
        this.sessionEndedAt = sessionEndedAt;
        this.sessionProgress = sessionProgress;
    }

    /**
     * Gets the ID of the account reading the book
     * @return The ID of the account reading the book
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Gets the ID of the book being read
     * @return The ID of the book being read
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Gets the time the reading session started at
     * @return The time the reading session started at
     */
    public java.sql.Timestamp getSessionStartedAt() {
        return sessionStartedAt;
    }

    /**
     * Gets the time the reading session ended at
     * @return The time the reading session ended at
     */
    public java.sql.Timestamp getSessionEndedAt() {
        return sessionEndedAt;
    }

    /**
     * Gets the progress made in this reading session
     * @return The amount of progress made in this reading session
     */
    public int getSessionProgress() {
        return sessionProgress;
    }

    /**
     * Sets the time that the reading session ended at
     * @param sessionEndedAt The time the reading session ended at
     */
    public void setSessionEndedAt(java.sql.Timestamp sessionEndedAt) {
        this.sessionEndedAt = sessionEndedAt;
    }

    /**
     * Sets the progress made in this reading session
     * @param sessionProgress The progress made in this reading session
     */
    public void setSessionProgress(int sessionProgress) {
        this.sessionProgress = sessionProgress;
    }
}
