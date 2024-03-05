package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Collection on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class Collection {
    @JsonProperty("collectionId") private final String collectionId;
    @JsonProperty("userId") private final String userId;
    @JsonProperty("collectionName") private String collectionName;

    /**
    * Create a collection with the given collectionId, userId, and collectionName
    * @param collectionId 6 digit ID of the collection
    * @param userId 6 digit ID of the user the collection belongs to
    * @param collectionName name of the collection
    */
    public Collection(@JsonProperty("collectionId") String collectionId, @JsonProperty("userId") String userId, @JsonProperty("collectionName") String collectionName) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.collectionName = collectionName;
    }

    /**
     * Retrieves the id of the collection
     * @return the id of the collection
     */
    public String getCollectionId() {
        return collectionId;
    }

    /**
     * Retrieves the id of the user who owns the collection
     * @return the id of the user who owns the collection
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Retrieves the name of the collection
     * @return the name of the collection
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Updates the name of the collection
     * @param collectionName the new name for the collection
     */
    public void setCollectionName(@JsonProperty("collectionName") String collectionName) {
        this.collectionName = collectionName;
    }
}
