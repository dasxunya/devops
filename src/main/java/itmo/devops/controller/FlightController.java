package itmo.devops.controller;

import itmo.devops.model.Flight;
import itmo.devops.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "/create-flight")
    public ResponseEntity<?> create(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
