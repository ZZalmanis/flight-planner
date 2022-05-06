package io.codelex.myflightplanner.Controllers;

import io.codelex.myflightplanner.Classes.Flight;
import io.codelex.myflightplanner.Requests.AddFlightRequest;
import io.codelex.myflightplanner.Services.AdminFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
public class AdminFlightController {

    private AdminFlightService adminFlightService;

    public AdminFlightController(AdminFlightService adminFlightService) {
        this.adminFlightService = adminFlightService;
    }

    @CrossOrigin()
    @GetMapping("/flights/{flightID}")
    public synchronized Flight getFlight(@PathVariable("flightID") int id) {
        for (int i = 0; i < adminFlightService.flightList().size(); i++) {
            if (adminFlightService.flightList().get(i).getId() == id) {
                return adminFlightService.flightList().get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin()
    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized ResponseEntity<Flight> addFlight(@Valid @RequestBody AddFlightRequest flightRequest) {
        return new ResponseEntity<>(adminFlightService.addFlight(flightRequest), HttpStatus.CREATED);
    }

    @CrossOrigin()
    @DeleteMapping("/flights/{flightID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlight(@PathVariable("flightID") int id) {
        adminFlightService.deleteFlight(id);
    }

}
