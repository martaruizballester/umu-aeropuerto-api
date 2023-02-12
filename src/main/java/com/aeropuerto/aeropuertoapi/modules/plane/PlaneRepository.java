package com.aeropuerto.aeropuertoapi.modules.plane;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    Plane findPlaneByIdAndAirline_Name(Long Id,String airlineName);
    Plane findPlaneById(Long Id);

}
