package org.iesalixar.daw2.danielgarik.dwese_festival.entities;

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
    private String phone;
    private String email;
    private String country;

    public Artist(String code, String name, String phone, String email, String country) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.country = country;
    }
}
