package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Contributor on the website and all it's metadata
 * 
 * @author Caiden Williams
 */
public class Contributor {
    @JsonProperty("contributorId") private final String contributorId;
    @JsonProperty("contributorName") private final String contributorName;
    @JsonProperty("contributorType") private final String contributorType;

    /**
    * Create a contributor with the given id and name
    * @param contributorId 6 digit ID of the contributor
    * @param contributorName name of the contributor
    */
    public Contributor(@JsonProperty("contributorId") String contributorId, @JsonProperty("contributorName") String contributorName, @JsonProperty("contributorType") String contributorType) {
        this.contributorId = contributorId;
        this.contributorName = contributorName;
        this.contributorType = contributorType;
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

    public String getContributorType() {
        return contributorType;
    }
}
