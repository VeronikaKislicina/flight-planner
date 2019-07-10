package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.flights.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    FlightService flightService;

    @Autowired
    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity fetchFlight(@PathVariable("id") Long id) {
        return flightService.fetchFlight(id);
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight(@RequestBody @Valid AddFlightRequestDTO addFlightRequestDTO) {
        Flight flight = flightService.addFlight(addFlightRequestDTO);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    void handleIllegalArgument() {
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    void handleIllegalState() {
    }
}
