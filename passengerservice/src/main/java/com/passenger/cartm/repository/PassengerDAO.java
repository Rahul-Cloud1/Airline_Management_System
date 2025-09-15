
    package com.passenger.cartm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.passenger.cartm.models.Passenger;

@Repository
public class PassengerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Check if passenger has bookings
    public boolean hasBookings(Long passengerId) {
        String sql = "SELECT COUNT(*) FROM BOOKINGS WHERE passenger_id = ?";
        Integer count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, passengerId);
        } catch (Exception e) {
            System.err.println("Error checking bookings for passenger " + passengerId + ": " + e.getMessage());
        }
        return count != null && count > 0;
    }

    public List<Passenger> findAll() {
        String sql = "SELECT * FROM PASSENGERS";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Passenger.class));
        
    }

    public Passenger findById(Long id) {
        String sql = "SELECT * FROM PASSENGERS WHERE id = ?";
        List<Passenger> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Passenger.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Passenger p) {
        String sql = "INSERT INTO PASSENGERS (name, email_id, phone, passport_number, date_of_birth) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, p.getName(), p.getEmailId(), p.getPhone(), p.getPassportNumber(), p.getDateOfBirth());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM PASSENGERS WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (org.springframework.dao.DataAccessException e) {
            // Log the exception (could use a logger in real projects)
            System.err.println("Error deleting passenger with id " + id + ": " + e.getMessage());
            return 0;
        }
    }
}
