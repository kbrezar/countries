package dev.kbrezar.countries;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Prikažite podatke o imenu države, glavnem mestu, prebivalstvu,
// območju in trimestno kodo države.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    private String name;

    private String capital;

    private int population;

    private String region;

    private int code;


}
