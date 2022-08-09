package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //@ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping (path = "/bands/register", method = RequestMethod.POST)
//    public void register (@Valid @RequestBody)

}
