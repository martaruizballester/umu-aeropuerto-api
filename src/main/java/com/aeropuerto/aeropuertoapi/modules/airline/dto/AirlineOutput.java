package com.aeropuerto.aeropuertoapi.modules.airline.dto;

import lombok.Data;

@Data
public class AirlineOutput {
    private Long id;
    private String name;
    private Integer numPlanes;
}
