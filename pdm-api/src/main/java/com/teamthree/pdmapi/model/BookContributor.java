package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Book on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class BookContributor {  
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("contributor") private final Contributor contributor;
    @JsonProperty("type") private final String type;


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
    public BookContributor(@JsonProperty("bookId") String bookId, @JsonProperty("contributor") Contributor contributor, @JsonProperty("type") String type){
        this.bookId = bookId;
        this.contributor = contributor;
        this.type = type;
    }

    /**
     * Retrieves the id of the book
     * @return the id of the book
     */
    public String getBookId() {
        return bookId;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public String getType() {
        return type;
    }
}
