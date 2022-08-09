package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandNotFoundException;
import com.techelevator.model.UserNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBandDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Band> listAllBands() {
        List<Band> allBands = new ArrayList<>();
        String sql = "SELECT band_id, band_name, description FROM band";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Band band = mapRowToBand(results);
                allBands.add(band);
            }
            return allBands;

    }

    @Override
    public List<Band> listBandsByGenre(String genreName) {
        List<Band> bandsOfGenre = new ArrayList<>();
        String sql = "SELECT b.band_id, b.band_name, b.description " +
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
    public boolean createBand(Band newBand){
        String sql = "INSERT INTO band (band_name, description) VALUES (?, ?) RETURNING band_id;";
        int bandId = jdbcTemplate.queryForObject(sql, int.class, newBand.getBandName(), newBand.getDescription());
        newBand.setBandID(bandId);
        return true;
    }

    private Band mapRowToBand(SqlRowSet rs) {
        Band band = new Band();
        band.setBandID(rs.getInt("band_id"));
        band.setBandName(rs.getString("band_name"));
        band.setDescription(rs.getString("description"));
//        band.setActivated(true);
        return band;
    }

}
