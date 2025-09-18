package com.flight.cartm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flight.cartm.models.Flight;
import com.flight.cartm.repository.FlightDAO;

@Service
public class FlightService {

    private final FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public List<Flight> getAllFlights() {
        return flightDAO.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightDAO.findById(id);
    }

    public boolean saveFlight(Flight f) {
        return flightDAO.save(f) > 0;
    }

    public boolean deleteFlight(Long id) {
        return flightDAO.deleteById(id) > 0;
    }
        public Flight updateFlight(Long id, Flight f) {
            int updated = flightDAO.updateById(id, f);
            return updated > 0 ? flightDAO.findById(id) : null;
        }
}
