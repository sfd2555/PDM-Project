package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Refined Book on the website and all it's metadata
 *
 * @author Christopher
 */
public class RefinedBook extends Book {
    @JsonProperty("formats") private final Format[] formats;
    @JsonProperty("contributors") private final Contributor[] contributors;
    @JsonProperty("genres") private final Genre[] genres;
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
    public RefinedBook(@JsonProperty("bookId") String bookId,
                       @JsonProperty("bookTitle") String bookTitle,
                       @JsonProperty("formats") Format[] formats,
                       @JsonProperty("contributors") Contributor[] contributors,
                       @JsonProperty("genres") Genre[] genres){
        super(bookId, bookTitle);
        this.formats = formats;
        this.contributors = contributors;
        this.genres = genres;
    }

}
