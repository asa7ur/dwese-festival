package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Performance;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.SQLException;

@Repository
public class PerformanceDAOImpl implements PerformanceDAO {

    private final JdbcTemplate jdbcTemplate;

    public PerformanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Performance> listAllPerformances() throws SQLException {
        String sql = "SELECT * FROM Performances";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Performance.class));
    }

    @Override
    public void insertPerformance(Performance performance) throws SQLException {
        String sql = "INSERT INTO Performances (code, date, start_time, end_time, artist_id, stage_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                performance.getCode(),
                performance.getDate(),
                performance.getStartTime(),
                performance.getEndTime(),
                performance.getArtistId(),
                performance.getStageId());
    }

    @Override
    public void updatePerformance(Performance performance) throws SQLException {
        String sql = "UPDATE Performances SET code = ?, date = ?, start_time = ?, end_time = ?, artist_id = ?, stage_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                performance.getCode(),
                performance.getDate(),
                performance.getStartTime(),
                performance.getEndTime(),
                performance.getArtistId(),
                performance.getStageId(),
                performance.getId());
    }

    @Override
    public void deletePerformance(Long id) throws SQLException {  // <- int
        String sql = "DELETE FROM Performances WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Performance getPerformanceById(Long id) throws SQLException {  // <- int
        String sql = "SELECT * FROM Performances WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Performance.class), id);
    }

    @Override
    public boolean existsPerformanceByCode(String code) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Performances WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        return count != null && count > 0;
    }

    @Override
    public boolean existsPerformanceByCodeAndNotId(String code, Long id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Performances WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        return count != null && count > 0;
    }
}