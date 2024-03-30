package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Format on the website and all their metadata
 *
 * @author Beining Zhou
 */
public class Format {
    @JsonProperty("formatId") private final String formatId;
    @JsonProperty("formatType") private final String formatType;
    @JsonProperty("bookLength") private final int bookLength;
    @JsonProperty("releaseDate") private final String releaseDate;

    /**
     * Create a format with the given formatId and formatType
     * @param formatId 6-digit ID of the format
     * @param formatType type of the format
     */
    public Format(@JsonProperty("formatId") String formatId, @JsonProperty("formatType") String formatType,
                  @JsonProperty("bookLength") int bookLength, @JsonProperty("releaseDate") String releaseDate) {
        this.formatId = formatId;
        this.formatType = formatType;
        this.bookLength = bookLength;
        this.releaseDate = releaseDate;
    }

    /**
     * Retrieves the id of the format
     * @return the id of the format
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     * Retrieves the type of the format
     * @return the type of the format
     */
    public String getFormatType() {
        return formatType;
    }

    public int getBookLength() {
        return bookLength;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
}

