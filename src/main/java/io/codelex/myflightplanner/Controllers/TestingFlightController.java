package io.codelex.myflightplanner.Controllers;

import io.codelex.myflightplanner.Services.TestingFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testing-api")
public class TestingFlightController {

    private TestingFlightService testingFlightService;

    public TestingFlightController(TestingFlightService testingFlightService) {
        this.testingFlightService = testingFlightService;
    }

    @CrossOrigin()
    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public synchronized void clearFlights() {
        testingFlightService.clearFlights();
    }
}
