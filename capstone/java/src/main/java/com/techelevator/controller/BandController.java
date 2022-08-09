package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import com.techelevator.model.BandAlreadyExistsException;
import com.techelevator.model.BandDto;
import com.techelevator.model.User;
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path = "/bands/register", method = RequestMethod.POST)
    public void createBand (Principal principal, @Valid @RequestBody BandDto newBand){
//        Band newBand = new Band();
//        User bandManager = new User();
//        bandManager.setAuthorities("BAND_MANAGER");
        bandDao.createBand(newBand.getBandName(), newBand.getDescription(), principal);
//        if (!bandDao.createBand(newBand.getBandName(), newBand.getDescription())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Band registration failed.");
//        }

        //TODO: make the user who creates the account the band manager role.
        //DONE: created band is added to database

        //want to check for duplicate bands, but not essential
    }

}
