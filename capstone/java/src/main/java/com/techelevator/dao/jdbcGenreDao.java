package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class jdbcGenreDao implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    public jdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> listAllGenres() {
        List<Genre> allGenres = new ArrayList<>();
        String sql = "SELECT genre_id, genre_name FROM genre ORDER BY genre_name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Genre genre = mapRowToGenre(results);
                allGenres.add(genre);
            }
        return allGenres;
    }

    @Override
    public Genre getGenreById(int id) {
        String sql = "SELECT genre_id, genre_name FROM genre WHERE genre_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        Genre genre = new Genre();
        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    @Override
    public Genre getGenreByName(String genreName) {
        String sql = "SELECT genre_id, genre_name FROM genre WHERE genre_name = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreName);
        Genre genre = new Genre();
        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    private Genre mapRowToGenre(SqlRowSet rs) {
        Genre genre = new Genre();
        genre.setGenreId(rs.getInt("genre_id"));
        genre.setGenreName(rs.getString("genre_name"));
        return genre;
    }
}
