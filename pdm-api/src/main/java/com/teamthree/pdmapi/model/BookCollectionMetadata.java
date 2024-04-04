package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookCollectionMetadata {
    @JsonProperty("collectionId") private final String collectionId;
    @JsonProperty("bookId") private final String bookId;
    @JsonProperty("bookTitle") private final String bookTitle;
    @JsonProperty("formatType") private final String formatType;
    @JsonProperty("bookLength") private final int bookLength;
    @JsonProperty("genres") private final Genre[] genres;
    @JsonProperty("contributors") private final Contributor[] contributors;

    /**
    * TODO
    * 
    * {@literal @}JsonProperty is used in serialization and deserialization
    * of the JSON object to the Java object in mapping the fields.  If a field
    * is not provided in the JSON object, the Java field gets the default Java
    * value, i.e. 0 for int
    */
    public BookCollectionMetadata(@JsonProperty("collectionId") String collectionId, @JsonProperty("bookId") String bookId, @JsonProperty("formatType") String formatType, @JsonProperty("bookTitle") String bookTitle, @JsonProperty("bookLength") int bookLength, @JsonProperty("genres") Genre[] genres, @JsonProperty("contributors") Contributor[] contributors){
        this.collectionId = collectionId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.formatType = formatType;
        this.bookLength = bookLength;
        this.genres = genres;
        this.contributors = contributors;
    }

    /**
     * Retrieves the id of the book
     * @return the id of the book
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * TODO
     * @return
     */
    public String getCollectionId() {
        return collectionId;
    }

    /**
     * TODO
     * @return
     */
    public String getFormatType() {
        return formatType;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    /**
     * TODO
     * @return
     */
    public int getBookLength() {
        return bookLength;
    }

    public Contributor[] getContributors() {
        return contributors;
    }
}
