package io.codelex.myflightplanner.Services;

import io.codelex.myflightplanner.Repositories.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class TestingFlightService {
    private FlightRepository flightRepository;

    public TestingFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }
}
