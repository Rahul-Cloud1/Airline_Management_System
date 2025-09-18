
package com.passenger.cartm.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passenger.cartm.models.Passenger;
import com.passenger.cartm.models.PassengerDetailsDTO;
import com.passenger.cartm.services.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @GetMapping("/all/details")
    public List<PassengerDetailsDTO> getAllPassengerDetails() {
        return service.getAllPassengerDetails();
    }
    

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
    public ResponseEntity<String> deletePassenger(@PathVariable Long id) {
        try {
            int result = service.deletePassenger(id);
            if (result == 1) {
                return ResponseEntity.ok("Passenger deleted successfully!");
            } else if (result == 2) {
                return ResponseEntity.status(409).body("Passenger has booking, cannot be deleted");
            } else {
                return ResponseEntity.status(404).body("Passenger not found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
