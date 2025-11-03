package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Sponsor;
import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class StageSponsorDAOImpl implements StageSponsorDAO {

    private static final Logger logger = LoggerFactory.getLogger(StageSponsorDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public StageSponsorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sponsor> getSponsorByStageId(Long stageId) throws SQLException {
        logger.info("Getting sponsors for stage ID {}", stageId);
        String sql = "SELECT s.* FROM Sponsors s " +
                "INNER JOIN Stage_Sponsors ss ON s.id = ss.sponsor_id " +
                "WHERE ss.stage_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sponsor.class), stageId);
    }

    @Override
    public List<Stage> getStagesBySponsorId(Long sponsorId) throws SQLException {
        logger.info("Getting stages for sponsor ID {}", sponsorId);
        String sql = "SELECT st.* FROM Stages st " +
                "INNER JOIN Stage_Sponsors ss ON st.id = ss.stage_id " +
                "WHERE ss.sponsor_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Stage.class), sponsorId);
    }

    @Override
    public void deleteByStageId(Long stageId) throws SQLException {
        logger.info("Deleting all sponsors for stage ID {}", stageId);
        String sql =  "DELETE FROM Stage_Sponsors WHERE stage_id = ?";
        jdbcTemplate.update(sql, stageId);
    }

    @Override
    public void updateSponsorsForStage(Long stageId, List<Long> sponsorIds) throws SQLException {
        logger.info("Updating sponsors for stage ID {}", stageId);
        deleteByStageId(stageId);

        if(sponsorIds == null || sponsorIds.isEmpty()){
            logger.info("No sponsors provided for stage ID {}, all relations cleared", stageId);
            return;
        }

        String sql = "INSERT INTO Stage_Sponsors (stage_id, sponsor_id) VALUES (?, ?)";
        for(Long sponsorId : sponsorIds){
            jdbcTemplate.update(sql, stageId, sponsorId);
        }
        logger.info("Updated {} sponsors for stage ID {}", sponsorIds.size(), stageId);
    }

    @Override
    public void deleteBySponsorId(Long sponsorId) throws SQLException {
        logger.info("Deleting all stages for sponsor ID {}", sponsorId);
        String sql =  "DELETE FROM Stage_Sponsors WHERE sponsor_id = ?";
        jdbcTemplate.update(sql, sponsorId);
    }

    @Override
    public void updateStagesForSponsor(Long sponsorId, List<Long> stageIds) throws SQLException {
        logger.info("Updating stages for sponsor ID {}", sponsorId);
        deleteBySponsorId(sponsorId);

        if(stageIds == null || stageIds.isEmpty()){
            logger.info("No stages provided for sponsor ID {}, all relations cleared", sponsorId);
            return;
        }

        String sql = "INSERT INTO Stage_Sponsors (stage_id, sponsor_id) VALUES (?, ?)";
        for(Long stageId : stageIds){
            jdbcTemplate.update(sql, stageId, sponsorId);
        }
        logger.info("Updated {} sponsors for stage ID {}", stageIds.size(), sponsorId);
    }
}
