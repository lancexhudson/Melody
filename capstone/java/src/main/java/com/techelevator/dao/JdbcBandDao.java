package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcUserDao jdbcUserDao;

    public JdbcBandDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = jdbcUserDao;
    }


    @Override
    public List<Band> listAllBands() {
        List<Band> allBands = new ArrayList<>();
        String sql = "SELECT band_id, band_name, description, image_link FROM band ORDER BY band_name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        String sql2 = "SELECT genre_name, bg.genre_id " +
                " FROM genre AS g " +
                " JOIN band_genre as bg ON g.genre_id = bg.genre_id " +
                " JOIN band as b ON b.band_id = bg.band_id " +
                " WHERE b.band_id = ?";
        int theBandId = 0;

        while (results.next()) {
            List<Genre> genres = new ArrayList<>();
            Band band = mapRowToBand(results);
            theBandId = results.getInt("band_id");


            SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, theBandId);
            while (results2.next()) {

                Genre genre = new Genre();
                genre.setGenreId(results2.getInt("genre_id"));
                genre.setGenreName(results2.getString("genre_name"));
                genres.add(genre);
            }
            band.setGenres(genres);
            allBands.add(band);
        }
        return allBands;

    }

    @Override
    public List<Band> listBandsByGenre(String genreName) {
        List<Band> bandsOfGenre = new ArrayList<>();
        String sql = "SELECT b.band_id, b.band_name, b.description, b.image_link " +
                "FROM band AS b JOIN band_genre AS bg ON b.band_id = bg.band_id " +
                "JOIN genre AS g ON bg.genre_id = g.genre_id " +
                "WHERE g.genre_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreName);
        while (results.next()) {
            Band band = mapRowToBand(results);
            bandsOfGenre.add(band);
        }
        return bandsOfGenre;
    }

    @Override
    public Band getBandByName(String bandName) throws BandNotFoundException {
        String sql = "SELECT band_id, band_name, description FROM band WHERE band_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandName);
        Band theBand = new Band();
        if (results.next()) {
            theBand = mapRowToBand(results);
            return theBand;
        }
        throw new BandNotFoundException();
    }

    @Override
    public Band getBandById(int bandId) throws BandNotFoundException {
        String sql = "SELECT band_id, band_name, description, image_link FROM band WHERE band_id = ?;";

        String sql2 = "SELECT genre_name, bg.genre_id " +
                " FROM genre AS g " +
                " JOIN band_genre as bg ON g.genre_id = bg.genre_id " +
                " JOIN band as b ON b.band_id = bg.band_id " +
                " WHERE b.band_id = ?";

        int theBandId = 0;



        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        Band theBand = new Band();
        if (results.next()) {
            List<Genre> genres = new ArrayList<>();
            theBandId = bandId;
            SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, theBandId);

            while(results2.next()) {
                Genre genre = new Genre();
                genre.setGenreId(results2.getInt("genre_id"));
                genre.setGenreName(results2.getString("genre_name"));
                genres.add(genre);
            }

            theBand = mapRowToBand(results);
            theBand.setGenres(genres);
            return theBand;
        }
        throw new BandNotFoundException();
    }

    @Override
    public boolean createBand(String bandName, String description, String imageLink, Integer[] genreId, Principal principal) {
        Band newBand = new Band();
        newBand.setBandName(bandName);
        newBand.setDescription(description);
        String sql = "INSERT INTO band (band_name, description, image_link) VALUES (?, ?, ?) RETURNING band_id;";
        int bandId = jdbcTemplate.queryForObject(sql, int.class, bandName, description, imageLink);
        newBand.setBandID(bandId);
        User user = jdbcUserDao.findByUsername(principal.getName());
        String sql2 = "INSERT INTO band_user (user_id, band_id) VALUES (?,?) RETURNING band_id;";
        int bandId2 = jdbcTemplate.queryForObject(sql2, int.class, user.getId(), newBand.getBandId());
        String newRole = "USER, BAND_MANAGER";
        user.setAuthorities(newRole);
        String sql3 = "UPDATE users SET role = ? WHERE user_id = ?;";
        jdbcTemplate.update(sql3, newRole, user.getId());
        List<Integer> genreIds = Arrays.asList(genreId);
        setGenres(genreIds, bandId);
        return true;
    }

    @Override
    public void updateBand(String bandName, String description, String imageLink, Integer[] genreId, int bandId) {
        String sql = "UPDATE band SET band_name = ?, description = ?, image_link = ? WHERE band_id = ?";
        jdbcTemplate.update(sql, bandName, description, imageLink, bandId);
        List<Integer> genreIds = Arrays.asList(genreId);
        updateGenres(genreIds, bandId);
    }

    @Override
    public void setGenres(List<Integer> genreIds, int bandId) {
        String sql = "INSERT INTO band_genre (band_id, genre_id) VALUES (?,?)";
        for (int id : genreIds) {
            jdbcTemplate.update(sql, bandId, id);
        }

    }

    @Override
    public void updateGenres(List<Integer> genreIds, int bandId) {
        String sql = "DELETE FROM band_genre WHERE band_id = ?";
        jdbcTemplate.update(sql, bandId);
        String sql2 = "INSERT INTO band_genre (band_id, genre_id) VALUES (?,?)";
        for (int id : genreIds) {
            jdbcTemplate.update(sql2, bandId, id);
        }

    }

    @Override
    public void setFavorite(int bandId, Principal principal) {
        String sql = "INSERT INTO user_favorite_bands (user_id, band_id) VALUES (?,?)";
        User user = jdbcUserDao.findByUsername(principal.getName());
        jdbcTemplate.update(sql, user.getId(), bandId);
    }

    @Override
    public List<Band> favoriteBandsByUser(int userId, Principal principal){
        String sql = "SELECT u.user_id, band.band_id, band_name, description, image_link FROM band " +
                "JOIN user_favorite_bands AS ufb ON ufb.band_id = band.band_id " +
                "JOIN users as u ON u.user_id = ufb.user_id " +
                "WHERE u.user_id = ?;";

        userId = jdbcUserDao.findIdByUsername(principal.getName());
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

        String sql2 = "SELECT genre_name, bg.genre_id " +
                " FROM genre AS g " +
                " JOIN band_genre as bg ON g.genre_id = bg.genre_id " +
                " JOIN band as b ON b.band_id = bg.band_id " +
                " WHERE b.band_id = ?";
        int theBandId = 0;

        List<Band>favoriteBands = new ArrayList<>();
        while (result.next()) {

            List<Genre> genres = new ArrayList<>();
            Band band = mapRowToBand(result);
            theBandId = result.getInt("band_id");
//            theBandId = results.getInt("band.band_id");

            SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, theBandId);
            while (results2.next()) {

                Genre genre = new Genre();
                genre.setGenreId(results2.getInt("genre_id"));
                genre.setGenreName(results2.getString("genre_name"));
                genres.add(genre);

            }

            band.setGenres(genres);
            favoriteBands.add(band);
        }
        return favoriteBands;
    }

    @Override
    public List<Integer> usersFollowingMyBand(int bandId) {
        String sql = "SELECT user_id FROM user_favorite_bands WHERE band_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        List<Integer> users = new ArrayList<>();
        while (results.next()) {
            users.add(results.getInt("user_id"));
        }
        return users;
    }

    @Override
    public int managerOfMyBand(int bandId) {
        String sql = "SELECT user_id FROM band_user WHERE band_id = ?;";
        int managerId = jdbcTemplate.queryForObject(sql, int.class, bandId);
        return managerId;
    }

    @Override
    public void deleteFavorite(int userId, int band_id) {
        String sql = "DELETE FROM user_favorite_bands WHERE user_id = ? AND band_id = ?";
        jdbcTemplate.update(sql,userId, band_id);
        String sql2 = "DELETE FROM messages WHERE user_id = ? AND band_id = ?";
        jdbcTemplate.update(sql2, userId, band_id);
    }


    private Band mapRowToBand(SqlRowSet rs) {
        Band band = new Band();
        band.setBandID(rs.getInt("band_id"));
        band.setBandName(rs.getString("band_name"));
        band.setDescription(rs.getString("description"));
        band.setImageLink((rs.getString("image_link")));
        return band;
    }

}
