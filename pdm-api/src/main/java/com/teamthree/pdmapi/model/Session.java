package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a reading session
 *
 * @author Christopher Liu
 */
public class Session {
    @JsonProperty("userId") private final String userId;
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("sessionStartedAt") private final int sessionStartedAt;
    @JsonProperty("sessionEndedAt") private int sessionEndedAt;
    @JsonProperty("sessionProgress") private int sessionProgress;

    /**
     * Creates a reading session and populates it with information about the session
     * @param userId            The ID of the user reading the book
     * @param bookId            The ID of the book being read
     * @param sessionStartedAt  The UNIX timestamp that the session began at
     * @param sessionEndedAt    The UNIX timestamp that the session ended at (if the session is not ongoing)
     * @param sessionProgress   The progress made in this session (number of pages read)
     */
    public Session(@JsonProperty("userId") String userId,
                   @JsonProperty("bookId") String bookId,
                   @JsonProperty("sessionStartedAt") int sessionStartedAt,
                   @JsonProperty("sessionEndedAt") int sessionEndedAt,
                   @JsonProperty("sessionProgress") int sessionProgress) {
        this.userId = userId;
        this.bookId = bookId;
        this.sessionStartedAt = sessionStartedAt;
        this.sessionEndedAt = sessionEndedAt;
        this.sessionProgress = sessionProgress;
    }

    /**
     * Gets the ID of the user reading the book
     * @return The ID of the user reading the book
     */
    public String getUserId() {
        return userId;
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
    public int getSessionStartedAt() {
        return sessionStartedAt;
    }

    /**
     * Gets the time the reading session ended at
     * @return The time the reading session ended at
     */
    public int getSessionEndedAt() {
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
    public void setSessionEndedAt(int sessionEndedAt) {
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
