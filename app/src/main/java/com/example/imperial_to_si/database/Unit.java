package com.example.imperial_to_si.database;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Unit {

    private Integer id;
    private String name;
    private Double factor;
    private String unitSI;
}
