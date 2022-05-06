package io.codelex.myflightplanner.Classes;

import org.apache.commons.text.WordUtils;

import javax.validation.constraints.NotNull;

public class Airport {

    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = WordUtils.capitalize(country).strip();
        this.city = WordUtils.capitalize(city);
        this.airport = airport.toUpperCase().strip();
    }

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

    public static boolean compareAirports(Airport airport, Airport airport2) {
        return airport.country.equals(airport2.country) &&
                airport.city.equals(airport2.city) &&
                airport.airport.equals(airport2.airport);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airportName='" + airport + '\'' +
                '}';
    }

    public static boolean searchString(Airport airport, String search) {

        return airport.airport.toLowerCase().contains(search.toLowerCase().strip()) ||
                airport.city.toLowerCase().contains(search.toLowerCase()) ||
                airport.country.toLowerCase().contains(search.toLowerCase().strip());
    }
}
