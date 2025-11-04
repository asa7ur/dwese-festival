package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.SQLException;

@Repository
public class PerformanceDAOImpl implements PerformanceDAO {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public PerformanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Performance> listAllPerformances() throws SQLException {
        logger.info("Listing all performances from the database...");

        String sql = "SELECT p.id, p.code, p.date, " +
                "p.start_time AS startTime, " +
                "p.end_time AS endTime, " +
                "p.artist_id AS artistId, " +
                "p.stage_id AS stageId, " +
                "a.name AS artistName, " +
                "s.name AS stageName " +
                "FROM Performances p " +
                "JOIN Artists a ON p.artist_id = a.id " +
                "JOIN Stages s ON p.stage_id = s.id";

        List<Performance> performances = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Performance.class));

        logger.info("Retrieved {} performances from the database.", performances.size());
        return performances;
    }


    @Override
    public void insertPerformance(Performance performance) throws SQLException {
        logger.info("Inserting performance with code: {}", performance.getCode());
        String sql = "INSERT INTO Performances (code, date, start_time, end_time, artist_id, stage_id) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql,
                performance.getCode(),
                performance.getDate(),
                performance.getStartTime(),
                performance.getEndTime(),
                performance.getArtistId(),
                performance.getStageId());
        logger.info("Inserted performance. Rows affected: {}", rowsAffected);
    }

    @Override
    public void updatePerformance(Performance performance) throws SQLException {
        logger.info("Updating performance with id: {}", performance.getId());
        String sql = "UPDATE Performances SET code = ?, date = ?, start_time = ?, end_time = ?, artist_id = ?, stage_id = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                performance.getCode(),
                performance.getDate(),
                performance.getStartTime(),
                performance.getEndTime(),
                performance.getArtistId(),
                performance.getStageId(),
                performance.getId());
        logger.info("Updated performance. Rows affected: {}", rowsAffected);
    }

    @Override
    public void deletePerformance(Long id) throws SQLException {
        logger.info("Deleting performance with id: {}", id);
        String sql = "DELETE FROM Performances WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted performance. Rows affected: {}", rowsAffected);
    }

    @Override
    public Performance getPerformanceById(Long id) throws SQLException {
        logger.info("Retrieving performance with id: {}", id);
        String sql = "SELECT p.id, p.code, p.date, " +
                "p.start_time AS startTime, " +
                "p.end_time AS endTime, " +
                "p.artist_id AS artistId, " +
                "p.stage_id AS stageId, " +
                "a.name AS artistName, " +
                "s.name AS stageName " +
                "FROM Performances p " +
                "JOIN Artists a ON p.artist_id = a.id " +
                "JOIN Stages s ON p.stage_id = s.id " +
                "WHERE p.id = ?";
        try {
            Performance performance = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Performance.class), id);
            logger.info("Retrieved performance with id: {}", id);
            return performance;
        } catch (Exception e) {
            logger.warn("No performance found with id: {}", id);
            return null;
        }
    }

    @Override
    public boolean existsPerformanceByCode(String code) throws SQLException {
        logger.info("Checking if performance with code {} exists.", code);
        String sql = "SELECT COUNT(*) FROM Performances WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Performance with code {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsPerformanceByCodeAndNotId(String code, Long id) throws SQLException {
        logger.info("Checking if performance with code {} exists excluding id {}.", code, id);
        String sql = "SELECT COUNT(*) FROM Performances WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Performance with code {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }

    @Override
    public boolean existsPerformanceByArtistId(Long artistId) throws SQLException {
        logger.info("Checking if artist with id {} has any performances.", artistId);
        String sql = "SELECT COUNT(*) FROM Performances WHERE artist_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, artistId);
        boolean exists = count != null && count > 0;
        logger.info("Artist with id {} has performances: {}", artistId, exists);
        return exists;
    }

    @Override
    public boolean existsPerformanceByStageId(Long stageId) throws SQLException {
        logger.info("Checking if stage with id {} has any performances.", stageId);
        String sql = "SELECT COUNT(*) FROM Performances WHERE stage_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, stageId);
        boolean exists = count != null && count > 0;
        logger.info("Stage with id {} has performances: {}", stageId, exists);
        return exists;
    }
}