package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Stage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

@Repository
public class StageDAOImpl implements StageDAO {

    private static final Logger logger = LoggerFactory.getLogger(StageDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public StageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stage> listAllStages() {
        logger.info("Listing all stages from the database.");
        String sql = "SELECT * FROM Stages"; // Mantener plural si la tabla se llama así
        List<Stage> stages = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stage.class));
        logger.info("Retrieved {} stages from the database.", stages.size());
        return stages;
    }

    @Override
    public void insertStage(Stage stage) {
        logger.info("Inserting stage with code: {}, name: {}", stage.getCode(), stage.getName());
        String sql = "INSERT INTO Stages (code, name, capacity) VALUES (?, ?, ?)";
        int rows = jdbcTemplate.update(sql, stage.getCode(), stage.getName());
        logger.info("Inserted stage. Rows affected: {}", rows);
    }


    @Override
    public void updateStage(Stage stage) {
        logger.info("Updating stage with id: {}", stage.getId());
        String sql = "UPDATE Stages SET code = ?, name = ?, capacity = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, stage.getCode(), stage.getName(), stage.getCapacity(), stage.getId());
        logger.info("Updated stage. Rows affected: {}", rows);
    }


    @Override
    public void deleteStage(Long id) throws SQLException {
        String sql = "DELETE FROM Stages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Stage getStageById(Long id) throws SQLException {
        String sql = "SELECT * FROM Stages WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Stage.class), id);
    }

    @Override
    public boolean existsStageByCode(String code) {
        logger.info("Checking if stage with code: {} exists", code);
        String sql = "SELECT COUNT(*) FROM Stages WHERE UPPER(code) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Stage with code {} exists: {}", code, exists);
        return exists;
    }

    @Override
    public boolean existsStageByCodeAndNotId(String code, Long id) {
        logger.info("Checking if stage with code: {} exists excluding id: {}", code, id);
        String sql = "SELECT COUNT(*) FROM Stages WHERE UPPER(code) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Stage with code: {} exists excluding id {}: {}", code, id, exists);
        return exists;
    }
}
