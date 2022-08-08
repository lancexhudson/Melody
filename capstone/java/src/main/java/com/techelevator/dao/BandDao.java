package com.techelevator.dao;

import com.techelevator.model.Band;

import java.util.List;

public interface BandDao {

    List<Band> listAllBands();

    List<Band> listBandsByGenre(String genreName);

    Band getBandByName(String bandName);



}
