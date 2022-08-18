package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandNotFoundException;
import com.techelevator.model.GenreDTO;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<Band> listAllBands();

    List<Band> listBandsByGenre(String genreName) throws BandNotFoundException;

    Band getBandByName(String bandName);

    Band getBandById(int bandId);

    boolean createBand(String bandName, String description, String imageLink, Integer[] genreIds, Principal principal);

    void updateBand(String bandName, String description, String imageLink, Integer[] genreId, int bandId);

    public void setGenres(List<Integer> genreIds, int bandId );

    void updateGenres(List<Integer> genreIds, int bandId);

    public void setFavorite(int bandId, Principal principal);

    List<Band> favoriteBandsByUser(int userId, Principal principal);

    List<Integer> usersFollowingMyBand(int bandId);

    int managerOfMyBand(int bandId);

    void deleteFavorite(int userId, int band_id);

}
