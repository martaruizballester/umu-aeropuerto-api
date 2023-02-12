package com.aeropuerto.aeropuertoapi.modules.plane;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneService {
    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

}