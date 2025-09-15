package com.flight.cartm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flight.cartm.models.Flight;

@Repository
public class FlightDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Flight> findAll() {
    String sql = "SELECT * FROM flight"; // Table name in lowercase (check DB)
    

    List<Flight> flights = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class));

   

    return flights;
}


    public Flight findById(Long id) {
        String sql = "SELECT * FROM FLIGHT WHERE id = ?";
        List<Flight> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Flight f) {
        String sql = "INSERT INTO FLIGHT (flight_number, origin, destination, departure_time, arrival_time, seats_available) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, f.getFlightNumber(), f.getOrigin(), f.getDestination(), f.getDepartureTime(), f.getArrivalTime(), f.getSeatsAvailable());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM FLIGHT WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
