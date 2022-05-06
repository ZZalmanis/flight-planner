package io.codelex.myflightplanner.Services;

import io.codelex.myflightplanner.Classes.Airport;
import io.codelex.myflightplanner.Classes.Flight;
import io.codelex.myflightplanner.Classes.PageResult;
import io.codelex.myflightplanner.Repositories.FlightRepository;
import io.codelex.myflightplanner.Requests.SearchFlightsRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFlightService {
    private FlightRepository flightRepository;

    public UserFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> flightList() {
        return flightRepository.getFlightList();
    }

    public List<Airport> getAirport(String search) {
        List<Airport> output = new ArrayList<>();
        for (int i = 0; i < flightList().size(); i++) {
            if (Airport.searchString(flightList().get(i).getFrom(), search)) {
                output.add(flightList().get(i).getFrom());
                return output;
            }
            if (Airport.searchString(flightList().get(i).getTo(), search)) {
                output.add(flightList().get(i).getTo());
                return output;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Flight getFlightById(int id) {
        if (!(id < 0) && !(id - 1 > flightList().size())) {
            for (int i = 0; i < flightList().size(); i++) {
                if (flightList().get(i).getId() == id) {
                    return flightList().get(i);
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public PageResult getFlightSearchList(String search) {

        Flight flight;
        PageResult pageResult = new PageResult();

        if (!SearchFlightsRequest.isProperlyFormatted(search)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        String searchFrom = StringUtils.substringBetween(search, "{\"from\":\"", "\",\"to\"");
        String searchTo = StringUtils.substringBetween(search, "\"to\":\"", "\",\"departure");
        String searchDeparture = StringUtils.substringBetween("\"departureDate\":\"", "\"}");

        if (searchFrom == null || searchTo == null || searchFrom.equals(searchTo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < flightList().size(); i++) {
            flight = flightList().get(i);

            if (
                    flight.getFrom().getAirport().equals(searchFrom) &&
                            flight.getTo().getAirport().equals(searchTo)
            ) {
                pageResult.addFlight(flightList().get(i));
            }
        }
        return pageResult;
    }
}
