package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Performance;
import java.sql.SQLException;
import java.util.List;

public interface PerformanceDAO {
    List<Performance> listAllPerformances() throws SQLException;
    void insertPerformance(Performance performance) throws SQLException;
    void updatePerformance(Performance performance) throws SQLException;
    void deletePerformance(int id) throws SQLException;
    Performance getPerformanceById(int id) throws SQLException;
    boolean existsPerformanceByCode(String code) throws SQLException;
    boolean existsPerformanceByCodeAndNotId(String code, Long id) throws SQLException;
}
