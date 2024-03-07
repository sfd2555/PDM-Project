package com.teamthree.pdmapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an Audience on the website and all their metadata
 *
 * @author Beining Zhou
 */
public class Audience {
    @JsonProperty("audienceId") private final String audienceId;
    @JsonProperty("audienceName") private final String audienceName;


    /**
     * Create a audience with the given audienceId and audienceName
     * @param audienceId 6-digit ID of the audience
     * @param audienceName name of the audience
     *
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Audience(@JsonProperty("audienceId") String audienceId, @JsonProperty("audienceName") String audienceName){
        this.audienceId = audienceId;
        this.audienceName = audienceName;
    }

    /**
     * Retrieves the id of the audience
     * @return the id of the audience
     */
    public String getAudienceId() {
        return audienceId;
    }

    /**
     * Retrieves the name of the audience
     * @return the name of the audience
     */
    public String getAudienceName() {
        return audienceName;
    }
}
