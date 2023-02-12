package com.aeropuerto.aeropuertoapi.modules.airline;

import com.aeropuerto.aeropuertoapi.modules.airline.dto.AirlineOutput;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("{airlineName}/services/info")
public class AirlineController {
    private final AirlineService airlineService;

    private final ModelMapper modelMapper;

    public AirlineController(AirlineService airlineService, ModelMapper modelMapper) {
        this.airlineService = airlineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<AirlineOutput> findByName(@PathVariable final String airlineName) {
        try {
            Optional<Airline> airline = airlineService.findOneByName(airlineName);
            if (airline.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            AirlineOutput airlineOutput = modelMapper.map(airline.get(), AirlineOutput.class);
            return ResponseEntity.status(HttpStatus.OK).body(airlineOutput);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
