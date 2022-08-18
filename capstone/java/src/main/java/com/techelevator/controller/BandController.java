package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

//@PreAuthorize("isAuthenticated")
@RestController
@CrossOrigin
public class BandController {

    private BandDao bandDao;

    public BandController(BandDao bandDao){
        this.bandDao = bandDao;
    }

    @RequestMapping(path = "/bands", method = RequestMethod.GET)
    public List<Band> listOfBands (){
        return bandDao.listAllBands();
    }

    @RequestMapping(path = "/bands/genre/{genre}", method = RequestMethod.GET)
    public List<Band> bandsByGenre (@PathVariable("genre") String genre){
        return bandDao.listBandsByGenre(genre);
    }

    @RequestMapping(path = "/bands/name/{name}", method = RequestMethod.GET)
    public Band band (@PathVariable("name") String bandName){
        return bandDao.getBandByName(bandName);
    }

    @RequestMapping(path = "/bands/id/{id}", method = RequestMethod.GET)
    public Band band (@PathVariable("id") int bandId){
        return bandDao.getBandById(bandId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/bands/update/{id}", method = RequestMethod.PUT)
    public void updateBand (@PathVariable("id") int bandId, @RequestBody BandDto bandToUpdate){
        bandDao.updateBand(bandToUpdate.getBandName(), bandToUpdate.getDescription(), bandToUpdate.getImageLink(), bandToUpdate.getGenre(), bandId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path = "/bands/register", method = RequestMethod.POST)
    public void createBand (Principal principal, @Valid @RequestBody BandDto newBand){
        bandDao.createBand(newBand.getBandName(), newBand.getDescription(), newBand.getImageLink(), newBand.getGenre(), principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/bands/favorites", method = RequestMethod.PUT)
    public void favoriteBand (Principal principal, @RequestBody FavoriteDTO favoriteDTO) {
        bandDao.setFavorite(favoriteDTO.getBandId(), principal);
    }

    @RequestMapping (path = "/bands/favorites/{id}", method = RequestMethod.GET)
    public List<Band> favoriteBandsByUser(@PathVariable("id") int userId, Principal principal){
        return bandDao.favoriteBandsByUser(userId, principal);
    }

    @RequestMapping (path = "/bands/myFollowers/{id}", method = RequestMethod.GET)
    public List<Integer> myFollowers(@PathVariable("id") int bandId) {
        return bandDao.usersFollowingMyBand(bandId);
    }

    @RequestMapping(path = "/bands/myManager/{id}", method = RequestMethod.GET)
    public int myManager(@PathVariable("id") int bandId) {
        return bandDao.managerOfMyBand(bandId);
    }

    @RequestMapping (path = "/band/{bandId}/delete/favorite/{id}", method = RequestMethod.DELETE)
    public void deleteFavorite(@PathVariable("bandId") int bandId, @PathVariable("id") int userId) {
        bandDao.deleteFavorite(userId, bandId);
    }
}
