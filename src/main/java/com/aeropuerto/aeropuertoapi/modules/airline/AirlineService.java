package com.aeropuerto.aeropuertoapi.modules.airline;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineService {
    private final AirlineRepository repository;

    public AirlineService(AirlineRepository repository) {
        this.repository = repository;
    }

    public Optional<Airline> findOneByName(String name) {
        Airline airline = this.repository.findOneByName(name);
        return Optional.ofNullable(airline);
    }
}
