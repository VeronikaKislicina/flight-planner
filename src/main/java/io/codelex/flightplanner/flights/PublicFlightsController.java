package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.airport.Airport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class PublicFlightsController {

    private final FlightService flightService;

    public PublicFlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlight(@Valid @RequestBody SearchFlightDTO searchFlightDTO) {
        flightService.searchFlight(searchFlightDTO);
        return new PageResult<>();
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity findFlight(@Valid @PathVariable ("id") Long id) {
        ResponseEntity flightById = flightService.findFlightById(id);
        return flightById;
    }

    @GetMapping("/airports")
    public List<Airport> searchFlightRequest(String search) {
        return flightService.searchFlightRequest(search);
//        return new ResponseEntity<>(flight, OK);
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
