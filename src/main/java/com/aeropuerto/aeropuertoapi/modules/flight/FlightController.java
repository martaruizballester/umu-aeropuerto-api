package com.aeropuerto.aeropuertoapi.modules.flight;

import com.aeropuerto.aeropuertoapi.constants.FlightStatus;
import com.aeropuerto.aeropuertoapi.modules.airline.Airline;
import com.aeropuerto.aeropuertoapi.modules.airline.AirlineRepository;
import com.aeropuerto.aeropuertoapi.modules.flight.dto.FlightInput;
import com.aeropuerto.aeropuertoapi.modules.flight.dto.FlightDeparture;
import com.aeropuerto.aeropuertoapi.modules.plane.Plane;
import com.aeropuerto.aeropuertoapi.modules.plane.PlaneRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("{airlineName}/services")
public class FlightController {
    @Autowired
    private final FlightService flightService;
    private final ModelMapper modelMapper;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    public FlightController(FlightService flightService, PlaneRepository planeRepository,ModelMapper modelMapper) {
        this.flightService = flightService;
        this.planeRepository = planeRepository;
        this.airlineRepository = airlineRepository;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/vuelo")
    public ResponseEntity<List<FlightDeparture>> buscar(@PathVariable final String airlineName) {

        List<Flight> flights = flightService.findFlightsByAirline_Name(airlineName);
        List<FlightDeparture> flightDepartures = flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDeparture.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(flightDepartures);
    }
    @GetMapping("/vuelo/{id}")
    public ResponseEntity<List<FlightDeparture>> search(@PathVariable final Long id, @PathVariable final String airlineName) {

        Flight flights = flightService.findFlightsByIdAndAirline_Name(id,airlineName);
        if(flights!=null){
            List<FlightDeparture> flightDepartures = List.of(modelMapper.map(flights, FlightDeparture.class));
            return ResponseEntity.ok(flightDepartures);

        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/vuelo")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDeparture addFlight(@RequestBody FlightInput newflight,@PathVariable final String airlineName) throws NotFoundException {
        Plane planeId = planeRepository.findPlaneById(newflight.getPlaneId());
        if(planeId==null) {
            throw new NotFoundException("El avion no existe");
        }
        Flight flights = flightService.findFlightByPlane_Id(newflight.getPlaneId());
        if(flights!=null){
            throw new NotFoundException("El avion ya esta asignado a un vuelo");
        }
        Plane planeAirline = planeRepository.findPlaneByIdAndAirline_Name(newflight.getPlaneId(),airlineName);
        if(planeAirline==null){
            throw new NotFoundException("El avion no pertenece a la aerolinea");
        }
        Airline airline = airlineRepository.findByName(airlineName);
        Flight flightToCreate= new Flight();
        flightToCreate.setDepartureTime(newflight.getDepartureTime());
        flightToCreate.setFlightDate(newflight.getFlightDate());
        flightToCreate.setOrigin(newflight.getOrigin());
        flightToCreate.setDestination(newflight.getDestination());
        flightToCreate.setPlane(planeId);
        flightToCreate.setAirline(airline);
        Flight flight=flightRepository.save(flightToCreate);
        return modelMapper.map(flight, FlightDeparture.class);
    }




    @PutMapping("/vuelo/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @PathVariable final String airlineName,@RequestBody Flight flightDetails) {
        Flight updateFlight = flightRepository.findFlightsByIdAndAirline_Name(id,airlineName);
        if(updateFlight!=null) {
            updateFlight.setFlightDate(flightDetails.getFlightDate());
            updateFlight.setOrigin(flightDetails.getOrigin());
            updateFlight.setDestination(flightDetails.getDestination());
            updateFlight.setStatus(flightDetails.getStatus());
            updateFlight.setTakeoffTime(flightDetails.getTakeoffTime());
            updateFlight.setDepartureTime(flightDetails.getDepartureTime());
            flightService.saveFlight(updateFlight);
            return ResponseEntity.ok(updateFlight);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/vuelo/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id, @PathVariable final String airlineName) throws NotFoundException {
        Flight updateFlight = flightRepository.findFlightsByIdAndAirline_Name(id,airlineName);
        if(updateFlight!=null) {
            flightService.deleteFlight(id);
            return ResponseEntity.ok(updateFlight);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/salida")
    public ResponseEntity<List<FlightDeparture>> searchOuts(@PathVariable final String airlineName){
        List<Flight> flights = flightService.findFlightsByStatusAndAirline_Name(FlightStatus.ON_AIR.ordinal(),airlineName);
        if(flights!=null){
            List<FlightDeparture> flightDepartures = flights.stream()
                    .map(flight -> modelMapper.map(flight, FlightDeparture.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(flightDepartures);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/salida/{id}")
    public ResponseEntity<FlightDeparture> searchDeparture(@PathVariable final Long id, @PathVariable final String airlineName) {

        Flight flights = flightService.findFlightByIdAndStatusAndAirline_Name(id,FlightStatus.ON_AIR.ordinal(),airlineName);
        if(flights!=null){
            FlightDeparture flightDeparture = modelMapper.map(flights, FlightDeparture.class);
            return ResponseEntity.ok(flightDeparture);
        }else{
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("/salida/{id}/despegue")
    public ResponseEntity<Flight> updateFlightTakeoff(@PathVariable Long id, @PathVariable final String airlineName, @RequestBody Flight flightDetailsTakeoff) {
        Flight updateFlightTakeoff = flightService.findFlightByIdAndStatusAndAirline_Name(id, FlightStatus.TAKE_OFF.ordinal(), airlineName);
        if (updateFlightTakeoff != null) {
            updateFlightTakeoff.setFlightDate(flightDetailsTakeoff.getFlightDate());
            updateFlightTakeoff.setOrigin(flightDetailsTakeoff.getOrigin());
            updateFlightTakeoff.setDestination(flightDetailsTakeoff.getDestination());
            updateFlightTakeoff.setStatus(flightDetailsTakeoff.getStatus());
            updateFlightTakeoff.setTakeoffTime(flightDetailsTakeoff.getTakeoffTime());
            updateFlightTakeoff.setDepartureTime(flightDetailsTakeoff.getDepartureTime());
            flightService.saveFlight(updateFlightTakeoff);
            return ResponseEntity.ok(updateFlightTakeoff);

        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
