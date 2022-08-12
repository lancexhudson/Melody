package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.model.Genre;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GenreController {

    private GenreDao genreDao;

    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @RequestMapping(path = "/genres", method = RequestMethod.GET)
    public List<Genre> listOfGenres() {
        return genreDao.listAllGenres();
    }

    @RequestMapping(path = "/genres/{id}", method = RequestMethod.GET)
    public Genre genre(@PathVariable("id") int id) {
        return genreDao.getGenreById(id);
    }

    @RequestMapping(path = "/genres/name/{name}", method = RequestMethod.GET)
    public Genre genre(@PathVariable("name") String name) {
        return genreDao.getGenreByName(name);
    }
}
