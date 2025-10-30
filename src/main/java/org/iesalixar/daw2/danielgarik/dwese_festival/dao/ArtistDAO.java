package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDAO {
    List<Artist> listAllArtists() throws SQLException;

    void insertArtist(Artist artist) throws SQLException;

    void updateArtist(Artist artist) throws SQLException;

    void deleteArtist(Long id) throws SQLException;

    Artist getArtistById(Long id) throws SQLException;

    boolean existsArtistByCode(String code) throws SQLException;

    boolean existsArtistByCodeAndNotId(String code, Long id) throws SQLException;

}
