package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BandDto {

    private int bandId;
    private String bandName;
    private String description;
    private String imageLink;
    private Integer[] genre;
    @JsonIgnore
    private boolean activated;

    public Integer[] getGenre() {
        return genre;
    }

    public void setGenre(Integer[] genre) {
        this.genre = genre;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
