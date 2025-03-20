package itmo.devops.controller;

import itmo.devops.dto.DeleteFlightDto;
import itmo.devops.dto.FlightDto;
import itmo.devops.error.AppError;
import itmo.devops.model.Flight;
import itmo.devops.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FlightController {

    private final FlightService flightService;

    @PutMapping(value = "/flight")
    public ResponseEntity<?> create(@RequestBody FlightDto flightDto) {
        try {
            flightService.create(flightDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "error on creating flight"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/flight")
    public ResponseEntity<?> delete(@RequestBody DeleteFlightDto dto) {
        try {
            flightService.delete(dto.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "error on deleting flight"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/flight")
    public ResponseEntity<?> update(@RequestBody Flight flight) {
        Flight updatedFlight;

        try {
            updatedFlight = flightService.updateById(flight);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "error on updating flight"), HttpStatus.BAD_REQUEST);
        }

        if (updatedFlight == null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "no entity found for updating"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @GetMapping("/flights")
    public ResponseEntity<?> getAll() {
        List<Flight> flights;

        try {
            flights = flightService.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "error on getting flight"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
