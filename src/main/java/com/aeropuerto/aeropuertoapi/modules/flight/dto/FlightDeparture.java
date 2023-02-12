package com.aeropuerto.aeropuertoapi.modules.flight.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class FlightDeparture {
    private Long id;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime takeoffTime;
    private String origin;
    private String destination;
    private Integer status;

}
