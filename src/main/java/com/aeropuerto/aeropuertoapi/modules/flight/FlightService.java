package com.aeropuerto.aeropuertoapi.modules.flight;

import com.aeropuerto.aeropuertoapi.modules.airline.Airline;
import com.aeropuerto.aeropuertoapi.modules.airline.AirlineRepository;
import com.aeropuerto.aeropuertoapi.modules.flight.dto.FlightInput;
import com.aeropuerto.aeropuertoapi.modules.plane.Plane;
import com.aeropuerto.aeropuertoapi.modules.plane.PlaneRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private final PlaneRepository planeRepository;
    @Autowired
    private AirlineRepository airlineRepository;

    public FlightService(FlightRepository flightRepository,
                         PlaneRepository planeRepository) {
        this.flightRepository = flightRepository;
        this.planeRepository = planeRepository;
        this.airlineRepository = airlineRepository;
    }

    public List<Flight> findFlightsByAirline_Name(String airlineName) {
        return flightRepository.findFlightsByAirline_Name(airlineName);
    }

    public Flight findFlightsByIdAndAirline_Name(Long id, String airline) {
        return flightRepository.findFlightsByIdAndAirline_Name(id, airline);
    }



    public Flight saveFlight(Flight updateflight){
        return flightRepository.save(updateflight);
    }

    public void deleteFlight(Long id){
       flightRepository.deleteById(id);

    }

    public List<Flight> findFlightsByStatusAndAirline_Name(Integer status, String airline){
        return flightRepository.findFlightsByStatusAndAirline_Name(status,airline);
    }

    public Flight findFlightByIdAndStatusAndAirline_Name(Long id,Integer status, String airline){
        return flightRepository.findFlightByIdAndStatusAndAirline_Name(id,status,airline);
    }
    public Flight findFlightByPlane_Id(Long id){
        return flightRepository.findFlightByPlane_Id(id);
    }



}
