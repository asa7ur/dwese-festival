package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private Long id;
    private String code;
    private String name;
    private String country;

    public Artist(String code, String name, String country){
        this.code = code;
        this.name = name;
        this.country = country;
    }
}
