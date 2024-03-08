package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the Genre of a book
 *
 * @author Christopher Liu
 */
public class Genre {
    @JsonProperty("genreId") private final String genreId;
    @JsonProperty("genreName") private final String genreName;

    /**
     * Create a genre with a given genreId and genreName
     * @param genreId The 6-digit ID of the genre
     * @param genreName The name of the genre
     */
    public Genre(@JsonProperty("genreId") String genreId, @JsonProperty("genreName") String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    /**
     * Retrieves the id of the genre
     * @return The id of the genre
     */
    public String getGenreId() {
        return genreId;
    }

    /**
     * Retrieves the name of the genre
     * @return the name of the genre
     */
    public String getGenreName() {
        return genreName;
    }

}

