package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Sponsor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SponsorDAOImpl implements SponsorDAO {
    private static final Logger logger = LoggerFactory.getLogger(SponsorDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public SponsorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sponsor> listAllSponsors() {
        logger.info("Listing all sponsors from the database.");
        String sql = "SELECT * FROM Sponsors";
        List<Sponsor> sponsors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sponsor.class));
        logger.info("Retrieved {} sponsors from the database.", sponsors.size());
        return sponsors;
    }

    @Override
    public void insertSponsor(Sponsor sponsor) {
        logger.info("Inserting sponsor with code: {}, name: {}, phone: {}, email: {} and contribution: {}", sponsor.getCode(), sponsor.getName(), sponsor.getPhone(), sponsor.getEmail(), sponsor.getContribution());
        String sql = "INSERT INTO Sponsors (code, name, phone, email, contribution) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, sponsor.getCode(), sponsor.getName(), sponsor.getPhone(), sponsor.getEmail(), sponsor.getContribution());
        logger.info("Inserted sponsor. Rows affected: {}", rowsAffected);
    }

    @Override
    public void updateSponsor(Sponsor sponsor) {
        logger.info("Updating sponsor with id: {}", sponsor.getId());
        String sql = "UPDATE Sponsors SET code = ?, name = ?, phone = ?, email = ?, contribution = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, sponsor.getCode(), sponsor.getName(), sponsor.getPhone(), sponsor.getEmail(), sponsor.getContribution(), sponsor.getId());
        logger.info("Updated sponsor. Rows affected: {}", rowsAffected);
    }

    @Override
    public void deleteSponsor(Long id) {
        logger.info("Deleting sponsor with id: {}", id);
        String sql = "DELETE FROM Sponsors WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted sponsor. Rows affected: {}", rowsAffected);
    }

    @Override
    public Sponsor getSponsorById(Long id) {
        logger.info("Retrieving sponsor by id: {}", id);
        String sql = "SELECT * FROM Sponsors WHERE id = ?";
        try {
            Sponsor sponsor = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Sponsor.class), id);
            logger.info("Sponsor retrieved: {} - {}", sponsor.getCode(), sponsor.getName());
            return sponsor;
        } catch (Exception e) {
            logger.warn("No sponsor found with id: {}", id);
            return null;
        }
    }

    @Override
    public boolean existsSponsorByCode(String code) {
        logger.info("Checking if sponsor with code: {} exists", code);
        String sql = "SELECT COUNT(*) FROM Sponsors WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Sponsor with code: {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsSponsorByCodeAndNotId(String code, Long id) {
        logger.info("Checking if sponsor with code: {} exists excluding id: {}",
                code, id);
        String sql = "SELECT COUNT(*) FROM Sponsors WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Sponsor with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}