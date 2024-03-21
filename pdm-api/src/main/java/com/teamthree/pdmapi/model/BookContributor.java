package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Book on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class BookContributor {  
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("bookTitle") private final String bookTitle;


    /**
    * Create a book with the given bookId and bookTitle
    * @param bookId 6 digit ID of the book
    * @param bookTitle title of the book
    * 
    * {@literal @}JsonProperty is used in serialization and deserialization
    * of the JSON object to the Java object in mapping the fields.  If a field
    * is not provided in the JSON object, the Java field gets the default Java
    * value, i.e. 0 for int
    */
    public Book(@JsonProperty("bookId") String bookId, @JsonProperty("bookTitle") String bookTitle){
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    /**
     * Retrieves the id of the book
     * @return the id of the book
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Retrieves the title of the book
     * @return the title of the book
     */
    public String getBookTitle() {
        return bookTitle;
    }
}
