package com.booking.cartm.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booking.cartm.clients.FlightServiceClient;
import com.booking.cartm.clients.PassengerServiceClient;
import com.booking.cartm.models.Booking;
import com.booking.cartm.models.BookingDetailsDTO;
import com.booking.cartm.repository.BookingDAO;

@Service
public class BookingService {

    /**
     * Returns all bookings with passenger name and flight locations
     */
    public List<BookingDetailsDTO> getAllBookingDetails() {
        List<Booking> bookings = bookingDAO.findAll();
        List<BookingDetailsDTO> detailsList = new ArrayList<>();
        for (Booking booking : bookings) {
            // Fetch passenger details
            Map<String, Object> passenger = null;
            try {
                passenger = (Map<String, Object>) passengerServiceClient.getPassengerById(booking.getPassengerId());
            } catch (Exception e) { /* handle error */ }
            // Fetch flight details
            Map<String, Object> flight = null;
            try {
                flight = (Map<String, Object>) flightServiceClient.getFlightById(booking.getFlightId());
            } catch (Exception e) { /* handle error */ }

            BookingDetailsDTO dto = new BookingDetailsDTO();
            dto.setId(booking.getId());
            if (passenger != null) {
                dto.setPassengerName((String) passenger.getOrDefault("name", ""));
                dto.setEmailId((String) passenger.getOrDefault("emailId", ""));
                dto.setPhone((String) passenger.getOrDefault("phone", ""));
                dto.setPassportNumber((String) passenger.getOrDefault("passportNumber", ""));
                dto.setDateOfBirth((String) passenger.getOrDefault("dateOfBirth", ""));
            }
            if (flight != null) {
                dto.setFlightNumber((String) flight.getOrDefault("flightNumber", ""));
                dto.setOrigin((String) flight.getOrDefault("origin", ""));
                dto.setDestination((String) flight.getOrDefault("destination", ""));
                dto.setDepartureTime((String) flight.getOrDefault("departureTime", ""));
                dto.setArrivalTime((String) flight.getOrDefault("arrivalTime", ""));
            }
            dto.setSeatNumber(booking.getSeatNumber());
            dto.setStatus(booking.getStatus());
            dto.setBookingDate(booking.getBookingDate() != null ? booking.getBookingDate().toString() : "");
            detailsList.add(dto);
        }
        return detailsList;
    }
    private final BookingDAO bookingDAO;
    private final FlightServiceClient flightServiceClient;
    private final PassengerServiceClient passengerServiceClient;

    public BookingService(BookingDAO bookingDAO, FlightServiceClient flightServiceClient, PassengerServiceClient passengerServiceClient) {
        this.bookingDAO = bookingDAO;
        this.flightServiceClient = flightServiceClient;
        this.passengerServiceClient = passengerServiceClient;
    }
            public List<Booking> getBookingsByPassengerId(Long passengerId) {
                return bookingDAO.findByPassengerId(passengerId);
            }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingDAO.findById(id);
    }

    public int saveBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public boolean deleteBooking(Long id) {
        return bookingDAO.deleteById(id) > 0;
    }

    public boolean updateBooking(Long id, Booking updatedBooking) {
        return bookingDAO.update(id, updatedBooking) > 0;
    }

    // Example: Fetch flight details from flightservice
    public Object getFlightDetails(Long flightId) {
        return flightServiceClient.getFlightById(flightId);
    }

    // Example: Fetch passenger details from passengerservice
    public Object getPassengerDetails(Long passengerId) {
        return passengerServiceClient.getPassengerById(passengerId);
    }
}
