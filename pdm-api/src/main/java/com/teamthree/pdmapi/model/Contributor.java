package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Contributor on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class Contributor {
    @JsonProperty("contributorId") private final String contributorId;
    @JsonProperty("contributorName") private String contributorName;

    /**
    * Create a contributor with the given id and name
    * @param contributorId 6 digit ID of the contributor
    * @param contributorName name of the contributor
    */
    public Contributor(@JsonProperty("contributorId") String contributorId, @JsonProperty("contributorName") String contributorName) {
        this.contributorId = contributorId;
        this.contributorName = contributorName;
    }

    /**
     * Retrieves the id of the contributor
     * @return the id of the contributor
     */
    public String getContributorId() {
        return this.contributorId;
    }

    /**
     * Retrieves the name of the contributor
     * @return the name of the contributor
     */
    public String getContributorName() {
        return contributorName;
    }

    /**
     * Updates the contributor name
     * @param contributorName the new contributor name
     */
    public void setContributorName(@JsonProperty("contributorName") String contributorName) {
        this.contributorName = contributorName;
    }
}
