package io.codelex.myflightplanner.Repositories;

import io.codelex.myflightplanner.Classes.Airport;
import io.codelex.myflightplanner.Classes.Flight;
import io.codelex.myflightplanner.Requests.AddFlightRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Configuration()
public class FlightRepository {

    private List<Flight> flightList = new ArrayList<>();

    @RolesAllowed("ADMIN")
    public void deleteFlight(int id) {
        for (int i = 0; i < flightList.size(); i++) {
            if (flightList.get(i).getId() == id) {
                flightList.remove(i);
            }
        }
    }

    @RolesAllowed("ADMIN")
    public Flight addFlight(AddFlightRequest addFlightRequest) {

        int findLowestFreeId = 0;

        for (Flight flight : flightList) {
            if (flight.getId() == findLowestFreeId) {
                findLowestFreeId++;
            } else {
                break;
            }
        }

        Flight newFlight = new Flight(findLowestFreeId, addFlightRequest);

        if (
                Airport.compareAirports(newFlight.getFrom(), newFlight.getTo()) ||
                        Flight.checkForBlankValues(newFlight) ||
                        !Flight.tryParseDate(newFlight.getDepartureTime()) ||
                        !Flight.tryParseDate(newFlight.getArrivalTime()) ||
                        !Flight.checkDates(newFlight.getDepartureTime(), newFlight.getArrivalTime())
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (flightAlreadyExists(newFlight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        flightList.add(newFlight);
        return newFlight;

    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void clearFlights() {
        flightList.clear();

    }

    private boolean flightAlreadyExists(Flight flight) {

        for (Flight value : flightList) {
            if (AddFlightRequest.compareFlights(AddFlightRequest.toFlightRequest(value), (AddFlightRequest.toFlightRequest(flight)))) {
                return true;
            }
        }
        return false;
    }

}
