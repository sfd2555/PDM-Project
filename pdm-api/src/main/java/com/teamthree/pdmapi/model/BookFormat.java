package com.teamthree.pdmapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a BookFormat on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class BookFormat {  
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("format") private final Format format;
    @JsonProperty("length_pages") private final int length_pages;
    @JsonProperty("release_date") private final Date release_date;


    /**
    * Create a 'bookFormat' with the given bookId, format entity, number of pages, and the release date
    * @param bookId 6 digit ID of the book
    * @param format the format entity
    * @param length_pages the number of pages in the book
    * @param release_date the release date of the format
    * 
    * {@literal @}JsonProperty is used in serialization and deserialization
    * of the JSON object to the Java object in mapping the fields.  If a field
    * is not provided in the JSON object, the Java field gets the default Java
    * value, i.e. 0 for int
    */
    public BookFormat(@JsonProperty("bookId") String bookId, @JsonProperty("format") Format format, @JsonProperty("length_pages") int length_pages, @JsonProperty("release_date") Date release_date){
        this.bookId = bookId;
        this.format = format;
        this.length_pages = length_pages;
        this.release_date = release_date;
    }

    /**
     * Retrieves the id of the book
     * @return the id of the book
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Retrieves the format entity
     * @return the format entity
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Retrieves the length of the book
     * @return the length of the book
     */
    public int getLength() {
        return length_pages;
    }

    /**
     * Retrieves the release date of the format
     * @return the release date of the format
     */
    public Date getReleaseDate() {
        return release_date;
    }
}