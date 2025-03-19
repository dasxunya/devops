package itmo.devops.controller;

import itmo.devops.error.AppError;
import itmo.devops.model.Flight;
import itmo.devops.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("api/v1")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "/create-flight")
    public ResponseEntity<?> create(@RequestBody Flight flight) {
        if (flight.getId() != null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "id is restricted"), HttpStatus.BAD_REQUEST);
        }

        try {
            flightService.createFlight(flight);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "error on creating flight"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
