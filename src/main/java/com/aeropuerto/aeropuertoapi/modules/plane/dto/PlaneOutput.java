package com.aeropuerto.aeropuertoapi.modules.plane.dto;

import lombok.Data;

@Data
public class PlaneOutput {
    private Long id;
    private String model;
    private Integer capacity;
}
