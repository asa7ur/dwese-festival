package org.iesalixar.daw2.danielgarik.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {
    private Long id;
    private String code;
    private String name;
    private String phone;
    private String email;
    private BigDecimal contribution;

    public Sponsor(String code, String name, String phone, String email, BigDecimal contribution) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.contribution = contribution;
    }
}
