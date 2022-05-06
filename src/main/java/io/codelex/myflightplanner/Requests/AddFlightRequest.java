package io.codelex.myflightplanner.Requests;

import io.codelex.myflightplanner.Classes.Airport;
import io.codelex.myflightplanner.Classes.Flight;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AddFlightRequest {

    @Valid
    @NotNull
    private Airport from;

    @Valid
    @NotNull
    private Airport to;

    @NotBlank
    private String carrier;
    @Valid
    @NotBlank
    private String departureTime;
    @Valid
    @NotBlank
    private String arrivalTime;

    public AddFlightRequest(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    private Airport toAirport(String country, String city, String airport) {
        return new Airport(country, city, airport);
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public static AddFlightRequest toFlightRequest(Flight flight) {
        return new AddFlightRequest(flight.getFrom(), flight.getTo(), flight.getCarrier(), flight.getDepartureTime(), flight.getArrivalTime());
    }

    public static boolean compareFlights(AddFlightRequest flightRequest, AddFlightRequest flightRequest2) {
        return flightRequest.arrivalTime.equals(flightRequest2.arrivalTime) &&
                flightRequest.departureTime.equals(flightRequest2.departureTime) &&
                flightRequest.carrier.equals(flightRequest2.carrier) &&
                Airport.compareAirports(flightRequest.from, flightRequest2.from) &&
                Airport.compareAirports(flightRequest.to, flightRequest2.to);
    }

    @Override
    public String toString() {
        return "AddFlightRequest{" +
                "to=" + to +
                ", from=" + from +
                ", carrier='" + carrier + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}
