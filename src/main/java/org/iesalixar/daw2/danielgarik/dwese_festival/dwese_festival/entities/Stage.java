package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {
    private Long id;
    private String code;
    private String name;
    private int capacity;

    public Stage(String code, String name, int capacity){
        this.code = code;
        this.name = name;
        this.capacity = capacity;
    }
}
