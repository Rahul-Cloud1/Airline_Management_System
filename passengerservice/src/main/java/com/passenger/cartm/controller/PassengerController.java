package com.passenger.cartm.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.passenger.cartm.models.Passenger;
import com.passenger.cartm.services.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Passenger> getAllPassengers() {
        return service.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return service.getPassengerById(id);
    }

    @PostMapping("/add")
    public String addPassenger(@RequestBody Passenger p) {
        int result = service.savePassenger(p);
        boolean success = result > 0;
        return success ? "Passenger added successfully!" : "Failed to add passenger!";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePassenger(@PathVariable Long id) {
        boolean success = service.deletePassenger(id);
        return success ? "Passenger deleted successfully!" : "Passenger not found!";
    }
}
