package com.viking.vikingtask01.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Country {
    String name;

    public Country(String name) {
        this.name = name;
    }
}
