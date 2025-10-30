package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Sponsor;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Stage;

import java.sql.SQLException;
import java.util.List;

public interface StageSponsorDAO {
    List<Sponsor> getSponsorByStageId(Long stageId) throws SQLException;
    List<Stage> getStagesBySponsorId(Long sponsorId) throws SQLException;
    void deleteByStageId(Long stageId) throws SQLException;
    void updateSponsorsForStage(Long stageId, List<Long> sponsorIds) throws SQLException;
}
