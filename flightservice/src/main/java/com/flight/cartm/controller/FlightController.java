package com.flight.cartm.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.flight.cartm.models.Flight;
import com.flight.cartm.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return service.getFlightById(id);
    }

    @PostMapping("/add")
    public String addFlight(@RequestBody Flight f) {
        boolean success = service.saveFlight(f);
        return success ? "Flight added successfully!" : "Failed to add flight!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        boolean success = service.deleteFlight(id);
        return success ? "Flight deleted successfully!" : "Flight not found!";
    }
}
