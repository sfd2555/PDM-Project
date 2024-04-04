package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionMetadata {

    @JsonProperty("collectionId") private final String collectionId;
    @JsonProperty("accountId") private final String accountId;
    @JsonProperty("collectionName") private String collectionName;
    @JsonProperty("collectionEntries") private int collectionEntries;
    @JsonProperty("collectionVolume") private int collectionVolume;

    /**
    * Create a collection with the given collectionId, accountId, and collectionName
    * @param collectionId 6 digit ID of the collection
    * @param accountId 6 digit ID of the account the collection belongs to
    * @param collectionName name of the collection
    */
    public CollectionMetadata(@JsonProperty("collectionId") String collectionId, @JsonProperty("accountId") String accountId, @JsonProperty("collectionName") String collectionName, @JsonProperty("collectionEntries") int collectionEntries, @JsonProperty("collectionVolume") int collectionVolume) {
        this.collectionId = collectionId;
        this.accountId = accountId;
        this.collectionName = collectionName;
        this.collectionEntries = collectionEntries;
        this.collectionVolume = collectionVolume;
    }

    /**
     * Retrieves the id of the collection
     * @return the id of the collection
     */
    public String getCollectionId() {
        return collectionId;
    }

    /**
     * Retrieves the id of the account who owns the collection
     * @return the id of the account who owns the collection
     */
    public String getAccountId() {
        return accountId;
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

    public int getCollectionEntries() {
        return collectionEntries;
    }

    public int getCollectionVolume() {
        return collectionVolume;
    }
}
