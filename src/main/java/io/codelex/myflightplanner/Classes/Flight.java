package io.codelex.myflightplanner.Classes;

import io.codelex.myflightplanner.Requests.AddFlightRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private int id;
    @NotNull
    @Valid
    private Airport from;
    @NotNull
    @Valid
    private Airport to;
    @NotNull
    private String carrier;
    @NotNull
    @Valid
    private String departureTime;
    @NotNull
    @Valid
    private String arrivalTime;

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Flight(int id, AddFlightRequest addFlightRequest) {
        this.id = id;
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = addFlightRequest.getDepartureTime();
        this.arrivalTime = addFlightRequest.getArrivalTime();
    }

    public int getId() {
        return id;
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

    public static synchronized boolean tryParseDate(String input) {
        try {
            LocalDateTime.parse(input, format);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static synchronized boolean checkDates(String departureTime, String arrivalTime) {
        LocalDateTime departure = LocalDateTime.parse(departureTime, format);
        LocalDateTime arrival = LocalDateTime.parse(arrivalTime, format);

        if (departure.isEqual(arrival) || arrival.isBefore(departure)) {
            return false;
        } else {
            return true;
        }
    }

    public static synchronized boolean checkForBlankValues(Flight flight) {
        if (flight.getFrom().getAirport().isBlank() ||
                flight.getFrom().getCity().isBlank() ||
                flight.getFrom().getCountry().isBlank() ||
                flight.getTo().getAirport().isBlank() ||
                flight.getTo().getCity().isBlank() ||
                flight.getTo().getCountry().isBlank() ||
                flight.getCarrier().isBlank()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", fromAirport=" + from +
                ", toAirport=" + to +
                ", carrier='" + carrier + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", format=" + format +
                '}';
    }
}
