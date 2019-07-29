package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.airport.Airport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FlightService {

    private static final AtomicInteger count = new AtomicInteger(0);
    private List<Flight> flights = new ArrayList<>();

    public void deleteAll() {
        flights.clear();
    }

    public ResponseEntity fetchFlight(Long id) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public Boolean checkIfFlightPresent(AddFlightRequestDTO addFlightRequestDTO) {

        for (Flight flight : flights) {
            if (
                    flight.getArrivalTime().equals(addFlightRequestDTO.getArrivalTime()) &&
                    flight.getDepartureTime().equals(addFlightRequestDTO.getDepartureTime()) &&
                    flight.getFrom().equals(addFlightRequestDTO.getFrom()) &&
                    flight.getTo().equals(addFlightRequestDTO.getTo()) &&
                    flight.getCarrier().toLowerCase().equals(addFlightRequestDTO.getCarrier().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkIfAirportsAreSimilarWhenAddingFlight(AddFlightRequestDTO addFlightRequestDTO) {

        if (addFlightRequestDTO.getFrom().equals(addFlightRequestDTO.getTo())) {
            return true;
        }
        return false;
    }

    public synchronized Flight addFlight(AddFlightRequestDTO addFlightRequestDTO) {

        if (checkIfFlightPresent(addFlightRequestDTO)) {
            throw new IllegalStateException();
        }

        if (checkIfAirportsAreSimilarWhenAddingFlight(addFlightRequestDTO)) {
            throw new IllegalArgumentException();
        }

        if (addFlightRequestDTO.getArrivalTime().isBefore(addFlightRequestDTO.getDepartureTime()) ||
                (addFlightRequestDTO.getArrivalTime().equals(addFlightRequestDTO.getDepartureTime()))) {
            throw new IllegalArgumentException();
        }

        Flight flight = new Flight();

        flight.setId((long) count.incrementAndGet());

        flight.setArrivalTime(addFlightRequestDTO.getArrivalTime());
        flight.setDepartureTime(addFlightRequestDTO.getDepartureTime());
        flight.setCarrier(addFlightRequestDTO.getCarrier());
        flight.setFrom(addFlightRequestDTO.getFrom());
        flight.setTo(addFlightRequestDTO.getTo());

        flights.add(flight);

        return flight;
    }

    public synchronized void deleteFlight(Long id) {

    }

    public Boolean checkIfAirportsAreSimilarWhenSearchingFlight(SearchFlightDTO searchFlightDTO) {

        if (searchFlightDTO.getFrom().trim().toLowerCase().equals(searchFlightDTO.getTo().trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    public List<Flight> searchFlight(SearchFlightDTO searchFlightDTO) {

        List<Flight> searchedFlights = new ArrayList<>();

        if (checkIfAirportsAreSimilarWhenSearchingFlight(searchFlightDTO)) {
            throw new IllegalArgumentException();
        }

        return searchedFlights;
    }

    public ResponseEntity findFlightById(Long id) {

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (Flight flight : flights) {
            if (flight.getId().equals(id)) {
                responseEntity = new ResponseEntity<>(flight, HttpStatus.OK);
            }
        }

        return responseEntity;
    }


    public List<Airport> searchFlightRequest(String search) {

        List<Airport> airports = new ArrayList<>();

        for (Flight flight : flights) {

            Airport airport = flight.getFrom();

            if (airport.getCity().toUpperCase().startsWith(search.trim().toUpperCase()) ||
                airport.getCountry().toUpperCase().startsWith(search.trim().toUpperCase()) ||
                airport.getAirport().toUpperCase().startsWith(search.trim().toUpperCase())) {

                airports.add(airport);

            }
        }
        return airports;
    }


}

