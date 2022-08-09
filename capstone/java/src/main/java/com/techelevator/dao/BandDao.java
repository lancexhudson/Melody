package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandNotFoundException;

import java.util.List;

public interface BandDao {

    List<Band> listAllBands();

    List<Band> listBandsByGenre(String genreName) throws BandNotFoundException;

    Band getBandByName(String bandName);

    boolean createBand(String bandName, String description);
}
