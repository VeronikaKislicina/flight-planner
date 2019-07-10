package io.codelex.flightplanner.airport;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Airport {
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String airport;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return airport.toLowerCase().trim().equals(airport1.airport.toLowerCase().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport);
    }

}
