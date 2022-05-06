package io.codelex.myflightplanner.Controllers;

import io.codelex.myflightplanner.Classes.Airport;
import io.codelex.myflightplanner.Classes.Flight;
import io.codelex.myflightplanner.Classes.PageResult;
import io.codelex.myflightplanner.Services.UserFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserFlightController {

    private UserFlightService userFlightService;

    public UserFlightController(UserFlightService userFlightService) {
        this.userFlightService = userFlightService;
    }

    @CrossOrigin()
    @GetMapping("/airports")
    @ResponseStatus(HttpStatus.OK)
    public synchronized List<Airport> getAirport(@RequestParam("search") String search) {
        return userFlightService.getAirport(search);
    }

    @CrossOrigin()
    @GetMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public synchronized Flight getFlightById(@PathVariable int id) {
        return userFlightService.getFlightById(id);
    }

    @CrossOrigin()
    @PostMapping("/flights/search")
    @ResponseStatus(HttpStatus.OK)
    public synchronized PageResult getFlight(@Valid @RequestBody String search) {
        return userFlightService.getFlightSearchList(search);//.toString();
    }

}
