
package com.passenger.cartm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passenger.cartm.clients.BookingServiceClient;
import com.passenger.cartm.clients.FlightServiceClient;
import com.passenger.cartm.models.Passenger;
import com.passenger.cartm.models.PassengerDetailsDTO;
import com.passenger.cartm.repository.PassengerDAO;

@Service
public class PassengerService {
    @Autowired
    private BookingServiceClient bookingServiceClient;
    @Autowired
    private FlightServiceClient flightServiceClient;

    public List<PassengerDetailsDTO> getAllPassengerDetails() {
        List<Passenger> passengers = getAllPassengers();
        List<PassengerDetailsDTO> result = new ArrayList<>();
        for (Passenger p : passengers) {
            PassengerDetailsDTO dto = new PassengerDetailsDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setEmailId(p.getEmailId());
            dto.setPhone(p.getPhone());
            dto.setPassportNumber(p.getPassportNumber());
            dto.setDateOfBirth(p.getDateOfBirth() != null ? p.getDateOfBirth().toString() : "");

            List<Map<String, Object>> bookings = new ArrayList<>();
            try {
                Object bookingsObj = bookingServiceClient.getBookingsByPassengerId(p.getId());
                if (bookingsObj instanceof List) {
                    bookings = (List<Map<String, Object>>) bookingsObj;
                }
            } catch (Exception e) { /* handle error */ }

            List<PassengerDetailsDTO.BookingInfo> bookingInfos = new ArrayList<>();
            for (Map<String, Object> booking : bookings) {
                PassengerDetailsDTO.BookingInfo bInfo = new PassengerDetailsDTO.BookingInfo();
                bInfo.setBookingId(((Number) booking.getOrDefault("id", 0)).longValue());
                bInfo.setSeatNumber((String) booking.getOrDefault("seatNumber", ""));
                bInfo.setStatus((String) booking.getOrDefault("status", ""));
                bInfo.setBookingDate((String) booking.getOrDefault("bookingDate", ""));
                // Fetch flight details
                Map<String, Object> flight = null;
                try {
                    Object flightObj = flightServiceClient.getFlightById(((Number) booking.getOrDefault("flightId", 0)).longValue());
                    if (flightObj instanceof Map) {
                        flight = (Map<String, Object>) flightObj;
                    }
                } catch (Exception e) { /* handle error */ }
                if (flight != null) {
                    bInfo.setFlightNumber((String) flight.getOrDefault("flightNumber", ""));
                    bInfo.setOrigin((String) flight.getOrDefault("origin", ""));
                    bInfo.setDestination((String) flight.getOrDefault("destination", ""));
                    bInfo.setDepartureTime((String) flight.getOrDefault("departureTime", ""));
                    bInfo.setArrivalTime((String) flight.getOrDefault("arrivalTime", ""));
                }
                bookingInfos.add(bInfo);
            }
            dto.setBookings(bookingInfos);
            result.add(dto);
        }
        return result;
    }

    private final PassengerDAO passengerDAO;

    public PassengerService(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    // ...existing code...

        public List<Passenger> getAllPassengers() {
            return passengerDAO.findAll();
        }

        public Passenger getPassengerById(Long id) {
            return passengerDAO.findById(id);
        }

        public int savePassenger(Passenger passenger) {
            return passengerDAO.save(passenger);
        }

    // ...existing code...

    public int deletePassenger(Long id) {
        // 0 = not found, 1 = deleted, 2 = has bookings
        if (passengerDAO.hasBookings(id)) {
            return 2;
        }
        return passengerDAO.deleteById(id) > 0 ? 1 : 0;
    }
}
