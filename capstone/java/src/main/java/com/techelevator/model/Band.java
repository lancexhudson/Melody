package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Band {

    private int bandId;
    private String bandName;
    @JsonIgnore
    private boolean activated;



    public Band(int bandId, String bandName, boolean activated) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.activated = activated;
    }

    public int getBandId() {
        return bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return bandId  == band.bandId &&
                activated == band.activated &&
                Objects.equals(bandName, band.bandName) &&
                Objects.equals(activated, band.activated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandId, bandName, activated);
    }


}
