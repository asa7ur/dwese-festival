package org.iesalixar.daw2.danielgarik.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private Long id;
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long artistId;
    private Long stageId;

    public Performance(String code, LocalDate date, LocalTime startTime, LocalTime endTime, Long artistId, Long stageId) {
        this.code = code;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artistId = artistId;
        this.stageId = stageId;
    }
}
