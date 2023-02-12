package com.aeropuerto.aeropuertoapi.modules.flight;

import com.aeropuerto.aeropuertoapi.constants.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByAirline_Name(String airlineName);

    Flight findFlightsByIdAndAirline_Name(Long Id,String airline);
   List<Flight>  findFlightsByStatusAndAirline_Name(Integer status,String airline);
    Flight save(Flight flight);
    Flight findFlightByIdAndStatusAndAirline_Name(Long Id,Integer status,String airline);
    Flight findFlightByPlane_Id(Long id);
}
