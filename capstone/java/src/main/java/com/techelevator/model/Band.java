package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Band {

    private int bandId;
    private String bandName;
    private String description;
    private String imageLink;
    private List<Genre> genre;
    @JsonIgnore
    private boolean activated;

    public Band() {

    }
    public Band(int bandId, String bandName, String description, String imageLink, List<Genre> genre, boolean activated) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.description = description;
        this.imageLink = imageLink;
        this.activated = activated;
        this.genre = genre;
    }

    public int getBandId() {
        return bandId;
    }

    public String getDescription() { return description;}

    public String getBandName() {
        return bandName;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setBandID(int bandId) { this.bandId = bandId;}

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setDescription(String description) { this.description = description;}

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Genre> getGenres() {
        return genre;
    }

    public void setGenres(List<Genre> genre) {
        this.genre = genre;
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
