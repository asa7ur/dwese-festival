package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDAOImpl implements ArtistDAO {
    private static final Logger logger = LoggerFactory.getLogger(ArtistDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public ArtistDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Artist> listAllArtists() {
        logger.info("Listing all artists from the database.");
        String sql = "SELECT * FROM Artists";
        List<Artist> artists = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Artist.class));
        logger.info("Retrieved {} artists from the database.", artists.size());
        return artists;
    }

    @Override
    public void insertArtist(Artist artist) {
        logger.info("Inserting artist with code: {}, name: {}, phone: {} and email: {}", artist.getCode(), artist.getName(), artist.getPhone(), artist.getEmail());
        String sql = "INSERT INTO Artists (code, name, phone, email) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, artist.getCode(), artist.getName(), artist.getPhone(), artist.getEmail());
        logger.info("Inserted artist. Rows affected: {}", rowsAffected);
    }

    @Override
    public void updateArtist(Artist artist) {
        logger.info("Updating artist with id: {}", artist.getId());
        String sql = "UPDATE Artists SET code = ?, name = ?, phone = ?, email = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, artist.getCode(), artist.getName(), artist.getPhone(), artist.getEmail(), artist.getId());
        logger.info("Updated artist. Rows affected: {}", rowsAffected);
    }

    @Override
    public void deleteArtist(Long id) {
        logger.info("Deleting artist with id: {}", id);
        String sql = "DELETE FROM Artists WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted artist. Rows affected: {}", rowsAffected);
    }

    @Override
    public Artist getArtistById(Long id) {
        logger.info("Retrieving artist by id: {}", id);
        String sql = "SELECT * FROM Artists WHERE id = ?";
        try {
            Artist artist = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Artist.class), id);
            logger.info("Artist retrieved: {} - {}", artist.getCode(), artist.getName());
            return artist;
        } catch (Exception e) {
            logger.warn("No artist found with id: {}", id);
            return null;
        }
    }

    @Override
    public boolean existsArtistByCode(String code) {
        logger.info("Checking if artist with code: {} exists", code);
        String sql = "SELECT COUNT(*) FROM Artists WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Artist with code: {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsArtistByCodeAndNotId(String code, Long id) {
        logger.info("Checking if artist with code: {} exists excluding id: {}",
                code, id);
        String sql = "SELECT COUNT(*) FROM Artists WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Artist with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}
