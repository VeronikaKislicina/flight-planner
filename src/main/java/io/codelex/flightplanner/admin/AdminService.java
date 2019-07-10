package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private FlightService flightService;

    @Autowired
    public AdminService(FlightService flightService) {
        this.flightService = flightService;
    }

    public void clearFlights() {
        flightService.deleteAll();
    }
}
