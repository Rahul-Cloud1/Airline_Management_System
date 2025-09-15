package com.booking.cartm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.cartm.models.Booking;
import com.booking.cartm.models.BookingDetailsDTO;
import com.booking.cartm.services.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/passenger/{passengerId}")
    public List<Booking> getBookingsByPassengerId(@PathVariable Long passengerId) {
        return service.getBookingsByPassengerId(passengerId);
    }
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }

    @PostMapping("/add")
    public String addBooking(@RequestBody Booking b) {
        int result = service.saveBooking(b);
        boolean success = result > 0;
        return success ? "Booking added successfully!" : "Failed to add booking!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        boolean success = service.deleteBooking(id);
        return success ? "Booking deleted successfully!" : "Booking not found!";
    }
    
        @PutMapping("/update/{id}")
        public String updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
            boolean success = service.updateBooking(id, updatedBooking);
            return success ? "Booking updated successfully!" : "Booking not found!";
        }
    
    @GetMapping("/flight/{flightId}")
    public Object getFlightDetails(@PathVariable Long flightId) {
        return service.getFlightDetails(flightId);
    }

    /**
     * Returns all bookings with passenger name and flight locations
     */
    @GetMapping("/all/details")
    public List<BookingDetailsDTO> getAllBookingDetails() {
        return service.getAllBookingDetails();
    }
}
