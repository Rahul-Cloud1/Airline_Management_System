package com.flight.cartm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        @PutMapping("/update/{id}")
        public String updateFlight(@PathVariable Long id, @RequestBody Flight f) {
            Flight updated = service.updateFlight(id, f);
            return updated != null ? "Flight Updated successfully!" : "Flight not found!";
        }
}
