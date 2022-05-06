package io.codelex.myflightplanner.Services;

import io.codelex.myflightplanner.Classes.Flight;
import io.codelex.myflightplanner.Repositories.FlightRepository;
import io.codelex.myflightplanner.Requests.AddFlightRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminFlightService {

    private FlightRepository flightRepository;

    public AdminFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public synchronized Flight addFlight(AddFlightRequest addFlightRequest) {
        return flightRepository.addFlight(addFlightRequest);
    }

    public synchronized void deleteFlight(int id) {
        flightRepository.deleteFlight(id);
    }

    public synchronized List<Flight> flightList() {
        return flightRepository.getFlightList();
    }

}
