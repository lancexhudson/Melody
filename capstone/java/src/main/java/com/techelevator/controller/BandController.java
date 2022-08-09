package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import com.techelevator.model.BandAlreadyExistsException;
import com.techelevator.model.BandDto;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    public void registerBand (Principal principal, @Valid @RequestBody BandDto newBand){
        User bandManager = new User();
        bandManager.setAuthorities("BAND_MANAGER");
        if (!bandDao.createBand(newBand.getBandName(), newBand.getDescription())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Band registration failed.");
        }

        //TODO: make the user who creates the account the band manager role.

        //want to check for duplicate bands, but not essential

//        try {
//            Band band = bandDao.getBandByName(newBand.getBandName());
//            throw new BandAlreadyExistsException();
//        } catch (BandAlreadyExistsException e){
//            bandDao.createBand(newBand.getBandName(),newBand.getDescription());
//        }
    }

}
