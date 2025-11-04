package org.iesalixar.daw2.danielgarik.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private Long id;
    private String code;
    private Date date;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private Long artistId;
    private String artistName;
    private Long stageId;
    private String stageName;

    public Performance(String code, Date date, LocalTime startTime, LocalTime endTime, Long artistId, Long stageId) {
        this.code = code;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artistId = artistId;
        this.stageId = stageId;
    }
}
