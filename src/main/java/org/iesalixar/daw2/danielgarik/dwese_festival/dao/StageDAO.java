package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Stage;
import java.sql.SQLException;
import java.util.List;

public interface StageDAO {
    List<Stage> listAllStages() throws SQLException;
    void insertStage(Stage stage) throws SQLException;
    void updateStage(Stage stage) throws SQLException;
    void deleteStage(Long id) throws SQLException; // <- int
    Stage getStageById(Long id) throws SQLException; // <- int
    boolean existsStageByCode(String code) throws SQLException;
    boolean existsStageByCodeAndNotId(String code, Long id) throws SQLException;
}
