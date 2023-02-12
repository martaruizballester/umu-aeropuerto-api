package com.aeropuerto.aeropuertoapi.modules.airline;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findOneByName(String name);

    Airline findByName(String airlineName);
}
