package io.codelex.flightplanner.flights;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    void handleIllegalArgument() {
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    void handleIllegalState() {
    }
}
