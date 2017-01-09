package org.dimamir999.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tournament {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surface")
    private Surface surface;

    @JsonProperty(value = "court")
    private CourtType courtType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public CourtType getCourtType() {
        return courtType;
    }

    public void setCourtType(CourtType courtType) {
        this.courtType = courtType;
    }
}
