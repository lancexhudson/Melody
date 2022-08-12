package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.GenreDTO;

import java.util.List;

public interface GenreDao {

    public List<Genre> listAllGenres();
    public Genre getGenreById(int id);
    public Genre getGenreByName(String genreName);

}
