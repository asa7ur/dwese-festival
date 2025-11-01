package org.iesalixar.daw2.danielgarik.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private Long id;
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Long artistId;
    private String artistName;
    private Long stageId;
    private String stageName;

    public Performance(String code, Date date, Time startTime, Time endTime, Long artistId, Long stageId) {
        this.code = code;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artistId = artistId;
        this.stageId = stageId;
    }
}
