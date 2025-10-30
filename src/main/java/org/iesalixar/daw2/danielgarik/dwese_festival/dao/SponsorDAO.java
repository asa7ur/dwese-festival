package org.iesalixar.daw2.danielgarik.dwese_festival.dao;

import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Sponsor;

import java.sql.SQLException;
import java.util.List;

public interface SponsorDAO {
    List<Sponsor> listAllSponsors() throws SQLException;

    void insertSponsor(Sponsor sponsor) throws SQLException;

    void updateSponsor(Sponsor sponsor) throws SQLException;

    void deleteSponsor(Long id) throws SQLException;

    Sponsor getSponsorById(Long id) throws SQLException;

    boolean existsSponsorByCode(String code) throws SQLException;

    boolean existsSponsorByCodeAndNotId(String code, Long id) throws SQLException;

}
